function getXMLObject()  //XML OBJECT
{
   var xmlHttp = false;
   try {
     xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
   }
   catch (e) {
     try {
       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
     }
     catch (e2) {
       xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
     }
   }
   if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
     xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
   }
   return xmlHttp;  // Mandatory Statement returning the ajax object created
}
 
var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object

	/**********************************************************************/
	/**************************INICIO DE VALIDACIONES AIEPI****************/
	/**********************************************************************/

//funcion de calendario fechaRecienNacido
/*$(function() {
  $( "#datepickerRecienNacido" ).datepicker({maxDate: "+0D" });
});*/


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
	/**********************************************************************/
	/**************************FIN DE VALIDACIONES AIEPI*******************/
	/**********************************************************************/

	/**********************************************************************/
	/**************************INICIO DE INGRESOS CIE 10*******************/
	/**********************************************************************/
/*INGRESO DE CODIGOS CIE 10 DEL FORMATO: 0 MESES - 2 MESES AIEPI*/
function IngresarDxCeroDosCex() {
	var DxNombreCeroDos=document.getElementById("txtNomDiagnos").value;
	var DxAiepiCeroDos=document.getElementById("txtCodDiagnostico").value;
		if (DxAiepiCeroDos=="") {
			alert("Escriba el diagnostico del paciente");
			document.getElementById("txtNomDiagnos").focus();
		} else {

	var CodUsuario=document.getElementById("txtCodusuario").value;
	var CodAdmision=document.getElementById("CodAdmision").value;
	var CodPaciente=document.getElementById("CodPac").value;
	
	ajax=getXMLObject();
	ajax.open("POST","AiepiMultiplePacientes",true);

	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {	
			//var codFor=ajax.responseText;
			MostrarDxCeroDosCex(CodAdmision);
			document.getElementById("txtNomDiagnos").value="";
			document.getElementById("txtCodDiagnostico").value="";
			document.getElementById("guardar").disabled=false;
			//document.getElementById("txtCodDiagnostico").value=codFor;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=IngresarDxCeroDos&DxAiepiCeroDos=" + DxAiepiCeroDos
			+ "&DxNombreCeroDos=" + DxNombreCeroDos
			+ "&CodUsuario=" + CodUsuario
			+ "&CodAdmision=" + CodAdmision
			+ "&CodPaciente=" + CodPaciente);
		}//DxAiepi
}

/*INGRESO DE CODIGOS CIE 10 DEL FORMATO: 2 MESES - 5 AÑOS AIEPI*/
function IngresarDxDosCincoCex() {
	var DxNombreDosCinco=document.getElementById("txtNomDiagnos").value;
	var DxAiepiDosCinco=document.getElementById("txtCodDiagnostico").value;
		if (DxAiepiDosCinco=="") {
			alert("Escriba el diagnostico del paciente");
			document.getElementById("txtCodDiagnostico").focus();
		} else {

	var CodUsuario=document.getElementById("txtCodusuario").value;
	var CodAdmision=document.getElementById("CodAdmision").value;
	var CodPaciente=document.getElementById("CodPac").value;
	
	ajax=getXMLObject();
	ajax.open("POST","AiepiMultiplePacientes",true);

	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {	
			//var codFor=ajax.responseText;
			MostrarDxDosCincoCex(CodAdmision);
			document.getElementById("txtNomDiagnos").value="";
			document.getElementById("txtCodDiagnostico").value="";
			document.getElementById("guardar").disabled=false;
			//document.getElementById("txtCodDiagnostico").value=codFor;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=IngresarDxDosCinco&DxAiepiDosCinco=" + DxAiepiDosCinco
			+ "&DxNombreDosCinco=" + DxNombreDosCinco
			+ "&CodUsuario=" + CodUsuario
			+ "&CodAdmision=" + CodAdmision
			+ "&CodPaciente=" + CodPaciente);
		}//DxAiepi
}

/*IMPRIME LOS CODIGOS CIE 10 DEL FORMATO: 0 MESES - 2 MESES AIEPI*/
function MostrarDxCeroDosCex(Admision) {
	DivAiepi=document.getElementById('dxAiepi');
	var CodAdmision=document.getElementById("CodAdmision").value;
	
	ajax=getXMLObject();
	ajax.open("POST","AiepiMultiplePacientes",true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			Contenido=ajax.responseText;
			DivAiepi.innerHTML=Contenido;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MostrarDxCeroDos&CodAdmision=" + CodAdmision);
		
}

/*IMPRIME LOS CODIGOS CIE 10 DEL FORMATO: 2 MESES - 5 AÑOS AIEPI*/
function MostrarDxDosCincoCex(Admision) {
	DivAiepi=document.getElementById('dxAiepi');
	var CodAdmision=document.getElementById("CodAdmision").value;
	
	ajax=getXMLObject();
	ajax.open("POST","AiepiMultiplePacientes",true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			Contenido=ajax.responseText;
			DivAiepi.innerHTML=Contenido;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MostrarDxDosCinco&CodAdmision=" + CodAdmision);
		
}

/*ELIMINA LOS CODIGOS CIE 10 DE LA TABLA HIC_DIAGNOSTICOINGRESO DEL FORMATO: 0 MESES - 2 MESES AIEPI*/
function EliminarDxCeroDos(codDxCeroDos) {
	DivAiepi=document.getElementById('dxAiepi');
	var CodAdmision=document.getElementById("CodAdmision").value;
	
	ajax=getXMLObject();
	ajax.open("POST","AiepiMultiplePacientes",true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			MostrarDxCeroDosCex(CodAdmision);
			document.getElementById("txtNomDiagnos").focus();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=EliminarDxCeroDos&CodAdmision=" + CodAdmision+"&codDxCeroDos="+codDxCeroDos);
		
}

/*ELIMINA LOS CODIGOS CIE 10 DE LA TABLA AIEPI DX 0-2*/
function EliminarDxAiepiCeroDos(codAiepiCeroDos) {
	DivAiepi=document.getElementById('dxAiepi');
	var CodAdmision=document.getElementById("CodAdmision").value;
	
	ajax=getXMLObject();
	ajax.open("POST","AiepiMultiplePacientes",true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			MostrarDxCeroDosCex(CodAdmision);
			document.getElementById("txtNomDiagnos").focus();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=EliminarDxAiepiCeroDos&CodAdmision=" + CodAdmision+"&codAiepiCeroDos="+codAiepiCeroDos);
		
}

/*ELIMINA LOS CODIGOS CIE 10 DE LA TABLA HIC_DIAGNOSTICOINGRESO DEL FORMATO: 2 MESES - 5 AÑOS AIEPI*/
function EliminarDxDosCinco(codDx) {
	DivAiepi=document.getElementById('dxAiepi');
	var CodAdmision=document.getElementById("CodAdmision").value;
	
	ajax=getXMLObject();
	ajax.open("POST","AiepiMultiplePacientes",true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			MostrarDxDosCincoCex(CodAdmision);
			document.getElementById("txtNomDiagnos").focus();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=EliminarDxDosCinco&CodAdmision=" + CodAdmision+"&codDx="+codDx);
		
}

/*ELIMINA LOS CODIGOS CIE 10 DE LA TABLA AIEPI DX 2-5*/
function EliminarDxAiepiDosCinco(codAiepi) {
	DivAiepi=document.getElementById('dxAiepi');
	var CodAdmision=document.getElementById("CodAdmision").value;
	
	ajax=getXMLObject();
	ajax.open("POST","AiepiMultiplePacientes",true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			MostrarDxDosCincoCex(CodAdmision);
			document.getElementById("txtNomDiagnos").focus();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=EliminarDxAiepiDosCinco&CodAdmision=" + CodAdmision+"&codAiepi="+codAiepi);
		
}
/*FIN DE VALIDACIONES AIEPI*/
	/**********************************************************************/
	/**************************FIN DE INGRESOS CIE 10**********************/
	/**********************************************************************/

	/**********************************************************************/
	/**************************GUARDAR INFORME 0-2 AIEPI*******************/
	/**********************************************************************/
function GuardarAiepiCeroDosMesesCex(){
	
	var CodPaciente=document.getElementById("CodPac").value;
	var CodUsuario=document.getElementById("txtCodusuario").value;
	var CodAdmision=document.getElementById("CodAdmision").value;
	
	/******************************************************************************************************/
	/*************************************ANTECEDENTES*****************************************************/
	/******************************************************************************************************/
	var motConsulta=document.getElementById("mot_consulta").value;
	if (motConsulta=="") {
	alert("Escribir el motivo de la enfermedad actual");
		document.getElementById("mot_consulta").focus();
	} else {
		
	var antEmbarazo=document.getElementById("ant_embarazo").value;
	if (antEmbarazo=="") {
		alert("Escribir los antecedentes del embarazo");
		document.getElementById("ant_embarazo").focus();
	} else {

	var pesoNacer=document.getElementById("peso_nacer").value;
	if (pesoNacer=="") {
		alert("Escriba el peso al nacer");
		document.getElementById("peso_nacer").focus();
	} else {
	
	var tallaNacer=document.getElementById("talla_nacer").value;
	if (tallaNacer=="") {
		alert("Escriba la talla al nacer");
		document.getElementById("talla_nacer").focus();
	} else {
		
	var edadGestacional=document.getElementById("edad_gestacional").value;
	if (edadGestacional=="") {
		alert("Escriba la edad gestacional");
		document.getElementById("edad_gestacional").focus();
	} else {
		
	var hemoclasificacion=document.getElementById("hemoclasificacion").value;
	if (hemoclasificacion=="") {
		alert("Escriba la hemoclasificacion");
		document.getElementById("hemoclasificacion").focus();
	} else {
	
	var pesoActual=document.getElementById("peso_actual").value;
	if (pesoActual=="") {
		alert("Escriba el peso actual");
		document.getElementById("peso_actual").focus();
	} else {
		
	var talla=document.getElementById("talla").value;
	if (talla=="") {
		alert("Escriba la talla");
		document.getElementById("talla").focus();
	} else {
		
	var perimetroCefalico=document.getElementById("perimetro_cefalico").value;
	if (perimetroCefalico=="") {
		alert("Escriba el perimetro cefalico");
		document.getElementById("perimetro_cefalico").focus();
	} else {
		
	var frecuenciaCardiaca=document.getElementById("frecuencia_cardiaca").value;
	if (frecuenciaCardiaca=="") {
		alert("Escriba la frecuencia cardiaca");
		document.getElementById("frecuencia_cardiaca").focus();
	} else {
		
	var frecuenciaRespiratoria=document.getElementById("frecuencia_respiratoria").value;
	if (frecuenciaRespiratoria=="") {
		alert("Escriba la frecuencia respiratoria");
		document.getElementById("frecuencia_respiratoria").focus();
	} else {
		
	var temperatura=document.getElementById("temperatura").value;
	if (temperatura=="") {
		alert("Escriba la temperatura");
		document.getElementById("temperatura").focus();
	} else {
		
	var centigrados=document.getElementById("centigrados").value;
	if (centigrados=="") {
		alert("Escriba los centigrados");
		document.getElementById("centigrados").focus();
	} else {
			
	/******************************************************************************************************/
	/*************************************ENFERMEDAD GRAVE O INFECCION LOCAL*******************************/
	/******************************************************************************************************/
	//Radio Bebe Pecho
	var radBebePecho = "";
	var radioBebePecho = document.getElementsByName("rad_bebe_pecho");
		for (var x = 0; x < radioBebePecho.length; x ++) {
			if(radioBebePecho[x].checked) {
				radBebePecho=radioBebePecho[x].id;
			}                               
		}
	if (radBebePecho==false) {
		alert("Seleccione si puede beber del pecho, en la Seccion de Verificar Enfermedad Grave");
	} else {

	//Radio Fiebre
	var radFiebre = "";
	var radioFiebre = document.getElementsByName("rad_fiebre");
		for (var x = 0; x < radioFiebre.length; x ++) {
			if(radioFiebre[x].checked) {
				radFiebre=radioFiebre[x].id;
			}                               
		}
	if (radFiebre==false) {
		alert("Seleccione si ha tenido fiebre, en la Seccion de Verificar Enfermedad Grave");
	} else {	
		
	//Radio Hipotermia
	var radHipotermia = "";
	var radioHipotermia = document.getElementsByName("rad_hipotermia");
		for (var x = 0; x < radioHipotermia.length; x ++) {
			if(radioHipotermia[x].checked) {
				radHipotermia=radioHipotermia[x].id;
			}
		}
	if (radHipotermia==false) {
		alert("Seleccione si ha tenido hipotermia, en la Seccion de Verificar Enfermedad Grave");
	} else {
			
	//Radio Convulsiones
	var radConvulsiones = "";
	var radioConvulsiones = document.getElementsByName("rad_convulsiones");
		for (var x = 0; x < radioConvulsiones.length; x ++) {
			if(radioConvulsiones[x].checked) {
				radConvulsiones=radioConvulsiones[x].id;
			}                               
		}
	if (radConvulsiones==false) {
		alert("Seleccione si ha tenido convulsiones, en la Seccion de Verificar Enfermedad Grave");
	} else {
				
	//Radio Vomito
	var radVomito = "";
	var radioVomito = document.getElementsByName("rad_vomito");
		for (var x = 0; x < radioVomito.length; x ++) {
			if(radioVomito[x].checked) {
				radVomito=radioVomito[x].id;
			}                               
		}
	if (radVomito==false) {
		alert("Seleccione si ha tenido vomito, en la Seccion de Verificar Enfermedad Grave");
	} else {
		
	var vomitaTodo=document.getElementById("vomita_todo").value;	
	
	//Radio Diarrea
	var radDiarrea = "";
	var radioDiarrea = document.getElementsByName("rad_diarrea");
		for (var x = 0; x < radioDiarrea.length; x ++) {
			if(radioDiarrea[x].checked) {
				radDiarrea=radioDiarrea[x].id;
			}                               
		}
	if (radDiarrea==false) {
		alert("Seleccione si tiene diarrea, en la Seccion de Verificar Enfermedad Grave");
	} else {

	var cuandoDiarrea=document.getElementById("cuando_diarrea").value;
	
	//Radio Dificultad Respirar
	var radDificultadRespirar = "";
	var radioDificultadRespirar = document.getElementsByName("rad_dificultad_respirar");
		for (var x = 0; x < radioDificultadRespirar.length; x ++) {
			if(radioDificultadRespirar[x].checked) {
				radDificultadRespirar=radioDificultadRespirar[x].id;
			}                               
		}
	if (radDificultadRespirar==false) {
		alert("Seleccione si tiene dificultad para respirar, en la Seccion de Verificar Enfermedad Grave");
	} else {
		
	var expliqueDificultadRespirar=document.getElementById("exp_dificultad_respirar").value;
	
	//Radio Sangre Heces
	var radSangreHeces = "";
	var radioSangreHeces = document.getElementsByName("rad_sangre_heces");
		for (var x = 0; x < radioSangreHeces.length; x ++) {
			if(radioSangreHeces[x].checked) {
				radSangreHeces=radioSangreHeces[x].id;
			}                               
		}
	if (radSangreHeces==false) {
		alert("Seleccione si hay sangre en las heces, en la Seccion de Verificar Enfermedad Grave");
	} else {
		
	var panalesOrinados=document.getElementById("panales_orinados").value;
	if (panalesOrinados==false) {
		alert("Escriba cuantos panales ha orinado en las ultimas 24 horas");
		document.getElementById("panales_orinados").focus();
	} else {
	
	//Checkbox Mueve Solo Estimulo
	var ckbMueveSolo = "";
	var checkboxMueveSolo = document.getElementsByName("mueve_solo");
		for (var x = 0; x < checkboxMueveSolo.length; x ++) {
			if(checkboxMueveSolo[x].checked) {
				ckbMueveSolo=checkboxMueveSolo[x].id;
			}                               
		}
	
	//Checkbox Letargico
	var ckbLetargico = "";
	var checkboxLetargico = document.getElementsByName("letargico");
		for (var x = 0; x < checkboxLetargico.length; x ++) {
			if(checkboxLetargico[x].checked) {
				ckbLetargico=checkboxLetargico[x].id;
			}                               
		}
	
	//Checkbox Luce Mal
	var ckbLuceMal = "";
	var checkboxLuceMal = document.getElementsByName("luce_mal");
		for (var x = 0; x < checkboxLuceMal.length; x ++) {
			if(checkboxLuceMal[x].checked) {
				ckbLuceMal=checkboxLuceMal[x].id;
			}                               
		}
		
	//Checkbox Irritable
	var ckbIrritable = "";
	var checkboxIrritable = document.getElementsByName("irritable");
		for (var x = 0; x < checkboxIrritable.length; x ++) {
			if(checkboxIrritable[x].checked) {
				ckbIrritable=checkboxIrritable[x].id;
			}                               
		}	
		
	//Checkbox Palidez
	var ckbPalidez = "";
	var checkboxPalidez = document.getElementsByName("palidez");
		for (var x = 0; x < checkboxPalidez.length; x ++) {
			if(checkboxPalidez[x].checked) {
				ckbPalidez=checkboxPalidez[x].id;
			}                               
		}	
			
	//Checkbox Cianosis
	var ckbCianosis = "";
	var checkboxCianosis = document.getElementsByName("cianosis");
		for (var x = 0; x < checkboxCianosis.length; x ++) {
			if(checkboxCianosis[x].checked) {
				ckbCianosis=checkboxCianosis[x].id;
			}                               
		}
		
	//Checkbox Apneas
	var ckbApneas = "";
	var checkboxApneas = document.getElementsByName("apneas");
		for (var x = 0; x < checkboxApneas.length; x ++) {
			if(checkboxApneas[x].checked) {
				ckbApneas=checkboxApneas[x].id;
			}                               
		}
		
	//Checkbox Bilirrubinas
	var ckbBilirrubinas = "";
	var checkboxBilirrubinas = document.getElementsByName("bilirrubinas");
		for (var x = 0; x < checkboxBilirrubinas.length; x ++) {
			if(checkboxBilirrubinas[x].checked) {
				ckbBilirrubinas=checkboxBilirrubinas[x].id;
			}                               
		}
		
	//Checkbox Estridor
	var ckbEstridor = "";
	var checkboxEstridor = document.getElementsByName("estridor");
		for (var x = 0; x < checkboxEstridor.length; x ++) {
			if(checkboxEstridor[x].checked) {
				ckbEstridor=checkboxEstridor[x].id;
			}                               
		}
		
	//Checkbox Frecuencia Respiratoria
	var ckbFR = "";
	var checkboxFR = document.getElementsByName("fr");
		for (var x = 0; x < checkboxFR.length; x ++) {
			if(checkboxFR[x].checked) {
				ckbFR=checkboxFR[x].id;
			}                               
		}
		
	//Checkbox Aleteo Nasal
	var ckbAleteoNasal = "";
	var checkboxAleteoNasal = document.getElementsByName("aleteo_nasal");
		for (var x = 0; x < checkboxAleteoNasal.length; x ++) {
			if(checkboxAleteoNasal[x].checked) {
				ckbAleteoNasal=checkboxAleteoNasal[x].id;
			}                               
		}	
		
	//Checkbox Quejido
	var ckbQuejido = "";
	var checkboxQuejido = document.getElementsByName("quejido");
		for (var x = 0; x < checkboxQuejido.length; x ++) {
			if(checkboxQuejido[x].checked) {
				ckbQuejido=checkboxQuejido[x].id;
			}                               
		}
		
	//Checkbox Sibilancia
	var ckbSibilancia = "";
	var checkboxSibilancia = document.getElementsByName("sibilancia");
		for (var x = 0; x < checkboxSibilancia.length; x ++) {
			if(checkboxSibilancia[x].checked) {
				ckbSibilancia=checkboxSibilancia[x].id;
			}                               
		}
	
	//Checkbox Frecuencia Cardiaca
	var ckbFC = "";
	var checkboxFC = document.getElementsByName("fc");
		for (var x = 0; x < checkboxFC.length; x ++) {
			if(checkboxFC[x].checked) {
				ckbFC=checkboxFC[x].id;
			}                               
		}
		
	//Checkbox Supuracion Oido
	var ckbSupuracionOido = "";
	var checkboxSupuracionOido = document.getElementsByName("supuracion_oido");
		for (var x = 0; x < checkboxSupuracionOido.length; x ++) {
			if(checkboxSupuracionOido[x].checked) {
				ckbSupuracionOido=checkboxSupuracionOido[x].id;
			}                               
		}
		
	//Checkbox Tiraje Subcostal
	var ckbTirajeSubcostal = "";
	var checkboxTirajeSubcostal = document.getElementsByName("tiraje_subcostal");
		for (var x = 0; x < checkboxTirajeSubcostal.length; x ++) {
			if(checkboxTirajeSubcostal[x].checked) {
				ckbTirajeSubcostal=checkboxTirajeSubcostal[x].id;
			}                               
		}
	
	//Checkbox Secrecion Purulenta Conjuntival
	var ckbSecPurulentaConjuntival = "";
	var checkboxSecPurulentaConjuntival = document.getElementsByName("sec_purulenta_conjuntival");
		for (var x = 0; x < checkboxSecPurulentaConjuntival.length; x ++) {
			if(checkboxSecPurulentaConjuntival[x].checked) {
				ckbSecPurulentaConjuntival=checkboxSecPurulentaConjuntival[x].id;
			}                               
		}
		
	//Checkbox Edema Palpebral
	var ckbEdemaPalpebral = "";
	var checkboxEdemaPalpebral = document.getElementsByName("edema_palpebral");
		for (var x = 0; x < checkboxEdemaPalpebral.length; x ++) {
			if(checkboxEdemaPalpebral[x].checked) {
				ckbEdemaPalpebral=checkboxEdemaPalpebral[x].id;
			}                               
		}
		
	//Checkbox Eritema Periumbilical
	var ckbEritemaPeriumbilical = "";
	var checkboxEritemaPeriumbilical = document.getElementsByName("eritema_periumbilical");
		for (var x = 0; x < checkboxEritemaPeriumbilical.length; x ++) {
			if(checkboxEritemaPeriumbilical[x].checked) {
				ckbEritemaPeriumbilical=checkboxEritemaPeriumbilical[x].id;
			}                               
		}
		
	var pustulasPiel=document.getElementById("pustulas_piel").value;
		
	//Checkbox Secrecion Purulenta Ombligo
	var ckbSecPurulentaOmbligo = "";
	var checkboxSecPurulentaOmbligo = document.getElementsByName("sec_purulenta_ombligo");
		for (var x = 0; x < checkboxSecPurulentaOmbligo.length; x ++) {
			if(checkboxSecPurulentaOmbligo[x].checked) {
				ckbSecPurulentaOmbligo=checkboxSecPurulentaOmbligo[x].id;
			}                               
		}
		
	//Checkbox Equimosis
	var ckbEquimosis = "";
	var checkboxEquimosis = document.getElementsByName("equimosis");
		for (var x = 0; x < checkboxEquimosis.length; x ++) {
			if(checkboxEquimosis[x].checked) {
				ckbEquimosis=checkboxEquimosis[x].id;
			}                               
		}
		
	//Checkbox Petequias
	var ckbPetequias = "";
	var checkboxPetequias = document.getElementsByName("petequias");
		for (var x = 0; x < checkboxPetequias.length; x ++) {
			if(checkboxPetequias[x].checked) {
				ckbPetequias=checkboxPetequias[x].id;
			}                               
		}
		
	//Checkbox Placas Blanquecinas
	var ckbPlacasBlanquecinas = "";
	var checkboxPlacasBlanquecinas = document.getElementsByName("placas_blanquecinas");
		for (var x = 0; x < checkboxPlacasBlanquecinas.length; x ++) {
			if(checkboxPlacasBlanquecinas[x].checked) {
				ckbPlacasBlanquecinas=checkboxPlacasBlanquecinas[x].id;
			}                               
		}
		
	//Checkbox Hemorragia
	var ckbHemorragia = "";
	var checkboxHemorragia = document.getElementsByName("hemorragia");
		for (var x = 0; x < checkboxHemorragia.length; x ++) {
			if(checkboxHemorragia[x].checked) {
				ckbHemorragia=checkboxHemorragia[x].id;
			}                               
		}
	
	//Checkbox Llenado Capilar
	var ckbLlenadoCapilar = "";
	var checkboxLlenadoCapilar = document.getElementsByName("llenado_capilar");
		for (var x = 0; x < checkboxLlenadoCapilar.length; x ++) {
			if(checkboxLlenadoCapilar[x].checked) {
				ckbLlenadoCapilar=checkboxLlenadoCapilar[x].id;
			}                               
		}
		
	//Checkbox Distension Abdominal
	var ckbDistensionAbdominal = "";
	var checkboxDistensionAbdominal = document.getElementsByName("distension_abdominal");
		for (var x = 0; x < checkboxDistensionAbdominal.length; x ++) {
			if(checkboxDistensionAbdominal[x].checked) {
				ckbDistensionAbdominal=checkboxDistensionAbdominal[x].id;
			}                               
		}
		
	//Checkbox Fontanela Abombada
	var ckbFontanelaAbombada = "";
	var checkboxFontanelaAbombada = document.getElementsByName("fontanela_abombada");
		for (var x = 0; x < checkboxFontanelaAbombada.length; x ++) {
			if(checkboxFontanelaAbombada[x].checked) {
				ckbFontanelaAbombada=checkboxFontanelaAbombada[x].id;
			}                               
		}
		
	//Checkbox Ojos Hundidos
	var ckbOjosHundidos = "";
	var checkboxOjosHundidos = document.getElementsByName("ojos_hundidos");
		for (var x = 0; x < checkboxOjosHundidos.length; x ++) {
			if(checkboxOjosHundidos[x].checked) {
				ckbOjosHundidos=checkboxOjosHundidos[x].id;
			}                               
		}
		
	var estadoGeneral=document.getElementById("estado_general").value;
	var pliegueCutaneo=document.getElementById("pliegue_cutaneo").value;

		/*////////////////RESPUESTAS ENFERMEDAD GRAVE O INFECCION LOCAL///////////////////////////////*/
	//Checkbox Enfermedad Grave
	var ckbEnfermedadGrave = "";
	var checkboxEnfermedadGrave = document.getElementsByName("enfermedad_grave");
		for (var x = 0; x < checkboxEnfermedadGrave.length; x ++) {
			if(checkboxEnfermedadGrave[x].checked) {
				ckbEnfermedadGrave=checkboxEnfermedadGrave[x].id;
			}                               
		}
		
	//Checkbox Infeccion Local
	var ckbInfeccionLocal = "";
	var checkboxInfeccionLocal = document.getElementsByName("infeccion_local");
		for (var x = 0; x < checkboxInfeccionLocal.length; x ++) {
			if(checkboxInfeccionLocal[x].checked) {
				ckbInfeccionLocal=checkboxInfeccionLocal[x].id;
			}                               
		}
		
	//Checkbox No Enfermedad Grave No Infeccion Local
	var ckbNoEnfNoInf = "";
	var checkboxNoEnfNoInf = document.getElementsByName("no_enf_grave_no_inf_local");
		for (var x = 0; x < checkboxNoEnfNoInf.length; x ++) {
			if(checkboxNoEnfNoInf[x].checked) {
				ckbNoEnfNoInf=checkboxNoEnfNoInf[x].id;
			}                               
		}
		
	//Checkbox Deshidratacion
	var ckbDeshidratacion = "";
	var checkboxDeshidratacion = document.getElementsByName("deshidratacion");
		for (var x = 0; x < checkboxDeshidratacion.length; x ++) {
			if(checkboxDeshidratacion[x].checked) {
				ckbDeshidratacion=checkboxDeshidratacion[x].id;
			}                               
		}
		
	//Checkbox No Deshidratacion
	var ckbNoDeshidratacion = "";
	var checkboxNoDeshidratacion = document.getElementsByName("no_deshidratacion");
		for (var x = 0; x < checkboxNoDeshidratacion.length; x ++) {
			if(checkboxNoDeshidratacion[x].checked) {
				ckbNoDeshidratacion=checkboxNoDeshidratacion[x].id;
			}                               
		}
		
	//Checkbox Diarrea Prolongada
	var ckbDiarreaProlongada = "";
	var checkboxDiarreaProlongada = document.getElementsByName("diarrea_prolongada");
		for (var x = 0; x < checkboxDiarreaProlongada.length; x ++) {
			if(checkboxDiarreaProlongada[x].checked) {
				ckbDiarreaProlongada=checkboxDiarreaProlongada[x].id;
			}                               
		}
		
	//Checkbox Diarrea Sangre
	var ckbDiarreaSangre = "";
	var checkboxDiarreaSangre = document.getElementsByName("diarrea_sangre");
		for (var x = 0; x < checkboxDiarreaSangre.length; x ++) {
			if(checkboxDiarreaSangre[x].checked) {
				ckbDiarreaSangre=checkboxDiarreaSangre[x].id;
			}                               
		}	
	
	/******************************************************************************************************/
	/*************************************CRECIMIENTO Y PRÁCTICAS DE ALIMENTACIÓN**************************/
	/******************************************************************************************************/
	//Radio Dificultad Alimentarse
	var radDificultadAlimentarse = "";
	var radioDificultadAlimentarse = document.getElementsByName("rad_dificultad_alimentarse");
		for (var x = 0; x < radioDificultadAlimentarse.length; x ++) {
			if(radioDificultadAlimentarse[x].checked) {
				radDificultadAlimentarse=radioDificultadAlimentarse[x].id;
			}                               
		}
	if (radDificultadAlimentarse==false) {
		alert("Seleccione si tiene alguna dificultad para alimentarse, en la Seccion de Verificar Crecimiento");
	} else {
		
	var cualDifAlimentarse=document.getElementById("cual_dificultad_alimentarse").value;
	
	//Radio Dejado Comer
	var radDejadoComer = "";
	var radioDejadoComer = document.getElementsByName("rad_ha_dejado_comer");
		for (var x = 0; x < radioDejadoComer.length; x ++) {
			if(radioDejadoComer[x].checked) {
				radDejadoComer=radioDejadoComer[x].id;
			}                               
		}
	if (radDejadoComer==false) {
		alert("Seleccione si ha dejado de comer, en la Seccion de Verificar Crecimiento");
	} else {
		
	var desdeCuandoDejadoComer=document.getElementById("desde_cuando_ha_dejado_comer").value;
	
	//Radio Leche Materna
	var radLecheMaterna = "";
	var radioLecheMaterna = document.getElementsByName("rad_leche_materna");
		for (var x = 0; x < radioLecheMaterna.length; x ++) {
			if(radioLecheMaterna[x].checked) {
				radLecheMaterna=radioLecheMaterna[x].id;
			}                               
		}
	if (radLecheMaterna==false) {
		alert("Seleccione si se alimenta con leche materna, en la Seccion de Verificar Crecimiento");
	} else {
	
	var formaExclusiva=document.getElementById("forma_exclusiva").value;
	var cuantasVecesFormaExclusiva=document.getElementById("cuantas_veces_forma_exclusiva").value;
	
	//Radio Utiliza Chupo
	var radUtilizaChupo = "";
	var radioUtilizaChupo = document.getElementsByName("rad_utiliza_chupo");
		for (var x = 0; x < radioUtilizaChupo.length; x ++) {
			if(radioUtilizaChupo[x].checked) {
				radUtilizaChupo=radioUtilizaChupo[x].id;
			}                               
		}
	if (radUtilizaChupo==false) {
		alert("Seleccione si utiliza chupo, en la Seccion de Verificar Crecimiento");
	} else {
	
	//Radio Otra Leche
	var radOtraLeche = "";
	var radioOtraLeche = document.getElementsByName("rad_otra_leche");
		for (var x = 0; x < radioOtraLeche.length; x ++) {
			if(radioOtraLeche[x].checked) {
				radOtraLeche=radioOtraLeche[x].id;
			}                               
		}
	if (radOtraLeche==false) {
		alert("Seleccione si recibe otra leche, otro aliemnto o bebidas, en la Seccion de Verificar Crecimiento");
	} else {
		
	var cualesFrecuenciaOtraLeche=document.getElementById("cuales_frecuencia_otra_leche").value;
	var preparaOtraLeche=document.getElementById("prepara_otra_leche").value;
	var queUtilizaAlimentarlo=document.getElementById("que_utiliza_alimentarlo").value;
	var pesoEdad=document.getElementById("peso_edad").value;
	if (pesoEdad=="") {
		alert("Escriba el peso para la edad");
		document.getElementById("peso_edad").focus();
	} else {

	var pesoTalla=document.getElementById("peso_talla").value;
	if (pesoTalla=="") {
		alert("Escriba el peso para la talla");
		document.getElementById("peso_talla").focus();
	} else {

	var tendenciaPeso=document.getElementById("tendencia_peso").value;
	if (tendenciaPeso=="Seleccione") {
		alert("Elija la tendencia para el peso");
		document.getElementById("tendencia_peso").focus();
	} else {
	
	//Checkbox Boca Abierta
	var ckbBocaAbierta = "";
	var checkboxBocaAbierta = document.getElementsByName("boca_abierta");
		for (var x = 0; x < checkboxBocaAbierta.length; x ++) {
			if(checkboxBocaAbierta[x].checked) {
				ckbBocaAbierta=checkboxBocaAbierta[x].id;
			}                               
		}	
		
	//Checkbox Toca Seno
	var ckbTocaSeno = "";
	var checkboxTocaSeno = document.getElementsByName("toca_seno");
		for (var x = 0; x < checkboxTocaSeno.length; x ++) {
			if(checkboxTocaSeno[x].checked) {
				ckbTocaSeno=checkboxTocaSeno[x].id;
			}                               
		}	
		
	//Checkbox Labio Inferior
	var ckbLabioInferior = "";
	var checkboxLabioInferior = document.getElementsByName("labio_inferior");
		for (var x = 0; x < checkboxLabioInferior.length; x ++) {
			if(checkboxLabioInferior[x].checked) {
				ckbLabioInferior=checkboxLabioInferior[x].id;
			}                               
		}
		
	//Checkbox Areola Labio
	var ckbAreolaLabio = "";
	var checkboxAreolaLabio = document.getElementsByName("areola_labio");
		for (var x = 0; x < checkboxAreolaLabio.length; x ++) {
			if(checkboxAreolaLabio[x].checked) {
				ckbAreolaLabio=checkboxAreolaLabio[x].id;
			}                               
		}
		
	//Checkbox Cabeza Cuerpo Derecho
	var ckbCabCuerpoDerecho = "";
	var checkboxCabCuerpoDerecho = document.getElementsByName("cab_cuerpo_derecho");
		for (var x = 0; x < checkboxCabCuerpoDerecho.length; x ++) {
			if(checkboxCabCuerpoDerecho[x].checked) {
				ckbCabCuerpoDerecho=checkboxCabCuerpoDerecho[x].id;
			}                               
		}
		
	//Checkbox Direccion Pezon
	var ckbDireccionPezon = "";
	var checkboxDireccionPezon = document.getElementsByName("direccion_pezon");
		for (var x = 0; x < checkboxDireccionPezon.length; x ++) {
			if(checkboxDireccionPezon[x].checked) {
				ckbDireccionPezon=checkboxDireccionPezon[x].id;
			}                               
		}
		
	//Checkbox Hijo Frente Madre
	var ckbHijoFrenteMadre = "";
	var checkboxHijoFrenteMadre = document.getElementsByName("hijo_frente_madre");
		for (var x = 0; x < checkboxHijoFrenteMadre.length; x ++) {
			if(checkboxHijoFrenteMadre[x].checked) {
				ckbHijoFrenteMadre=checkboxHijoFrenteMadre[x].id;
			}                               
		}
		
	//Checkbox Madre Sostiene Cuerpo
	var ckbMadreSostieneCuerpo = "";
	var checkboxMadreSostieneCuerpo = document.getElementsByName("madre_sostiene_cuerpo");
		for (var x = 0; x < checkboxMadreSostieneCuerpo.length; x ++) {
			if(checkboxMadreSostieneCuerpo[x].checked) {
				ckbMadreSostieneCuerpo=checkboxMadreSostieneCuerpo[x].id;
			}                               
		}
		
	//Checkbox Lenta Profunda
	var ckbLentaProfunda = "";
	var checkboxLentaProfunda = document.getElementsByName("lenta_profunda");
		for (var x = 0; x < checkboxLentaProfunda.length; x ++) {
			if(checkboxLentaProfunda[x].checked) {
				ckbLentaProfunda=checkboxLentaProfunda[x].id;
			}                               
		}
	
		/*////////////////RESPUESTAS ALIMENTACION///////////////////////////////*/
	//Checkbox Problema Severo Alimentacion
	var ckbProbSeveroAlimentacion = "";
	var checkboxProbSeveroAlimentacion = document.getElementsByName("prob_severo_alimentacion");
		for (var x = 0; x < checkboxProbSeveroAlimentacion.length; x ++) {
			if(checkboxProbSeveroAlimentacion[x].checked) {
				ckbProbSeveroAlimentacion=checkboxProbSeveroAlimentacion[x].id;
			}                               
		}	
		
	//Checkbox Peso Muy Bajo
	var ckbPesoMuyBajo = "";
	var checkboxPesoMuyBajo = document.getElementsByName("peso_muy_bajo");
		for (var x = 0; x < checkboxPesoMuyBajo.length; x ++) {
			if(checkboxPesoMuyBajo[x].checked) {
				ckbPesoMuyBajo=checkboxPesoMuyBajo[x].id;
			}                               
		}
		
	//Checkbox Problemas Alimentacion
	var ckbProblemasAlimentacion = "";
	var checkboxProblemasAlimentacion = document.getElementsByName("problemas_alimentacion");
		for (var x = 0; x < checkboxProblemasAlimentacion.length; x ++) {
			if(checkboxProblemasAlimentacion[x].checked) {
				ckbProblemasAlimentacion=checkboxProblemasAlimentacion[x].id;
			}                               
		}
		
	//Checkbox Peso Bajo
	var ckbPesoBajo = "";
	var checkboxPesoBajo = document.getElementsByName("peso_bajo");
		for (var x = 0; x < checkboxPesoBajo.length; x ++) {
			if(checkboxPesoBajo[x].checked) {
				ckbPesoBajo=checkboxPesoBajo[x].id;
			}                               
		}
		
	//Checkbox Practicas Alimentacion
	var ckbPracticasAlimentacion = "";
	var checkboxPracticasAlimentacion = document.getElementsByName("practicas_alimentacion");
		for (var x = 0; x < checkboxPracticasAlimentacion.length; x ++) {
			if(checkboxPracticasAlimentacion[x].checked) {
				ckbPracticasAlimentacion=checkboxPracticasAlimentacion[x].id;
			}                               
		}
		
	/******************************************************************************************************/
	/*************************************VERIFICAR DESARROLLO*********************************************/
	/******************************************************************************************************/
	//Radio Parientes Padres
	var radParientesPadres = "";
	var radioParientesPadres = document.getElementsByName("rad_parientes_padres");
		for (var x = 0; x < radioParientesPadres.length; x ++) {
			if(radioParientesPadres[x].checked) {
				radParientesPadres=radioParientesPadres[x].id;
			}                               
		}
	if (radParientesPadres==false) {
		alert("Seleccione si son parientes los padres, en la Seccion de Verificar Problema Desarrollo");
	} else {
		
	//Radio Familiar Problema Mental
	var radFamProblemaMental = "";
	var radioFamProblemaMental = document.getElementsByName("rad_familiar_problema_mental");
		for (var x = 0; x < radioFamProblemaMental.length; x ++) {
			if(radioFamProblemaMental[x].checked) {
				radFamProblemaMental=radioFamProblemaMental[x].id;
			}                               
		}
	if (radFamProblemaMental==false) {
		alert("Seleccione si hay un familiar con problema mental o fisico, en la Seccion de Verificar Problema Desarrollo");
	} else {
		
	var quienCuidaNino=document.getElementById("quien_cuida_nino").value;
	if (quienCuidaNino=="") {
		alert("EScriba quien cuida al niño");
		document.getElementById("quien_cuida_nino").focus();
	} else {
	
	var desarrolloNino=document.getElementById("desarrollo_nino").value;
	if (desarrolloNino=="") {
		alert("Escriba como ve el desarrollo del niño");
		document.getElementById("desarrollo_nino").focus();
	} else {
	
	var antNatales=document.getElementById("ant_natales").value;
	if (antNatales=="") {
		alert("Escriba los antecedentes prenatales, natales y postnatales");
		document.getElementById("ant_natales").focus();
	} else {

	var alteracionFenitipica=document.getElementById("alteracion_fenitipica").value;
	if (alteracionFenitipica=="") {
		alert("Escriba la alteracion fenitipica");
		document.getElementById("alteracion_fenitipica").focus();
	} else {
	
	var PC=document.getElementById("pc").value;
	if (PC=="") {
		alert("Escriba el perimetro cefalico, en la seccion Desarrollo");
		document.getElementById("pc").focus();
	} else {
	
	var PCE=document.getElementById("pc_e").value;
	if (PCE=="") {
		alert("Escriba el perimetro cefalico para la edad, en la seccion Desarrollo");
		document.getElementById("pc_e").focus();
	} else {
	
	//Checkbox Reflejo Moro
	var ckbReflejoMoro = "";
	var checkboxReflejoMoro = document.getElementsByName("reflejo_moro");
		for (var x = 0; x < checkboxReflejoMoro.length; x ++) {
			if(checkboxReflejoMoro[x].checked) {
				ckbReflejoMoro=checkboxReflejoMoro[x].id;
			}                               
		}	
		
	//Checkbox Reflejo Succion
	var ckbReflejoSuccion = "";
	var checkboxReflejoSuccion = document.getElementsByName("reflejo_succion");
		for (var x = 0; x < checkboxReflejoSuccion.length; x ++) {
			if(checkboxReflejoSuccion[x].checked) {
				ckbReflejoSuccion=checkboxReflejoSuccion[x].id;
			}                               
		}	
		
	//Checkbox Reflejo Cocleo
	var ckbReflejoCocleo = "";
	var checkboxReflejoCocleo = document.getElementsByName("reflejo_cocleo");
		for (var x = 0; x < checkboxReflejoCocleo.length; x ++) {
			if(checkboxReflejoCocleo[x].checked) {
				ckbReflejoCocleo=checkboxReflejoCocleo[x].id;
			}                               
		}
		
	//Checkbox Manos Cerradas
	var ckbManosCerradas = "";
	var checkboxManosCerradas = document.getElementsByName("manos_cerradas");
		for (var x = 0; x < checkboxManosCerradas.length; x ++) {
			if(checkboxManosCerradas[x].checked) {
				ckbManosCerradas=checkboxManosCerradas[x].id;
			}                               
		}
		
	//Checkbox Brazos Piernas Flexionadas
	var ckbBraPierFlexionadas = "";
	var checkboxBraPierFlexionadas = document.getElementsByName("brazos_piernas_flexionadas");
		for (var x = 0; x < checkboxBraPierFlexionadas.length; x ++) {
			if(checkboxBraPierFlexionadas[x].checked) {
				ckbBraPierFlexionadas=checkboxBraPierFlexionadas[x].id;
			}                               
		}
		
	//Checkbox Vocaliza
	var ckbVocaliza = "";
	var checkboxVocaliza = document.getElementsByName("vocaliza");
		for (var x = 0; x < checkboxVocaliza.length; x ++) {
			if(checkboxVocaliza[x].checked) {
				ckbVocaliza=checkboxVocaliza[x].id;
			}                               
		}
		
	//Checkbox Sonrisa Social
	var ckbSonrisaSocial = "";
	var checkboxSonrisaSocial = document.getElementsByName("sonrisa_social");
		for (var x = 0; x < checkboxSonrisaSocial.length; x ++) {
			if(checkboxSonrisaSocial[x].checked) {
				ckbSonrisaSocial=checkboxSonrisaSocial[x].id;
			}                               
		}
		
	//Checkbox Movimiento Piernas
	var ckbMovimientoPiernas = "";
	var checkboxMovimientoPiernas = document.getElementsByName("movimiento_piernas");
		for (var x = 0; x < checkboxMovimientoPiernas.length; x ++) {
			if(checkboxMovimientoPiernas[x].checked) {
				ckbMovimientoPiernas=checkboxMovimientoPiernas[x].id;
			}                               
		}
		
	//Checkbox Sigue Objetos
	var ckbSigueObjetos = "";
	var checkboxSigueObjetos = document.getElementsByName("sigue_objetos");
		for (var x = 0; x < checkboxSigueObjetos.length; x ++) {
			if(checkboxSigueObjetos[x].checked) {
				ckbSigueObjetos=checkboxSigueObjetos[x].id;
			}                               
		}
	
		/*////////////////RESPUESTAS DESARROLLO///////////////////////////////*/
	//Checkbox Retraso Desarrollo
	var ckbRetrasoDesarrollo = "";
	var checkboxRetrasoDesarrollo = document.getElementsByName("retraso_desarrollo");
		for (var x = 0; x < checkboxRetrasoDesarrollo.length; x ++) {
			if(checkboxRetrasoDesarrollo[x].checked) {
				ckbRetrasoDesarrollo=checkboxRetrasoDesarrollo[x].id;
			}                               
		}
		
	//Checkbox Riesgo Problema
	var ckbRiesgoProblema = "";
	var checkboxRiesgoProblema = document.getElementsByName("riesgo_problema");
		for (var x = 0; x < checkboxRiesgoProblema.length; x ++) {
			if(checkboxRiesgoProblema[x].checked) {
				ckbRiesgoProblema=checkboxRiesgoProblema[x].id;
			}                               
		}
		
	//Checkbox Desarrollo Normal
	var ckbDesarrolloNormal = "";
	var checkboxDesarrolloNormal = document.getElementsByName("desarrollo_normal");
		for (var x = 0; x < checkboxDesarrolloNormal.length; x ++) {
			if(checkboxDesarrolloNormal[x].checked) {
				ckbDesarrolloNormal=checkboxDesarrolloNormal[x].id;
			}                               
		}
		
	/******************************************************************************************************/
	/*************************************DIAGNOSTICOS Y RECOMENDACIONES***********************************/
	/******************************************************************************************************/
	var completarExamenFisico=document.getElementById("completar_examen_fisico").value;
	//var diagnosticoExamenFisico=document.getElementById("diagnostico_examen_fisico").value;
	//var diagnosticoPac=document.getElementById("txtNomDiagnos").value;
	//var codigoCiePac=document.getElementById("txtCodDiagnostico").value;
	var tratarPac=document.getElementById("tratar_pac").value;
	var volverInmediato=document.getElementById("volver_inmediato").value;
	var recienNacido=document.getElementById("recien_nacido").value;
	var madre=document.getElementById("madre").value;
	var ninoSano=document.getElementById("nino_sano").value;
	var referidoConsulta=document.getElementById("referido_consulta").value;
	var programaVacunacion=document.getElementById("programa_vacunacion").value;
	var recomendacionesBuenTrato=document.getElementById("recomendaciones_buen_trato").value;
	var otrasRecomendaciones=document.getElementById("otras_recomendaciones").value;
	
	var CodReporte="";	
	
			ajax=getXMLObject();
			ajax.open("POST","AiepiMultiplePacientes",true);

			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					CodReporte=document.getElementById("txtCodReporte").value=ajax.responseText;
					//activarelementoFormulario("BotonAntecedentes");
					alert("Ingreso Exitoso");
					mostrarInformesAIEPI0a2(CodReporte);
					window.location.reload();
					//RedireccionFormatoCex(CodPaciente);
					//window.location="hic_SeleccionarPacientes.jsp?pacientes="+CodPaciente;
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=2.1&motConsulta=" + motConsulta
					+ "&CodPaciente=" + CodPaciente
					+ "&CodUsuario=" +  CodUsuario
					+ "&CodAdmision=" + CodAdmision
/*Encabezado*/		+ "&antEmbarazo=" + antEmbarazo
					+ "&pesoNacer=" + pesoNacer
					+ "&tallaNacer=" + tallaNacer
					+ "&edadGestacional=" + edadGestacional
					+ "&hemoclasificacion=" + hemoclasificacion
					+ "&pesoActual=" + pesoActual
					+ "&talla=" + talla
					+ "&perimetroCefalico=" + perimetroCefalico
					+ "&frecuenciaCardiaca=" + frecuenciaCardiaca
					+ "&frecuenciaRespiratoria=" + frecuenciaRespiratoria
					+ "&temperatura=" + temperatura
					+ "&centigrados=" + centigrados
/*Enfermedad*/		+ "&radBebePecho=" + radBebePecho
					+ "&radFiebre=" + radFiebre
					+ "&radHipotermia=" + radHipotermia
					+ "&radConvulsiones=" + radConvulsiones
					+ "&radVomito=" + radVomito
					+ "&vomitaTodo=" + vomitaTodo
					+ "&radDiarrea=" + radDiarrea
					+ "&cuandoDiarrea=" + cuandoDiarrea
					+ "&radDificultadRespirar=" + radDificultadRespirar
					+ "&expliqueDificultadRespirar=" + expliqueDificultadRespirar
					+ "&radSangreHeces=" + radSangreHeces
					+ "&panalesOrinados=" + panalesOrinados
/*Ckb Enfermedad*/	+ "&ckbMueveSolo=" + ckbMueveSolo
					+ "&ckbLetargico=" + ckbLetargico
					+ "&ckbLuceMal=" + ckbLuceMal
					+ "&ckbIrritable=" + ckbIrritable
					+ "&ckbPalidez=" + ckbPalidez
					+ "&ckbCianosis=" + ckbCianosis
					+ "&ckbApneas=" + ckbApneas
					+ "&ckbBilirrubinas=" + ckbBilirrubinas
					+ "&ckbEstridor=" + ckbEstridor
					+ "&ckbFR=" + ckbFR
					+ "&ckbAleteoNasal=" + ckbAleteoNasal
					+ "&ckbQuejido=" + ckbQuejido
					+ "&ckbSibilancia=" + ckbSibilancia
					+ "&ckbFC=" + ckbFC
					+ "&ckbSupuracionOido=" + ckbSupuracionOido
					+ "&ckbTirajeSubcostal=" + ckbTirajeSubcostal
					+ "&ckbSecPurulentaConjuntival=" + ckbSecPurulentaConjuntival
					+ "&ckbEdemaPalpebral=" + ckbEdemaPalpebral
					+ "&ckbEritemaPeriumbilical=" + ckbEritemaPeriumbilical
					+ "&pustulasPiel=" + pustulasPiel
					+ "&ckbSecPurulentaOmbligo=" + ckbSecPurulentaOmbligo
					+ "&ckbEquimosis=" + ckbEquimosis
					+ "&ckbPetequias=" + ckbPetequias
					+ "&ckbPlacasBlanquecinas=" + ckbPlacasBlanquecinas
					+ "&ckbHemorragia=" + ckbHemorragia
					+ "&ckbLlenadoCapilar=" + ckbLlenadoCapilar
					+ "&ckbDistensionAbdominal=" + ckbDistensionAbdominal
					+ "&ckbFontanelaAbombada=" + ckbFontanelaAbombada
					+ "&ckbOjosHundidos=" + ckbOjosHundidos
					+ "&estadoGeneral=" + estadoGeneral
					+ "&pliegueCutaneo=" + pliegueCutaneo
/*Resp Enfermedad*/	+ "&ckbEnfermedadGrave=" + ckbEnfermedadGrave
					+ "&ckbInfeccionLocal=" + ckbInfeccionLocal
					+ "&ckbNoEnfNoInf=" + ckbNoEnfNoInf
					+ "&ckbDeshidratacion=" + ckbDeshidratacion
					+ "&ckbNoDeshidratacion=" + ckbNoDeshidratacion
					+ "&ckbDiarreaProlongada=" + ckbDiarreaProlongada
					+ "&ckbDiarreaSangre=" + ckbDiarreaSangre
/*Alimentacion*/	+ "&radDificultadAlimentarse=" + radDificultadAlimentarse
					+ "&cualDifAlimentarse=" + cualDifAlimentarse
					+ "&radDejadoComer=" + radDejadoComer
					+ "&desdeCuandoDejadoComer=" + desdeCuandoDejadoComer
					+ "&radLecheMaterna=" + radLecheMaterna
					+ "&formaExclusiva=" + formaExclusiva
					+ "&cuantasVecesFormaExclusiva=" + cuantasVecesFormaExclusiva
					+ "&radUtilizaChupo=" + radUtilizaChupo
					+ "&radOtraLeche=" + radOtraLeche
					+ "&cualesFrecuenciaOtraLeche=" + cualesFrecuenciaOtraLeche
					+ "&preparaOtraLeche=" + preparaOtraLeche
					+ "&queUtilizaAlimentarlo=" + queUtilizaAlimentarlo
					+ "&pesoEdad=" + pesoEdad
					+ "&pesoTalla=" + pesoTalla
					+ "&tendenciaPeso=" + tendenciaPeso
/*Ckb Alimentacion*/+ "&ckbBocaAbierta=" + ckbBocaAbierta
					+ "&ckbTocaSeno=" + ckbTocaSeno
					+ "&ckbLabioInferior=" + ckbLabioInferior
					+ "&ckbAreolaLabio=" + ckbAreolaLabio
					+ "&ckbCabCuerpoDerecho=" + ckbCabCuerpoDerecho
					+ "&ckbDireccionPezon=" + ckbDireccionPezon
					+ "&ckbHijoFrenteMadre=" + ckbHijoFrenteMadre
					+ "&ckbMadreSostieneCuerpo=" + ckbMadreSostieneCuerpo
					+ "&ckbLentaProfunda=" + ckbLentaProfunda
/*Resp Alimentacio*/+ "&ckbProbSeveroAlimentacion=" + ckbProbSeveroAlimentacion
					+ "&ckbPesoMuyBajo=" + ckbPesoMuyBajo
					+ "&ckbProblemasAlimentacion=" + ckbProblemasAlimentacion
					+ "&ckbPesoBajo=" + ckbPesoBajo
					+ "&ckbPracticasAlimentacion=" + ckbPracticasAlimentacion					
/*Desarrollo*/		+ "&radParientesPadres=" + radParientesPadres
					+ "&radFamProblemaMental=" + radFamProblemaMental
					+ "&quienCuidaNino=" + quienCuidaNino
					+ "&desarrolloNino=" + desarrolloNino
					+ "&antNatales=" + antNatales
					+ "&alteracionFenitipica=" + alteracionFenitipica
					+ "&PC=" + PC
					+ "&PCE=" + PCE
/*Ckb Desarrollo*/	+ "&ckbReflejoMoro=" + ckbReflejoMoro
					+ "&ckbReflejoSuccion=" + ckbReflejoSuccion
					+ "&ckbReflejoCocleo=" + ckbReflejoCocleo
					+ "&ckbManosCerradas=" + ckbManosCerradas
					+ "&ckbBraPierFlexionadas=" + ckbBraPierFlexionadas
					+ "&ckbVocaliza=" + ckbVocaliza
					+ "&ckbSonrisaSocial=" + ckbSonrisaSocial
					+ "&ckbMovimientoPiernas=" + ckbMovimientoPiernas
					+ "&ckbSigueObjetos=" + ckbSigueObjetos
/*Resp Desarrollo*/	+ "&ckbRetrasoDesarrollo=" + ckbRetrasoDesarrollo
					+ "&ckbRiesgoProblema=" + ckbRiesgoProblema
					+ "&ckbDesarrolloNormal=" + ckbDesarrolloNormal
/*Recomendaciones*/	+ "&completarExamenFisico=" + completarExamenFisico
					//+ "&diagnosticoExamenFisico=" + diagnosticoExamenFisico
					//+ "&diagnosticoPac=" + diagnosticoPac
					//+ "&codigoCiePac=" + codigoCiePac
					+ "&tratarPac=" + tratarPac
					+ "&volverInmediato=" + volverInmediato
					+ "&recienNacido=" + recienNacido
					+ "&madre=" + madre
					+ "&ninoSano=" + ninoSano
					+ "&referidoConsulta=" + referidoConsulta
					+ "&programaVacunacion=" + programaVacunacion
					+ "&recomendacionesBuenTrato=" + recomendacionesBuenTrato
					+ "&otrasRecomendaciones=" + otrasRecomendaciones); //Posting txtname to Servlet*/	
			}//Mot Consulta
			}//Ant Embarazo
			}//Peso Nacer
			}//Talla Nacer
			}//Edad Gestacional
			}//Hemoclasificacion
			}//Peso Actual
			}//Talla
			}//Perimetro Cefalico
			}//Frecuancia Cardiaca
			}//Frecuencia Respiratoria
			}//Temperatura
			}//Centigrados
			}//Rad Bebe Pecho
			}//Rad Fiebre
			}//Rad Hipotermia
			}//Rad Convulsiones
			}//Rad Vomito
			}//Rad Diarrea
			}//Rad Dificultad Respirar
			}//Rad Sangre Heces
			}//Pañales Orinados
			}//Rad Dificultad Alimentarse
			}//Rad Dejado Comer
			}//Rad Leche Materna
			}//Rad Utiliza Chupo
			}//Rad Otra Leche
			}//Peso Edad
			}//Peso Talla
			}//Tendencia Peso
			}//Rad Parientes Padres
			}//Rad Familiar Problema Mental
			}//Quien Cuida Nino
			}//Desarrollo Niño
			}//Ant Natales
			}//Alteracion Fenitipica
			}//PC
			}//PCE
}
	/**********************************************************************/
	/**************************FIN GUARDAR INFORME 0-2 AIEPI***************/
	/**********************************************************************/

	/**********************************************************************/
	/**************************MOSTRAR INFORME 0-2 AIEPI*******************/
	/**********************************************************************/
function mostrarInformesAIEPI0a2(idcodInforme){
	pagina="aiepi_reporte0a2.jsp?CodInfPYPPlanFam="+idcodInforme;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Informe_Aiepi_0a2",opciones);
}
	/**********************************************************************/
	/**************************FIN MOSTRAR INFORME 0-2 AIEPI***************/
	/**********************************************************************/



	/***********************************************************************/
	/***************************GUARDAR INFORME 2-5 AIEPI*******************/
	/***********************************************************************/
function GuardarAiepiDosMesesCincoAnosCex(){	

	var CodPaciente=document.getElementById("CodPac").value;
	var CodUsuario=document.getElementById("txtCodusuario").value;
	var CodAdmision=document.getElementById("CodAdmision").value;
	/******************************************************************************************************/
	/*************************************ANTECEDENTES*****************************************************/
	/******************************************************************************************************/
	var motiConsulta=document.getElementById("mot_consulta").value;
	if (motiConsulta=="") {
		alert("Escriba el motivo de la consulta");
		document.getElementById("mot_consulta").focus();
	} else {

	var anteNatales=document.getElementById("ant_natales").value;
	if (anteNatales=="") {
		alert("Escriba los antecedentes prenatales, natales y postnatales");
		document.getElementById("ant_natales").focus();
	} else {

	var APF=document.getElementById("apf").value;
	if (APF=="") {
		alert("Escriba los antecedentes patologicos familiares");
		document.getElementById("apf").focus();
	} else {

	var temperaturas=document.getElementById("temperatura").value;
	if (temperaturas=="") {
		alert("Escriba la temperatura");
		document.getElementById("temperatura").focus();
	} else {

	var frecuenciaCardiacas=document.getElementById("frecuencia_cardiaca").value;
	if (frecuenciaCardiacas=="") {
		alert("Escriba la frecuencia cardiaca");
		document.getElementById("frecuencia_cardiaca").focus();
	} else {

	var frecuenciaRespiratorias=document.getElementById("frecuencia_respiratoria").value;
	if (frecuenciaRespiratorias=="") {
		alert("Escriba la frecuencia respiratoria");
		document.getElementById("frecuencia_respiratoria").focus();
	} else {

	var tallas=document.getElementById("talla").value;
	if (tallas=="") {
		alert("Escriba la talla");
		document.getElementById("talla").focus();
	} else {

	var peso=document.getElementById("peso").value;
	if (peso=="") {
		alert("Escriba el peso");
		document.getElementById("peso").focus();
	} else {

	var perimetroCefalicos=document.getElementById("perimetro_cefalico").value;
	if (perimetroCefalicos=="") {
		alert("Escriba el perimetro cefalico");
		document.getElementById("perimetro_cefalico").focus();
	} else {

	var IMC=document.getElementById("imc").value;
	if (IMC=="") {
		alert("Escriba el IMC");
		document.getElementById("imc").focus();
	} else {
	
	/******************************************************************************************************/
	/*************************************SIGNOS DE PELIGRO************************************************/
	/******************************************************************************************************/
	//Checkbox No Bebe Pecho
	var ckbNoBebePecho = "";
	var checkboxNoBebePecho = document.getElementsByName("no_bebe_pecho");
		for (var x = 0; x < checkboxNoBebePecho.length; x ++) {
			if(checkboxNoBebePecho[x].checked) {
				ckbNoBebePecho=checkboxNoBebePecho[x].id;
			}                               
		}
	
	//Checkbox Letargico
	var ckbxLetargico = "";
	var checkboxLetargico = document.getElementsByName("letargico");
		for (var x = 0; x < checkboxLetargico.length; x ++) {
			if(checkboxLetargico[x].checked) {
				ckbxLetargico=checkboxLetargico[x].id;
			}                               
		}
		
	//Checkbox Vomita Todo
	var ckbVomitaTodo = "";
	var checkboxVomitaTodo = document.getElementsByName("vomita_todo");
		for (var x = 0; x < checkboxVomitaTodo.length; x ++) {
			if(checkboxVomitaTodo[x].checked) {
				ckbVomitaTodo=checkboxVomitaTodo[x].id;
			}                               
		}
			
	//Checkbox Convulsiones
	var ckbConvulsiones = "";
	var checkboxConvulsiones = document.getElementsByName("convulsiones");
		for (var x = 0; x < checkboxConvulsiones.length; x ++) {
			if(checkboxConvulsiones[x].checked) {
				ckbConvulsiones=checkboxConvulsiones[x].id;
			}                               
		}
		
	var observacionesSignosPeligro=document.getElementById("obs_signos_peligro").value;
	
		/*////////////////RESPUESTAS SIGNOS PELIGRO///////////////////////////////*/
	//Checkbox Enfermedad Muy Grave
	var ckbEnfMuyGrave = "";
	var checkboxEnfMuyGrave = document.getElementsByName("enf_muy_grave");
		for (var x = 0; x < checkboxEnfMuyGrave.length; x ++) {
			if(checkboxEnfMuyGrave[x].checked) {
				ckbEnfMuyGrave=checkboxEnfMuyGrave[x].id;
			}                               
		}

	/******************************************************************************************************/
	/*************************************DIFICULTAD RESPIRAR**********************************************/
	/******************************************************************************************************/
	//Radio Dificultad Respirar
	var radiDificultadRespirar = "";
	var radioDificultadRespirar = document.getElementsByName("rad_dificultad_respirar");
		for (var x = 0; x < radioDificultadRespirar.length; x ++) {
			if(radioDificultadRespirar[x].checked) {
				radiDificultadRespirar=radioDificultadRespirar[x].id;
			}                               
		}
	if (radiDificultadRespirar==false) {
		alert("Seleccione si tiene tos o dificultad para respirar, en la Seccion Verificar Tos");
	} else {
		
	var desdeCuandoTos=document.getElementById("desde_cuando_tos").value;
	
	//Radio Episodio Sibilancias
	var radEpisodioSibilancias = "";
	var radioEpisodioSibilancias = document.getElementsByName("rad_episodio_sibilancias");
		for (var x = 0; x < radioEpisodioSibilancias.length; x ++) {
			if(radioEpisodioSibilancias[x].checked) {
				radEpisodioSibilancias=radioEpisodioSibilancias[x].id;
			}                               
		}
	
	//Radio Sibilancias Recurrentes
	var radSibilanciasRecurrentes = "";
	var radioSibilanciasRecurrentes = document.getElementsByName("rad_sibilancias_recurrentes");
		for (var x = 0; x < radioSibilanciasRecurrentes.length; x ++) {
			if(radioSibilanciasRecurrentes[x].checked) {
				radSibilanciasRecurrentes=radioSibilanciasRecurrentes[x].id;
			}                               
		}
	
	//Radio Cuadro Gripal
	var radCuadroGripal = "";
	var radioCuadroGripal = document.getElementsByName("rad_cuadro_gripal");
		for (var x = 0; x < radioCuadroGripal.length; x ++) {
			if(radioCuadroGripal[x].checked) {
				radCuadroGripal=radioCuadroGripal[x].id;
			}                               
		}
	
	//Radio Antecedentes Prematuridad
	var radAntecedentesPrematuridad = "";
	var radioAntecedentesPrematuridad = document.getElementsByName("rad_antecedentes_prematuridad");
		for (var x = 0; x < radioAntecedentesPrematuridad.length; x ++) {
			if(radioAntecedentesPrematuridad[x].checked) {
				radAntecedentesPrematuridad=radioAntecedentesPrematuridad[x].id;
			}                               
		}
		
	var respiracionesMinutos=document.getElementById("respiraciones_minutos").value;
		
	//Checkbox Respiracion Rapida
	var ckbRespiracionRapida = "";
	var checkboxRespiracionRapida = document.getElementsByName("respiracion_rapida");
		for (var x = 0; x < checkboxRespiracionRapida.length; x ++) {
			if(checkboxRespiracionRapida[x].checked) {
				ckbRespiracionRapida=checkboxRespiracionRapida[x].id;
			}                               
		}	
			
	//Checkbox Tiraje Subcostal
	var ckbxTirajeSubcostal = "";
	var checkboxTirajeSubcostal = document.getElementsByName("tiraje_subcostal");
		for (var x = 0; x < checkboxTirajeSubcostal.length; x ++) {
			if(checkboxTirajeSubcostal[x].checked) {
				ckbxTirajeSubcostal=checkboxTirajeSubcostal[x].id;
			}                               
		}
		
	//Checkbox Saturacion Oxigeno
	var ckbSaturacionOxigeno = "";
	var checkboxSaturacionOxigeno = document.getElementsByName("saturacion_oxigeno");
		for (var x = 0; x < checkboxSaturacionOxigeno.length; x ++) {
			if(checkboxSaturacionOxigeno[x].checked) {
				ckbSaturacionOxigeno=checkboxSaturacionOxigeno[x].id;
			}                               
		}
		
	//Checkbox Tiraje Supraclavicular
	var ckbTirajeSupraclavicular = "";
	var checkboxTirajeSupraclavicular = document.getElementsByName("tiraje_supraclavicular");
		for (var x = 0; x < checkboxTirajeSupraclavicular.length; x ++) {
			if(checkboxTirajeSupraclavicular[x].checked) {
				ckbTirajeSupraclavicular=checkboxTirajeSupraclavicular[x].id;
			}                               
		}
		
	//Checkbox Estridor
	var ckbxEstridor = "";
	var checkboxEstridor = document.getElementsByName("estridor");
		for (var x = 0; x < checkboxEstridor.length; x ++) {
			if(checkboxEstridor[x].checked) {
				ckbxEstridor=checkboxEstridor[x].id;
			}                               
		}
		
	//Checkbox Sibilancias
	var ckbSibilancias = "";
	var checkboxSibilancias = document.getElementsByName("sibilancias");
		for (var x = 0; x < checkboxSibilancias.length; x ++) {
			if(checkboxSibilancias[x].checked) {
				ckbSibilancias=checkboxSibilancias[x].id;
			}                               
		}
		
	//Checkbox Apnea 
	var ckbApnea = "";
	var checkboxApnea = document.getElementsByName("apnea");
		for (var x = 0; x < checkboxApnea.length; x ++) {
			if(checkboxApnea[x].checked) {
				ckbApnea=checkboxApnea[x].id;
			}                               
		}	
		
	//Checkbox Incapacidad Hablar
	var ckbIncapacidadHablar = "";
	var checkboxIncapacidadHablar = document.getElementsByName("incapacidad_hablar");
		for (var x = 0; x < checkboxIncapacidadHablar.length; x ++) {
			if(checkboxIncapacidadHablar[x].checked) {
				ckbIncapacidadHablar=checkboxIncapacidadHablar[x].id;
			}                               
		}
		
	//Checkbox Somnoliento
	var ckbSomnoliento = "";
	var checkboxSomnoliento = document.getElementsByName("somnoliento");
		for (var x = 0; x < checkboxSomnoliento.length; x ++) {
			if(checkboxSomnoliento[x].checked) {
				ckbSomnoliento=checkboxSomnoliento[x].id;
			}                               
		}
	
	//Checkbox Confuso
	var ckbConfuso = "";
	var checkboxConfuso = document.getElementsByName("confuso");
		for (var x = 0; x < checkboxConfuso.length; x ++) {
			if(checkboxConfuso[x].checked) {
				ckbConfuso=checkboxConfuso[x].id;
			}                               
		}
		
	//Checkbox Agitado
	var ckbAgitado = "";
	var checkboxAgitado = document.getElementsByName("agitado");
		for (var x = 0; x < checkboxAgitado.length; x ++) {
			if(checkboxAgitado[x].checked) {
				ckbAgitado=checkboxAgitado[x].id;
			}                               
		}
		
	var observacionesTos=document.getElementById("obs_tos").value;
	
		/*////////////////RESPUESTAS SIGNOS PELIGRO///////////////////////////////*/
	//Checkbox Grup Grave
	var ckbGrupGrave = "";
	var checkboxGrupGrave = document.getElementsByName("grup_grave");
		for (var x = 0; x < checkboxGrupGrave.length; x ++) {
			if(checkboxGrupGrave[x].checked) {
				ckbGrupGrave=checkboxGrupGrave[x].id;
			}                               
		}
		
	//Checkbox Bronquiolitis Grave
	var ckbBronquiolitisGrave = "";
	var checkboxBronquiolitisGrave = document.getElementsByName("bronquiolitis_grave");
		for (var x = 0; x < checkboxBronquiolitisGrave.length; x ++) {
			if(checkboxBronquiolitisGrave[x].checked) {
				ckbBronquiolitisGrave=checkboxBronquiolitisGrave[x].id;
			}                               
		}
		
	//Checkbox Sibilancia Grave
	var ckbSibilanciaGrave = "";
	var checkboxSibilanciaGrave = document.getElementsByName("sibilancia_grave");
		for (var x = 0; x < checkboxSibilanciaGrave.length; x ++) {
			if(checkboxSibilanciaGrave[x].checked) {
				ckbSibilanciaGrave=checkboxSibilanciaGrave[x].id;
			}                               
		}
		
	//Checkbox Crup
	var ckbCrup = "";
	var checkboxCrup = document.getElementsByName("crup");
		for (var x = 0; x < checkboxCrup.length; x ++) {
			if(checkboxCrup[x].checked) {
				ckbCrup=checkboxCrup[x].id;
			}                               
		}
		
	//Checkbox Sibilancia
	var ckbxSibilancia = "";
	var checkboxSibilancia = document.getElementsByName("sibilancia");
		for (var x = 0; x < checkboxSibilancia.length; x ++) {
			if(checkboxSibilancia[x].checked) {
				ckbxSibilancia=checkboxSibilancia[x].id;
			}                               
		}
		
	//Checkbox Neumonia Grave
	var ckbNeumoniaGrave = "";
	var checkboxNeumoniaGrav = document.getElementsByName("neumonia_grave");
		for (var x = 0; x < checkboxNeumoniaGrav.length; x ++) {
			if(checkboxNeumoniaGrav[x].checked) {
				ckbNeumoniaGrav=checkboxNeumoniaGrav[x].id;
			}                               
		}
		
	//Checkbox Neumonia
	var ckbNeumonia = "";
	var checkboxNeumonia = document.getElementsByName("neumonia");
		for (var x = 0; x < checkboxNeumonia.length; x ++) {
			if(checkboxNeumonia[x].checked) {
				ckbNeumonia=checkboxNeumonia[x].id;
			}                               
		}
		
	//Checkbox Tos
	var ckbTos = "";
	var checkboxTos = document.getElementsByName("tos");
		for (var x = 0; x < checkboxTos.length; x ++) {
			if(checkboxTos[x].checked) {
				ckbTos=checkboxTos[x].id;
			}                               
		}
	
	/******************************************************************************************************/
	/*************************************TIENE DIARREA****************************************************/
	/******************************************************************************************************/
	//Radio Tiene Diarrea
	var radTieneDiarrea = "";
	var radioTieneDiarrea = document.getElementsByName("rad_tiene_diarrea");
		for (var x = 0; x < radioTieneDiarrea.length; x ++) {
			if(radioTieneDiarrea[x].checked) {
				radTieneDiarrea=radioTieneDiarrea[x].id;
			}                               
		}
	if (radTieneDiarrea==false) {
		alert("Seleccione si tiene diarrea, en la Seccion Verificar Diarrea");
	} else {
		
	var desdeCuandoDiarrea=document.getElementById("desde_cuando_diarrea").value;
		
	//Radio Sangre Heces
	var radiSangreHeces = "";
	var radioSangreHeces = document.getElementsByName("rad_sangre_heces");
		for (var x = 0; x < radioSangreHeces.length; x ++) {
			if(radioSangreHeces[x].checked) {
				radiSangreHeces=radioSangreHeces[x].id;
			}                               
		}
		
	//Radio Vomito
	var radiVomito = "";
	var radioVomito = document.getElementsByName("rad_vomito");
		for (var x = 0; x < radioVomito.length; x ++) {
			if(radioVomito[x].checked) {
				radiVomito=radioVomito[x].id;
			}                               
		}
		
	var numVomitos=document.getElementById("num_vomitos").value;
	var numDiarreasVC=document.getElementById("num_diarreas_vc").value;
	var numDiarreasC=document.getElementById("num_diarreas_c").value;

	//Checkbox Comatoso
	var ckbComatoso = "";
	var checkboxComatoso = document.getElementsByName("comatoso");
		for (var x = 0; x < checkboxComatoso.length; x ++) {
			if(checkboxComatoso[x].checked) {
				ckbComatoso=checkboxComatoso[x].id;
			}                               
		}
		
	//Checkbox Intranquilo
	var ckbIntranquilo = "";
	var checkboxIntranquilo = document.getElementsByName("intranquilo");
		for (var x = 0; x < checkboxIntranquilo.length; x ++) {
			if(checkboxIntranquilo[x].checked) {
				ckbIntranquilo=checkboxIntranquilo[x].id;
			}                               
		}
		
	//Checkbox Ojos Hundidos
	var ckbxOjosHundidos = "";
	var checkboxOjosHundidos = document.getElementsByName("ojos_hundidos");
		for (var x = 0; x < checkboxOjosHundidos.length; x ++) {
			if(checkboxOjosHundidos[x].checked) {
				ckbxOjosHundidos=checkboxOjosHundidos[x].id;
			}                               
		}
		
	//Checkbox Bebe Mal
	var ckbBebeMal = "";
	var checkboxBebeMal = document.getElementsByName("bebe_mal");
		for (var x = 0; x < checkboxBebeMal.length; x ++) {
			if(checkboxBebeMal[x].checked) {
				ckbBebeMal=checkboxBebeMal[x].id;
			}                               
		}
		
	//Checkbox Bebe Avidamente
	var ckbBebeAvidamente = "";
	var checkboxBebeAvidamente = document.getElementsByName("bebe_avidamente");
		for (var x = 0; x < checkboxBebeAvidamente.length; x ++) {
			if(checkboxBebeAvidamente[x].checked) {
				ckbBebeAvidamente=checkboxBebeAvidamente[x].id;
			}                               
		}
		
	var pliegueCutaneos=document.getElementById("pliegue_cutaneo").value;
	var observacionesDiarrea=document.getElementById("obs_diarrea").value;
	
		/*////////////////RESPUESTAS DIARREA///////////////////////////////*/
	//Checkbox Deshidratacion Grave
	var ckbDeshidratacionGrave = "";
	var checkboxDeshidratacionGrave = document.getElementsByName("deshidratacion_grave");
		for (var x = 0; x < checkboxDeshidratacionGrave.length; x ++) {
			if(checkboxDeshidratacionGrave[x].checked) {
				ckbDeshidratacionGrave=checkboxDeshidratacionGrave[x].id;
			}                               
		}
		
	//Checkbox Grado Deshidratacion
	var ckbGradoDeshidratacion = "";
	var checkboxGradoDeshidratacion = document.getElementsByName("grado_deshidratacion");
		for (var x = 0; x < checkboxGradoDeshidratacion.length; x ++) {
			if(checkboxGradoDeshidratacion[x].checked) {
				ckbGradoDeshidratacion=checkboxGradoDeshidratacion[x].id;
			}                               
		}
		
	//Checkbox Alto Riesgo Deshidratacion
	var ckbAltoRiesgoDeshidratacion = "";
	var checkboxAltoRiesgoDeshidratacion = document.getElementsByName("alto_riesgo_deshidratacion");
		for (var x = 0; x < checkboxAltoRiesgoDeshidratacion.length; x ++) {
			if(checkboxAltoRiesgoDeshidratacion[x].checked) {
				ckbAltoRiesgoDeshidratacion=checkboxAltoRiesgoDeshidratacion[x].id;
			}                               
		}
		
	//Checkbox Sin Deshidratacion
	var ckbSinDeshidratacion = "";
	var checkboxSinDeshidratacion = document.getElementsByName("sin_deshidratacion");
		for (var x = 0; x < checkboxSinDeshidratacion.length; x ++) {
			if(checkboxSinDeshidratacion[x].checked) {
				ckbSinDeshidratacion=checkboxSinDeshidratacion[x].id;
			}                               
		}
		
	//Checkbox Diarrea Persistente Grave
	var ckbDiarreaPersistenteGrave = "";
	var checkboxDiarreaPersistenteGrave = document.getElementsByName("diarrea_persistente_grave");
		for (var x = 0; x < checkboxDiarreaPersistenteGrave.length; x ++) {
			if(checkboxDiarreaPersistenteGrave[x].checked) {
				ckbDiarreaPersistenteGrave=checkboxDiarreaPersistenteGrave[x].id;
			}                               
		}
		
	//Checkbox Diarrea Persistente
	var ckbDiarreaPersistente = "";
	var checkboxDiarreaPersistente = document.getElementsByName("diarrea_persistente");
		for (var x = 0; x < checkboxDiarreaPersistente.length; x ++) {
			if(checkboxDiarreaPersistente[x].checked) {
				ckbDiarreaPersistente=checkboxDiarreaPersistente[x].id;
			}                               
		}
			
	//Checkbox Disenteria
	var ckbDisenteria = "";
	var checkboxDisenteria = document.getElementsByName("disenteria");
		for (var x = 0; x < checkboxDisenteria.length; x ++) {
			if(checkboxDisenteria[x].checked) {
				ckbDisenteria=checkboxDisenteria[x].id;
			}                               
		}
	
	/******************************************************************************************************/
	/*************************************TIENE FIEBRE*****************************************************/
	/******************************************************************************************************/
	//Radio Tiene Fiebre
	var radTieneFiebre = "";
	var radioTieneFiebre = document.getElementsByName("rad_tiene_fiebre");
		for (var x = 0; x < radioTieneFiebre.length; x ++) {
			if(radioTieneFiebre[x].checked) {
				radTieneFiebre=radioTieneFiebre[x].id;
			}                               
		}
	if (radTieneFiebre==false) {
		alert("Seleccione si tiene fiebre, en la Seccion Verificar Fiebre");
	} else {
		
	var desdeCuandoFiebre=document.getElementById("desde_cuando_fiebre").value;
	
	//Radio Fiebre TO
	var radFiebreTO = "";
	var radioFiebreTO = document.getElementsByName("rad_fiebre_to");
		for (var x = 0; x < radioFiebreTO.length; x ++) {
			if(radioFiebreTO[x].checked) {
				radFiebreTO=radioFiebreTO[x].id;
			}                               
		}
	
	//Radio FiebreTN
	var radFiebreTN = "";
	var radioFiebreTN = document.getElementsByName("rad_fiebre_tn");
		for (var x = 0; x < radioFiebreTN.length; x ++) {
			if(radioFiebreTN[x].checked) {
				radFiebreTN=radioFiebreTN[x].id;
			}                               
		}
	
	//Radio Vive Quince Dias
	var radViveQuinceDias = "";
	var radioViveQuinceDias = document.getElementsByName("rad_vive_quince_dias");
		for (var x = 0; x < radioViveQuinceDias.length; x ++) {
			if(radioViveQuinceDias[x].checked) {
				radViveQuinceDias=radioViveQuinceDias[x].id;
			}                               
		}
	
	//Checkbox Zona Dengue
	var ckbZonaDengue = "";
	var checkboxZonaDengue = document.getElementsByName("zona_dengue");
		for (var x = 0; x < checkboxZonaDengue.length; x ++) {
			if(checkboxZonaDengue[x].checked) {
				ckbZonaDengue=checkboxZonaDengue[x].id;
			}                               
		}	
		
	var zonaMalaria=document.getElementById("zona_malaria").value;
		
	//Checkbox Rigidez Nuca
	var ckbRigidezNuca = "";
	var checkboxRigidezNuca = document.getElementsByName("rigidez_nuca");
		for (var x = 0; x < checkboxRigidezNuca.length; x ++) {
			if(checkboxRigidezNuca[x].checked) {
				ckbRigidezNuca=checkboxRigidezNuca[x].id;
			}                               
		}
		
	//Checkbox Apariencia Enfermo
	var ckbAparienciaEnfermo = "";
	var checkboxAparienciaEnfermo = document.getElementsByName("apariencia_enfermo");
		for (var x = 0; x < checkboxAparienciaEnfermo.length; x ++) {
			if(checkboxAparienciaEnfermo[x].checked) {
				ckbAparienciaEnfermo=checkboxAparienciaEnfermo[x].id;
			}                               
		}
		
	//Checkbox Manifestaciones Sangrado
	var ckbManifestacionesSangrado = "";
	var checkboxManifestacionesSangrado = document.getElementsByName("manifestaciones_sangrado");
		for (var x = 0; x < checkboxManifestacionesSangrado.length; x ++) {
			if(checkboxManifestacionesSangrado[x].checked) {
				ckbManifestacionesSangrado=checkboxManifestacionesSangrado[x].id;
			}                               
		}
		
	//Checkbox Aspecto Toxico
	var ckbAspectoToxico = "";
	var checkboxAspectoToxico = document.getElementsByName("aspecto_toxico");
		for (var x = 0; x < checkboxAspectoToxico.length; x ++) {
			if(checkboxAspectoToxico[x].checked) {
				ckbAspectoToxico=checkboxAspectoToxico[x].id;
			}                               
		}
		
	var respuestaSocial=document.getElementById("respuesta_social").value;
	var piel=document.getElementById("piel").value;
	
	//Checkbox Erupcion Cutanea
	var ckbErupcionCutanea = "";
	var checkboxErupcionCutanea = document.getElementsByName("erupcion_cutanea");
		for (var x = 0; x < checkboxErupcionCutanea.length; x ++) {
			if(checkboxErupcionCutanea[x].checked) {
				ckbErupcionCutanea=checkboxErupcionCutanea[x].id;
			}                               
		}
		
	//Checkbox Dolor Abdominal
	var ckbDolorAbdominal = "";
	var checkboxRigidezNuca = document.getElementsByName("dolor_abdominal");
		for (var x = 0; x < checkboxRigidezNuca.length; x ++) {
			if(checkboxRigidezNuca[x].checked) {
				ckbRigidezNuca=checkboxRigidezNuca[x].id;
			}                               
		}
		
	//Checkbox Cefalea
	var ckbCefalea = "";
	var checkboxCefalea = document.getElementsByName("cefalea");
		for (var x = 0; x < checkboxCefalea.length; x ++) {
			if(checkboxCefalea[x].checked) {
				ckbCefalea=checkboxCefalea[x].id;
			}                               
		}
		
	//Checkbox Mialgias
	var ckbMialgias = "";
	var checkboxMialgias = document.getElementsByName("mialgias");
		for (var x = 0; x < checkboxMialgias.length; x ++) {
			if(checkboxMialgias[x].checked) {
				ckbMialgias=checkboxMialgias[x].id;
			}                               
		}
		
	//Checkbox Artralgias
	var ckbArtralgias = "";
	var checkboxArtralgias = document.getElementsByName("artralgias");
		for (var x = 0; x < checkboxArtralgias.length; x ++) {
			if(checkboxArtralgias[x].checked) {
				ckbArtralgias=checkboxArtralgiaso[x].id;
			}                               
		}
		
	//Checkbox Dolor Retroocular
	var ckbDolorRetroocular = "";
	var checkboxDolorRetroocular = document.getElementsByName("dolor_retroocular");
		for (var x = 0; x < checkboxDolorRetroocular.length; x ++) {
			if(checkboxDolorRetroocular[x].checked) {
				ckbDolorRetroocular=checkboxDolorRetroocular[x].id;
			}                               
		}
			
	//Checkbox Postracion
	var ckbPostracion = "";
	var checkboxPostracion = document.getElementsByName("postracion");
		for (var x = 0; x < checkboxPostracion.length; x ++) {
			if(checkboxPostracion[x].checked) {
				ckbPostracion=checkboxPostracion[x].id;
			}                               
		}
			
	//Checkbox P Torniquete
	var ckbPTorniquete = "";
	var checkboxPTorniquete = document.getElementsByName("p_torniquete");
		for (var x = 0; x < checkboxPTorniquete.length; x ++) {
			if(checkboxPTorniquete[x].checked) {
				ckbPTorniquete=checkboxPTorniquete[x].id;
			}                               
		}
			
	//Checkbox Lipotimia
	var ckbLipotimia = "";
	var checkboxLipotimia = document.getElementsByName("lipotimia");
		for (var x = 0; x < checkboxLipotimia.length; x ++) {
			if(checkboxLipotimia[x].checked) {
				ckbLipotimia=checkboxLipotimia[x].id;
			}                               
		}
			
	//Checkbox Hepatomegalia
	var ckbHepatomegalia = "";
	var checkboxHepatomegalia = document.getElementsByName("hepatomegalia");
		for (var x = 0; x < checkboxHepatomegalia.length; x ++) {
			if(checkboxHepatomegalia[x].checked) {
				ckbHepatomegalia=checkboxHepatomegalia[x].id;
			}                               
		}
			
	//Checkbox Pulso Rapido
	var ckbPulsoRapido = "";
	var checkboxPulsoRapido = document.getElementsByName("pulso_rapido");
		for (var x = 0; x < checkboxPulsoRapido.length; x ++) {
			if(checkboxPulsoRapido[x].checked) {
				ckbPulsoRapido=checkboxPulsoRapido[x].id;
			}                               
		}
				
	//Checkbox Llenado Capilar
	var ckbxLlenadoCapilar = "";
	var checkboxLlenadoCapilar = document.getElementsByName("llenado_capilar");
		for (var x = 0; x < checkboxLlenadoCapilar.length; x ++) {
			if(checkboxLlenadoCapilar[x].checked) {
				ckbxLlenadoCapilar=checkboxLlenadoCapilar[x].id;
			}                               
		}

	//Checkbox Ascitis
	var ckbAscitis = "";
	var checkboxAscitis = document.getElementsByName("ascitis");
		for (var x = 0; x < checkboxAscitis.length; x ++) {
			if(checkboxAscitis[x].checked) {
				ckbAscitis=checkbox[x].id;
			}                               
		}
			
	//Checkbox Disminucion Diuresis
	var ckbDisminucionDiuresis = "";
	var checkboxDisminucionDiuresis = document.getElementsByName("disminucion_diuresis");
		for (var x = 0; x < checkboxDisminucionDiuresis.length; x ++) {
			if(checkboxDisminucionDiuresis[x].checked) {
				ckbDisminucionDiuresis=checkboxDisminucionDiuresis[x].id;
			}                               
		}
	
	//Checkbox Cuadro Hematico
	var ckbCuadroHematico = "";
	var checkboxCuadroHematico = document.getElementsByName("cuadro_hematico");
		for (var x = 0; x < checkboxCuadroHematico.length; x ++) {
			if(checkboxCuadroHematico[x].checked) {
				ckbCuadroHematico=checkboxCuadroHematico[x].id;
			}                               
		}
				
	//Checkbox Leucocitos
	var ckbLeucocitos = "";
	var checkboxLeucocitos = document.getElementsByName("leucocitos");
		for (var x = 0; x < checkboxLeucocitos.length; x ++) {
			if(checkboxLeucocitos[x].checked) {
				ckbLeucocitos=checkboxLeucocitos[x].id;
			}                               
		}
				
	//Checkbox Neutrofilos
	var ckbNeutrofilos = "";
	var checkboxNeutrofilos = document.getElementsByName("neutrofilos");
		for (var x = 0; x < checkboxNeutrofilos.length; x ++) {
			if(checkboxNeutrofilos[x].checked) {
				ckbNeutrofilos=checkboxNeutrofilos[x].id;
			}                               
		}
				
	//Checkbox Plaquetas
	var ckbPlaquetas = "";
	var checkboxPlaquetas = document.getElementsByName("plaquetas");
		for (var x = 0; x < checkboxPlaquetas.length; x ++) {
			if(checkboxPlaquetas[x].checked) {
				ckbPlaquetas=checkboxPlaquetas[x].id;
			}                               
		}
					
	//Checkbox Parcial Orina
	var ckbParcialOrina = "";
	var checkboxParcialOrina = document.getElementsByName("parcial_orina");
		for (var x = 0; x < checkboxParcialOrina.length; x ++) {
			if(checkboxParcialOrina[x].checked) {
				ckbParcialOrina=checkboxParcialOrina[x].id;
			}                               
		}
					
	//Checkbox Gota Gruesa
	var ckbGotaGruesa = "";
	var checkboxGotaGruesa = document.getElementsByName("gota_gruesa");
		for (var x = 0; x < checkboxGotaGruesa.length; x ++) {
			if(checkboxGotaGruesa[x].checked) {
				ckbGotaGruesa=checkboxGotaGruesa[x].id;
			}                               
		}

	var observacionesFiebre=document.getElementById("obs_fiebre").value;	
		
		/*////////////////RESPUESTAS TIENE FIEBRE///////////////////////////////*/
	//Checkbox Enfermedad Febril Riesgo
	var ckbEnfFebrilRiesgo = "";
	var checkboxEnfFebrilRiesgo = document.getElementsByName("enf_febril_riesgo");
		for (var x = 0; x < checkboxEnfFebrilRiesgo.length; x ++) {
			if(checkboxEnfFebrilRiesgo[x].checked) {
				ckbEnfFebrilRiesgo=checkboxEnfFebrilRiesgo[x].id;
			}                               
		}	
		
	//Checkbox Enfermedad Febril Riesgo Intermedio
	var ckbEnfFebrilRiesgoIntermedio = "";
	var checkboxEnfFebrilRiesgoIntermedio = document.getElementsByName("enf_febril_riesgo_intermedio");
		for (var x = 0; x < checkboxEnfFebrilRiesgoIntermedio.length; x ++) {
			if(checkboxEnfFebrilRiesgoIntermedio[x].checked) {
				ckbEnfFebrilRiesgoIntermedio=checkboxEnfFebrilRiesgoIntermedio[x].id;
			}                               
		}
		
	//Checkbox Enfermedad Febril Riesgo Bajo
	var ckbEnfFebrilRiesgoBajo = "";
	var checkboxEnfFebrilRiesgoBajo = document.getElementsByName("enf_febril_riesgo_bajo");
		for (var x = 0; x < checkboxEnfFebrilRiesgoBajo.length; x ++) {
			if(checkboxEnfFebrilRiesgoBajo[x].checked) {
				ckbEnfFebrilRiesgoBajo=checkboxEnfFebrilRiesgoBajo[x].id;
			}                               
		}
		
	//Checkbox Malaria Complicada
	var ckbMalariaComplicada = "";
	var checkboxMalariaComplicada = document.getElementsByName("malaria_complicada");
		for (var x = 0; x < checkboxMalariaComplicada.length; x ++) {
			if(checkboxMalariaComplicada[x].checked) {
				ckbMalariaComplicada=checkboxMalariaComplicada[x].id;
			}                               
		}
		
	//Checkbox Malaria
	var ckbMalaria = "";
	var checkboxMalaria = document.getElementsByName("malaria");
		for (var x = 0; x < checkboxMalaria.length; x ++) {
			if(checkboxMalaria[x].checked) {
				ckbMalaria=checkboxMalaria[x].id;
			}                               
		}

	//Checkbox Dengue Grave
	var ckbDengueGrave = "";
	var checkboxDengueGrave = document.getElementsByName("dengue_grave");
		for (var x = 0; x < checkboxDengueGrave.length; x ++) {
			if(checkboxDengueGrave[x].checked) {
				ckbDengueGrave=checkboxDengueGrave[x].id;
			}                               
		}
			
	//Checkbox Dengue Signos Alarma
	var ckbDengueSignosAlarma = "";
	var checkboxDengueSignosAlarma = document.getElementsByName("dengue_signos_alarma");
		for (var x = 0; x < checkboxDengueSignosAlarma.length; x ++) {
			if(checkboxDengueSignosAlarma[x].checked) {
				ckbDengueSignosAlarma=checkboxDengueSignosAlarma[x].id;
			}                               
		}
			
	//Checkbox Probable Dengue
	var ckbProbableDengue = "";
	var checkboxProbableDengue = document.getElementsByName("probable_dengue");
		for (var x = 0; x < checkboxProbableDengue.length; x ++) {
			if(checkboxProbableDengue[x].checked) {
				ckbProbableDengue=checkboxProbableDengue[x].id;
			}                               
		}		
			
	/******************************************************************************************************/
	/*************************************TIENE PROBLEMA DE OIDO*******************************************/
	/******************************************************************************************************/
	//Radio Tiene Problema Oido
	var radTieneProbOido = "";
	var radioTieneProbOido = document.getElementsByName("rad_tiene_prob_oido");
		for (var x = 0; x < radioTieneProbOido.length; x ++) {
			if(radioTieneProbOido[x].checked) {
				radTieneProbOido=radioTieneProbOido[x].id;
			}                               
		}
	if (radTieneProbOido==false) {
		alert("Seleccione si tiene problema de oido, en la Seccion Verificar Problema Oido");
	} else {
		
	//Radio Tiene Dolor Oido
	var radTieneDolorOido = "";
	var radioTieneDolorOido = document.getElementsByName("rad_tiene_dolor_oido");
		for (var x = 0; x < radioTieneDolorOido.length; x ++) {
			if(radioTieneDolorOido[x].checked) {
				radTieneDolorOido=radioTieneDolorOido[x].id;
			}                               
		}
	
	//Radio Tiene Supuracion
	var ckbTieneSupuracion = "";
	var checkboxTieneSupuracion = document.getElementsByName("rad_tiene_supuracion");
		for (var x = 0; x < checkboxTieneSupuracion.length; x ++) {
			if(checkboxTieneSupuracion[x].checked) {
				ckbTieneSupuracion=checkboxTieneSupuracion[x].id;
			}                               
		}	
		
	var desdeCuandoSupuracion=document.getElementById("desde_cuando_supuracion").value;
	var numEpisodiosPrevios=document.getElementById("num_episodios_previos").value;
		
	//Checkbox Tumefaccion Dolorosa
	var ckbTumefaccionDolorosa = "";
	var checkboxTumefaccionDolorosa = document.getElementsByName("tumefaccion_dolorosa");
		for (var x = 0; x < checkboxTumefaccionDolorosa.length; x ++) {
			if(checkboxTumefaccionDolorosa[x].checked) {
				ckbTumefaccionDolorosa=checkboxTumefaccionDolorosa[x].id;
			}                               
		}	
		
	//Checkbox Timpano Rojo
	var ckbTimpanoRojo = "";
	var checkboxTimpanoRojo = document.getElementsByName("timpano_rojo");
		for (var x = 0; x < checkboxTimpanoRojo.length; x ++) {
			if(checkboxTimpanoRojo[x].checked) {
				ckbTimpanoRojo=checkboxTimpanoRojo[x].id;
			}                               
		}
		
	//Checkbox Supuracion Oido
	var ckbxSupuracionOido = "";
	var checkboxSupuracionOido = document.getElementsByName("supuracion_oido");
		for (var x = 0; x < checkboxSupuracionOido.length; x ++) {
			if(checkboxSupuracionOido[x].checked) {
				ckbxSupuracionOido=checkboxSupuracionOido[x].id;
			}                               
		}
		
	var observacionesOido=document.getElementById("obs_oido").value;
	
		/*////////////////RESPUESTAS OIDO///////////////////////////////*/
	//Checkbox Mastoiditis
	var ckbMastoiditis = "";
	var checkboxMastoiditis = document.getElementsByName("mastoiditis");
		for (var x = 0; x < checkboxMastoiditis.length; x ++) {
			if(checkboxMastoiditis[x].checked) {
				ckbMastoiditis=checkboxMastoiditis[x].id;
			}                               
		}
		
	//Checkbox Otitis Media Cronica
	var ckbOtitisMediaCronica = "";
	var checkboxOtitisMediaCronica = document.getElementsByName("otitis_media_cronica");
		for (var x = 0; x < checkboxOtitisMediaCronica.length; x ++) {
			if(checkboxOtitisMediaCronica[x].checked) {
				ckbOtitisMediaCronica=checkboxOtitisMediaCronica[x].id;
			}                               
		}
		
	//Checkbox Otitis Media Recurrente
	var ckbOtitisMediaRecurrente = "";
	var checkboxOtitisMediaRecurrente = document.getElementsByName("otitis_media_recurrente");
		for (var x = 0; x < checkboxOtitisMediaRecurrente.length; x ++) {
			if(checkboxOtitisMediaRecurrente[x].checked) {
				ckbOtitisMediaRecurrente=checkboxOtitisMediaRecurrente[x].id;
			}                               
		}
		
	//Checkbox Otitis Media Aguda
	var ckbOtitisMediaAguda = "";
	var checkboxOtitisMediaAguda = document.getElementsByName("otitis_media_aguda");
		for (var x = 0; x < checkboxOtitisMediaAguda.length; x ++) {
			if(checkboxOtitisMediaAguda[x].checked) {
				ckbOtitisMediaAguda=checkboxOtitisMediaAguda[x].id;
			}                               
		}
		
	//Checkbox No Tiene Otitis
	var ckbNoTieneOtitis = "";
	var checkboxNoTieneOtitis = document.getElementsByName("no_tiene_otitis");
		for (var x = 0; x < checkboxNoTieneOtitis.length; x ++) {
			if(checkboxNoTieneOtitis[x].checked) {
				ckbNoTieneOtitis=checkboxNoTieneOtitis[x].id;
			}                               
		}
	
	/******************************************************************************************************/
	/*************************************TIENE PROBLEMA GARGANTA******************************************/
	/******************************************************************************************************/	
	//Radio Tiene Problema Garganta
	var radTieneProbGarganta = "";
	var radioTieneProbGarganta = document.getElementsByName("rad_tiene_prob_garganta");
		for (var x = 0; x < radioTieneProbGarganta.length; x ++) {
			if(radioTieneProbGarganta[x].checked) {
				radTieneProbGarganta=radioTieneProbGarganta[x].id;
			}                               
		}
	if (radTieneProbGarganta==false) {
		alert("Seleccione si tiene problema de garganta, en la Seccion Verificar Problema Garganta");
	} else {
		
	//Radio Tiene Dolor Garganta
	var radTieneDolorGarganta = "";
	var radioTieneDolorGarganta = document.getElementsByName("tiene_dolor_garganta");
		for (var x = 0; x < radioTieneDolorGarganta.length; x ++) {
			if(radioTieneDolorGarganta[x].checked) {
				radTieneDolorGarganta=radioTieneDolorGarganta[x].id;
			}                               
		}
		
	//Checkbox Ganglios Cuello
	var ckbGangliosCuello = "";
	var checkboxGangliosCuello = document.getElementsByName("ganglios_cuello");
		for (var x = 0; x < checkboxGangliosCuello.length; x ++) {
			if(checkboxGangliosCuello[x].checked) {
				ckbGangliosCuello=checkboxGangliosCuello[x].id;
			}                               
		}
		
	//Checkbox Amigdalas Eritematosas
	var ckbAmigdalasEritematosas = "";
	var checkboxAmigdalasEritematosas = document.getElementsByName("amigdalas_eritematosas");
		for (var x = 0; x < checkboxAmigdalasEritematosas.length; x ++) {
			if(checkboxAmigdalasEritematosas[x].checked) {
				ckbAmigdalasEritematosas=checkboxAmigdalasEritematosas[x].id;
			}                               
		}
		
	//Checkbox Exudado Blanquecino
	var ckbExudadoBlanquecino = "";
	var checkboxExudadoBlanquecino = document.getElementsByName("exudado_blanquecino");
		for (var x = 0; x < checkboxExudadoBlanquecino.length; x ++) {
			if(checkboxExudadoBlanquecino[x].checked) {
				ckbExudadoBlanquecino=checkboxExudadoBlanquecino[x].id;
			}                               
		}
	
	var observacionesGarganta=document.getElementById("obs_garganta").value;
	
		/*////////////////RESPUESTAS GARGANTA///////////////////////////////*/
	//Checkbox Faringoamigdalitis
	var ckbFaringoamigdalitis = "";
	var checkboxFaringoamigdalitis = document.getElementsByName("faringoamigdalitis");
		for (var x = 0; x < checkboxFaringoamigdalitis.length; x ++) {
			if(checkboxFaringoamigdalitis[x].checked) {
				ckbFaringoamigdalitis=checkboxFaringoamigdalitis[x].id;
			}                               
		}
		
	//Checkbox Estreptococica
	var ckbEstreptococica = "";
	var checkboxEstreptococica = document.getElementsByName("estreptococica");
		for (var x = 0; x < checkboxEstreptococica.length; x ++) {
			if(checkboxEstreptococica[x].checked) {
				ckbEstreptococica=checkboxEstreptococica[x].id;
			}                               
		}
		
	//Checkbox Faringoamigdalitis Viral
	var ckbFaringoamigdalitisViral = "";
	var checkboxFaringoamigdalitisViral = document.getElementsByName("faringoamigdalitis_viral");
		for (var x = 0; x < checkboxFaringoamigdalitisViral.length; x ++) {
			if(checkboxFaringoamigdalitisViral[x].checked) {
				ckbFaringoamigdalitisViral=checkboxFaringoamigdalitisViral[x].id;
			}                               
		}
		
	//Checkbox No Tiene Faringoamigdalitis
	var ckbNoTieneFaringoamigdalitis = "";
	var checkboxNoTieneFaringoamigdalitis = document.getElementsByName("no_tiene_faringoamigdalitis");
		for (var x = 0; x < checkboxNoTieneFaringoamigdalitis.length; x ++) {
			if(checkboxNoTieneFaringoamigdalitis[x].checked) {
				ckbNoTieneFaringoamigdalitis=checkboxNoTieneFaringoamigdalitis[x].id;
			}                               
		}
	
	/******************************************************************************************************/
	/*************************************VERIFICAR SALUD BUCAL********************************************/
	/******************************************************************************************************/
	//Radio Tiene Dolor Comer
	var radTieneDolorComer = "";
	var radioTieneDolorComer = document.getElementsByName("rad_tiene_dolor_comer");
		for (var x = 0; x < radioTieneDolorComer.length; x ++) {
			if(radioTieneDolorComer[x].checked) {
				radTieneDolorComer=radioTieneDolorComer[x].id;
			}                               
		}
	if (radTieneDolorComer==false) {
		alert("Seleccione si tiene dolor al comer, en la Seccion Verificar Salud Bucal");
	} else {
		
	//Radio Tiene Dolor Diente
	var radTieneDolorDiente = "";
	var radioTieneDolorDiente = document.getElementsByName("rad_tiene_dolor_diente");
		for (var x = 0; x < radioTieneDolorDiente.length; x ++) {
			if(radioTieneDolorDiente[x].checked) {
				radTieneDolorDiente=radioTieneDolorDiente[x].id;
			}                               
		}
	if (radTieneDolorDiente==false) {
		alert("Seleccione si tiene dolor en diente, en la Seccion Verificar Salud Bucal");
	} else {
		
	//Radio Trauma Cara
	var radTraumaCara = "";
	var radioTraumaCara = document.getElementsByName("rad_trauma_cara");
		for (var x = 0; x < radioTraumaCara.length; x ++) {
			if(radioTraumaCara[x].checked) {
				radTraumaCara=radioTraumaCara[x].id;
			}                               
		}
	if (radTraumaCara==false) {
		alert("Seleccione si tiene trauma en cara o boca, en la Seccion Verificar Salud Bucal");
	} else {
			
	//Radio Tiene Caries
	var radTieneCaries = "";
	var radioTieneCaries = document.getElementsByName("rad_tiene_caries");
		for (var x = 0; x < radioTieneCaries.length; x ++) {
			if(radioTieneCaries[x].checked) {
				radTieneCaries=radioTieneCaries[x].id;
			}                               
		}
	if (radTieneCaries==false) {
		alert("Seleccione si padres o hermanos tienen caries, en la Seccion Verificar Salud Bucal");
	} else {
			
	//Radio Limpia Boca Manana
	var radLimpiaBocaManana = "";
	var radioLimpiaBocaManana = document.getElementsByName("rad_limpia_boca_manana");
		for (var x = 0; x < radioLimpiaBocaManana.length; x ++) {
			if(radioLimpiaBocaManana[x].checked) {
				radLimpiaBocaManana=radioLimpiaBocaManana[x].id;
			}                               
		}
	if (radLimpiaBocaManana==false) {
		alert("Seleccione si le limpia la boca en la manana, en la Seccion Verificar Salud Bucal");
	} else {
				
	//Radio Limpia Boca Tarde
	var radLimpiaBocaTarde = "";
	var radioLimpiaBocaTarde = document.getElementsByName("rad_limpia_boca_tarde");
		for (var x = 0; x < radioLimpiaBocaTarde.length; x ++) {
			if(radioLimpiaBocaTarde[x].checked) {
				radLimpiaBocaTarde=radioLimpiaBocaTarde[x].id;
			}                               
		}
	if (radLimpiaBocaTarde==false) {
		alert("Seleccione si le limpia la boca en la tarde, en la Seccion Verificar Salud Bucal");
	} else {
				
	//Radio Limpia Boca Noche
	var radLimpiaBocaNoche = "";
	var radioLimpiaBocaNoche = document.getElementsByName("rad_limpia_boca_noche");
		for (var x = 0; x < radioLimpiaBocaNoche.length; x ++) {
			if(radioLimpiaBocaNoche[x].checked) {
				radLimpiaBocaNoche=radioLimpiaBocaNoche[x].id;
			}                               
		}
	if (radLimpiaBocaNoche==false) {
		alert("Seleccione si le limpia la boca en la noche, en la Seccion Verificar Salud Bucal");
	} else {
					
	//Radio Limpia Dientes
	var radLimpiaDientes = "";
	var radioLimpiaDientes = document.getElementsByName("rad_limpia_dientes");
		for (var x = 0; x < radioLimpiaDientes.length; x ++) {
			if(radioLimpiaDientes[x].checked) {
				radLimpiaDientes=radioLimpiaDientes[x].id;
			}                               
		}
	if (radLimpiaDientes==false) {
		alert("Seleccione si le limpia los dientes, en la Seccion Verificar Salud Bucal");
	} else {
					
	//Radio Nino Solo
	var radNinoSolo = "";
	var radioNinoSolo = document.getElementsByName("rad_nino_solo");
		for (var x = 0; x < radioNinoSolo.length; x ++) {
			if(radioNinoSolo[x].checked) {
				radNinoSolo=radioNinoSolo[x].id;
			}                               
		}
	if (radNinoSolo==false) {
		alert("Seleccione si le limpia el nino solo, en la Seccion Verificar Salud Bucal");
	} else {
						
	//Radio Cepillo
	var radCepillo = "";
	var radioCepillo = document.getElementsByName("rad_cepillo");
		for (var x = 0; x < radioCepillo.length; x ++) {
			if(radioCepillo[x].checked) {
				radCepillo=radioCepillo[x].id;
			}                               
		}
	if (radCepillo==false) {
		alert("Seleccione si utiliza cepillo, en la Seccion Verificar Salud Bucal");
	} else {
						
	//Radio Crema
	var radCrema = "";
	var radioCrema = document.getElementsByName("rad_crema");
		for (var x = 0; x < radioCrema.length; x ++) {
			if(radioCrema[x].checked) {
				radCrema=radioCrema[x].id;
			}                               
		}
		if (radCrema==false) {
			alert("Seleccione si utiliza crema, en la Seccion Verificar Salud Bucal");
		} else {
							
	//Radio Seda
	var radSeda = "";
	var radioSeda = document.getElementsByName("rad_seda");
		for (var x = 0; x < radioSeda.length; x ++) {
			if(radioSeda[x].checked) {
				radSeda=radioSeda[x].id;
			}                               
		}
		if (radSeda==false) {
			alert("Seleccione si utiliza seda, en la Seccion Verificar Salud Bucal");
		} else {
							
	//Radio Utiliza Chupo
	var radiUtilizaChupo = "";
	var radioUtilizaChupo = document.getElementsByName("rad_utiliza_chupo");
		for (var x = 0; x < radioUtilizaChupo.length; x ++) {
			if(radioUtilizaChupo[x].checked) {
				radiUtilizaChupo=radioUtilizaChupo[x].id;
			}                               
		}
		if (radiUtilizaChupo==false) {
			alert("Seleccione si utiliza chupo o biberon, en la Seccion Verificar Salud Bucal");
		} else {
	
	var ultimaConsultaOdontologica=document.getElementById("ultima_consulta_odontologica").value;
	if (ultimaConsultaOdontologica=="") {
		alert("Escriba cuando fue la ultima consulta odontologica");
		document.getElementById("ultima_consulta_odontologica").focus();
	} else {	
	
	//Checkbox Inflamacion Labio
	var ckbInflamacionLabio = "";
	var checkboxInflamacionLabio = document.getElementsByName("inflamacion_labio");
		for (var x = 0; x < checkboxInflamacionLabio.length; x ++) {
			if(checkboxInflamacionLabio[x].checked) {
				radInflamacionLabio=checkboxInflamacionLabio[x].id;
			}                               
		}
		
	//Checkbox No Involucra Surco
	var ckbNoInvolucraSurco = "";
	var checkboxNoInvolucraSurco = document.getElementsByName("no_involucra_surco");
		for (var x = 0; x < checkboxNoInvolucraSurco.length; x ++) {
			if(checkboxNoInvolucraSurco[x].checked) {
				ckbNoInvolucraSurco=checkboxNoInvolucraSurco[x].id;
			}                               
		}
		
	//Checkbox Enrojecimiento
	var ckbEnrojecimiento = "";
	var checkboxEnrojecimiento = document.getElementsByName("enrojecimiento");
		for (var x = 0; x < checkboxEnrojecimiento.length; x ++) {
			if(checkboxEnrojecimiento[x].checked) {
				ckbEnrojecimiento=checkboxEnrojecimiento[x].id;
			}                               
		}
		
	//Checkbox Inflamacion Encia
	var ckbInflamacionEncia = "";
	var checkboxInflamacionEncia = document.getElementsByName("inflamacion_encia");
		for (var x = 0; x < checkboxInflamacionEncia.length; x ++) {
			if(checkboxInflamacionEncia[x].checked) {
				ckbInflamacionEncia=checkboxInflamacionEncia[x].id;
			}                               
		}
		
	//Checkbox Localizado
	var ckbLocalizado = "";
	var checkboxLocalizado = document.getElementsByName("localizado");
		for (var x = 0; x < checkboxLocalizado.length; x ++) {
			if(checkboxLocalizado[x].checked) {
				ckbLocalizado=checkboxLocalizado[x].id;
			}                               
		}
		
	//Checkbox Generalizado
	var ckbGeneralizado = "";
	var checkboxGeneralizado = document.getElementsByName("generalizado");
		for (var x = 0; x < checkboxGeneralizado.length; x ++) {
			if(checkboxGeneralizado[x].checked) {
				ckbGeneralizado=checkboxGeneralizado[x].id;
			}                               
		}
			
	//Checkbox Deformacion Encia
	var ckbDeformacionEncia = "";
	var checkboxDeformacionEncia = document.getElementsByName("deformacion_encia");
		for (var x = 0; x < checkboxDeformacionEncia.length; x ++) {
			if(checkboxDeformacionEncia[x].checked) {
				ckbDeformacionEncia=checkboxDeformacionEncia[x].id;
			}                               
		}
			
	//Checkbox Exudado Pus
	var ckbExudadoPus = "";
	var checkboxExudadoPus = document.getElementsByName("exudado_pus");
		for (var x = 0; x < checkboxExudadoPus.length; x ++) {
			if(checkboxExudadoPus[x].checked) {
				ckbExudadoPus=checkboxExudadoPus[x].id;
			}                               
		}
			
	//Checkbox Vesiculas
	var ckbVesiculas = "";
	var checkboxVesiculas = document.getElementsByName("vesiculas");
		for (var x = 0; x < checkboxVesiculas.length; x ++) {
			if(checkboxVesiculas[x].checked) {
				ckbVesiculas=checkboxVesiculas[x].id;
			}                               
		}
			
	//Checkbox Ulceras
	var ckbUlceras = "";
	var checkboxUlceras = document.getElementsByName("ulceras");
		for (var x = 0; x < checkboxUlceras.length; x ++) {
			if(checkboxUlceras[x].checked) {
				ckbUlceras=checkboxUlceras[x].id;
			}                               
		}

	var placas=document.getElementById("placas").value;
	
	//Checkbox Fractura
	var ckbFractura = "";
	var checkboxFractura = document.getElementsByName("fractura");
		for (var x = 0; x < checkboxFractura.length; x ++) {
			if(checkboxFractura[x].checked) {
				ckbFractura=checkboxFractura[x].id;
			}                               
		}
		
	//Checkbox Movilidad
	var ckbMovilidad = "";
	var checkboxMovilidad = document.getElementsByName("movilidad");
		for (var x = 0; x < checkboxMovilidad.length; x ++) {
			if(checkboxMovilidad[x].checked) {
				ckbMovilidad=checkboxMovilidad[x].id;
			}                               
		}
			
	//Checkbox Desplazamiento
	var ckbDesplazamiento = "";
	var checkboxDesplazamiento = document.getElementsByName("desplazamiento");
		for (var x = 0; x < checkboxDesplazamiento.length; x ++) {
			if(checkboxDesplazamiento[x].checked) {
				ckbDesplazamiento=checkboxDesplazamiento[x].id;
			}                               
		}
			
	//Checkbox Extrusion
	var ckbExtrusion = "";
	var checkboxExtrusion = document.getElementsByName("extrusion");
		for (var x = 0; x < checkboxExtrusion.length; x ++) {
			if(checkboxExtrusion[x].checked) {
				ckbExtrusion=checkboxExtrusion[x].id;
			}                               
		}
			
	//Checkbox Intrusion
	var ckbIntrusion = "";
	var checkboxIntrusion = document.getElementsByName("intrusion");
		for (var x = 0; x < checkboxIntrusion.length; x ++) {
			if(checkboxIntrusion[x].checked) {
				ckbIntrusion=checkboxIntrusion[x].id;
			}                               
		}
			
	//Checkbox Avulsion
	var ckbAvulsion = "";
	var checkboxAvulsion = document.getElementsByName("avulsion");
		for (var x = 0; x < checkboxAvulsion.length; x ++) {
			if(checkboxAvulsion[x].checked) {
				ckbAvulsion=checkboxAvulsion[x].id;
			}                               
		}
	
	var herida=document.getElementById("herida").value;
	
	//Checkbox Manchas Blancas
	var ckbManchasBlancas = "";
	var checkboxManchasBlancas = document.getElementsByName("manchas_blancas");
		for (var x = 0; x < checkboxManchasBlancas.length; x ++) {
			if(checkboxManchasBlancas[x].checked) {
				ckbManchasBlancas=checkboxManchasBlancas[x].id;
			}                               
		}
		
	//Checkbox Cafes
	var ckbCafes = "";
	var checkboxCafes = document.getElementsByName("cafes");
		for (var x = 0; x < checkboxCafes.length; x ++) {
			if(checkboxCafes[x].checked) {
				ckbCafes=checkboxCafes[x].id;
			}                               
		}
			
	//Checkbox Caries Cavitacionales
	var ckbCariesCavitacionales = "";
	var checkboxCariesCavitacionales = document.getElementsByName("caries_cavitacionales");
		for (var x = 0; x < checkboxCariesCavitacionales.length; x ++) {
			if(checkboxCariesCavitacionales[x].checked) {
				ckbCariesCavitacionales=checkboxCariesCavitacionales[x].id;
			}                               
		}
			
	//Checkbox Placa Bacteriana
	var ckbPlacaBacteriana = "";
	var checkboxPlacaBacteriana = document.getElementsByName("placa_bacteriana");
		for (var x = 0; x < checkboxPlacaBacteriana.length; x ++) {
			if(checkboxPlacaBacteriana[x].checked) {
				ckbPlacaBacteriana=checkboxPlacaBacteriana[x].id;
			}                               
		}
	
	var observacionesSaludBucal=document.getElementById("obs_salud_bucal").value;
	
		/*////////////////RESPUESTAS VERIFICAR SALUD BUCAL///////////////////////////////*/
	//Checkbox Celulitis Facial
	var ckbCelulitisFacial = "";
	var checkboxCelulitisFacial = document.getElementsByName("celulitis_facial");
		for (var x = 0; x < checkboxCelulitisFacial.length; x ++) {
			if(checkboxCelulitisFacial[x].checked) {
				ckbCelulitisFacial=checkboxCelulitisFacial[x].id;
			}                               
		}
			
	//Checkbox Enfermedad Bucal Grave
	var ckbEnfBucalGrave = "";
	var checkboxEnfBucalGrave = document.getElementsByName("enf_bucal_grave");
		for (var x = 0; x < checkboxEnfBucalGrave.length; x ++) {
			if(checkboxEnfBucalGrave[x].checked) {
				ckbEnfBucalGrave=checkboxEnfBucalGrave[x].id;
			}                               
		}
			
	//Checkbox Trauma Bucodental
	var ckbTraumaBucodental = "";
	var checkboxTraumaBucodental = document.getElementsByName("trauma_bucodental");
		for (var x = 0; x < checkboxTraumaBucodental.length; x ++) {
			if(checkboxTraumaBucodental[x].checked) {
				ckbTraumaBucodental=checkboxTraumaBucodental[x].id;
			}                               
		}
	
	//Checkbox Estomatitis
	var ckbEstomatitis = "";
	var checkboxEstomatitis = document.getElementsByName("estomatitis");
		for (var x = 0; x < checkboxEstomatitis.length; x ++) {
			if(checkboxEstomatitis[x].checked) {
				ckbEstomatitis=checkboxEstomatitis[x].id;
			}                               
		}
		
	//Checkbox Enfermedad Dental
	var ckbEnfDental = "";
	var checkboxEnfDental = document.getElementsByName("enf_dental");
		for (var x = 0; x < checkboxEnfDental.length; x ++) {
			if(checkboxEnfDental[x].checked) {
				ckbEnfDental=checkboxEnfDental[x].id;
			}                               
		}
			
	//Checkbox Alto Riesgo Enfermedad Bucal
	var ckbAltoRiesgoEnfBucal = "";
	var checkboxAltoRiesgoEnfBucal = document.getElementsByName("alto_riesgo_enf_bucal");
		for (var x = 0; x < checkboxAltoRiesgoEnfBucal.length; x ++) {
			if(checkboxAltoRiesgoEnfBucal[x].checked) {
				ckbAltoRiesgoEnfBucal=checkboxAltoRiesgoEnfBucal[x].id;
			}                               
		}
			
	//Checkbox Bajo Riesgo Enfermedad Bucal
	var ckbBajoRiesgoEnfBucal = "";
	var checkboxBajoRiesgoEnfBucal = document.getElementsByName("bajo_riesgo_enf_bucal");
		for (var x = 0; x < checkboxBajoRiesgoEnfBucal.length; x ++) {
			if(checkboxBajoRiesgoEnfBucal[x].checked) {
				ckbBajoRiesgoEnfBucal=checkboxBajoRiesgoEnfBucal[x].id;
			}                               
		}
	
	/******************************************************************************************************/
	/*************************************VERIFICAR CRECIMIENTO********************************************/
	/******************************************************************************************************/
	//Radio Emanacion Visible
	var radEmanacionVisible = "";
	var radioEmanacionVisible = document.getElementsByName("emanacion_visible");
		for (var x = 0; x < radioEmanacionVisible.length; x ++) {
			if(radioEmanacionVisible[x].checked) {
				radEmanacionVisible=radioEmanacionVisible[x].id;
			}                               
		}
	if (radEmanacionVisible==false) {
		alert("Seleccione si tiene emanacion visible, en la Seccion Verificar Crecimiento");
	} else {
	
	var pesosEdad=document.getElementById("peso_edad").value;
	if (pesosEdad=="") {
		alert("Escriba el peso para la edad");
		document.getElementById("peso_edad").focus();
	} else {
	
	//Radio Edema Pies
	var radEdemaPies = "";
	var radioEdemaPies = document.getElementsByName("rad_edema_pies");
		for (var x = 0; x < radioEdemaPies.length; x ++) {
			if(radioEdemaPies[x].checked) {
				radEdemaPies=radioEdemaPies[x].id;
			}                               
		}
	if (radEdemaPies==false) {
		alert("Seleccione si tiene edema en ambos pies, en la Seccion Verificar Crecimiento");
	} else {
		
	var apariencia=document.getElementById("apariencia").value;
	if (apariencia=="") {
		alert("Escriba la apariencia");
		document.getElementById("apariencia").focus();
	} else {
		
	var IMCEdad=document.getElementById("imc_edad").value;
	if (IMCEdad=="") {
		alert("Escriba el IMC para la edad");
		document.getElementById("imc_edad").focus();
	} else {
	
	var tallaEdad=document.getElementById("talla_edad").value;
	if (tallaEdad=="") {
		alert("Escriba la talla para la edad");
		document.getElementById("talla_edad").focus();
	} else {
	
	var pesosTalla=document.getElementById("peso_talla").value;
	if (pesosTalla=="") {
		alert("Escriba el peso para la talla");
		document.getElementById("peso_talla").focus();
	} else {
	
	var tendenciasPeso=document.getElementById("tendencia_peso").value;
	if (tendenciasPeso=="Seleccione") {
		alert("Elija la tendencia del peso");
		document.getElementById("tendencia_peso").focus();
	} else {
	
	//Checkbox Desnutricion Global Severa
	var ckbDesnutricionGlobalSevera = "";
	var checkboxDesnutricionGlobalSevera = document.getElementsByName("desnutricion_global_severa");
		for (var x = 0; x < checkboxDesnutricionGlobalSevera.length; x ++) {
			if(checkboxDesnutricionGlobalSevera[x].checked) {
				ckbDesnutricionGlobalSevera=checkboxDesnutricionGlobalSevera[x].id;
			}                               
		}
		
	//Checkbox Desnutricion Global
	var ckbDesnutricionGlobal= "";
	var checkboxDesnutricionGlobal = document.getElementsByName("desnutricion_global");
		for (var x = 0; x < checkboxDesnutricionGlobal.length; x ++) {
			if(checkboxDesnutricionGlobal[x].checked) {
				ckbDesnutricionGlobal=checkboxDesnutricionGlobal[x].id;
			}                               
		}	
		
	//Checkbox Riesgo Desnutricion
	var ckbRiesgoDesnutricion = "";
	var checkboxRiesgoDesnutricion= document.getElementsByName("riesgo_desnutricion");
		for (var x = 0; x < checkboxRiesgoDesnutricion.length; x ++) {
			if(checkboxRiesgoDesnutricion[x].checked) {
				ckbRiesgoDesnutricion=checkboxRiesgoDesnutricion[x].id;
			}                               
		}
		
	//Checkbox Peso Adecuado Edad
	var ckbPesoAdecuadoEdad= "";
	var checkboxPesoAdecuadoEdad = document.getElementsByName("peso_adecuado_edad");
		for (var x = 0; x < checkboxPesoAdecuadoEdad.length; x ++) {
			if(checkboxPesoAdecuadoEdad[x].checked) {
				ckbPesoAdecuadoEdad=checkboxPesoAdecuadoEdad[x].id;
			}                               
		}
		
	//Checkbox Desnutricion Cronica
	var ckbDesnutricionCronica = "";
	var checkboxDesnutricionCronica = document.getElementsByName("desnutricion_cronica");
		for (var x = 0; x < checkboxDesnutricionCronica.length; x ++) {
			if(checkboxDesnutricionCronica[x].checked) {
				ckbDesnutricionCronica=checkboxDesnutricionCronica[x].id;
			}                               
		}
				
	//Checkbox Riesgo DNT
	var ckbRiesgoDNT= "";
	var checkboxRiesgoDNT = document.getElementsByName("riesgo_DNT");
		for (var x = 0; x < checkboxRiesgoDNT.length; x ++) {
			if(checkboxRiesgoDNT[x].checked) {
				ckbRiesgoDNT=checkboxRiesgoDNT[x].id;
			}                               
		}
		
	//Checkbox Talla Adecuada Edad
	var ckbTallaAdecuadaEdad = "";
	var checkboxTallaAdecuadaEdad = document.getElementsByName("talla_adecuada_edad");
		for (var x = 0; x < checkboxTallaAdecuadaEdad.length; x ++) {
			if(checkboxTallaAdecuadaEdad[x].checked) {
				ckbTallaAdecuadaEdad=checkboxTallaAdecuadaEdad[x].id;
			}                               
		}
					
	//Checkbox Desnutricion Aguda
	var ckbDesnutricionAguda= "";
	var checkboxDesnutricionAguda = document.getElementsByName("desnutricion_aguda");
		for (var x = 0; x < checkboxDesnutricionAguda.length; x ++) {
			if(checkboxDesnutricionAguda[x].checked) {
				ckbDesnutricionAguda=checkboxDesnutricionAguda[x].id;
			}                               
		}	
		
	//Checkbox DNT Aguda
	var ckbDNTAguda = "";
	var checkboxDNTAguda = document.getElementsByName("DNT_aguda");
		for (var x = 0; x < checkboxDNTAguda.length; x ++) {
			if(checkboxDNTAguda[x].checked) {
				ckbDNTAguda=checkboxDNTAguda[x].id;
			}                               
		}
						
	//Checkbox Peso Adecuado Talla
	var ckbPesoAdecuadoTalla= "";
	var checkboxPesoAdecuadoTalla = document.getElementsByName("peso_adecuado_talla");
		for (var x = 0; x < checkboxPesoAdecuadoTalla.length; x ++) {
			if(checkboxPesoAdecuadoTalla[x].checked) {
				ckbPesoAdecuadoTalla=checkboxPesoAdecuadoTalla[x].id;
			}                               
		}
		
	//Checkbox Sobrepeso
	var ckbSobrepeso = "";
	var checkboxSobrepeso = document.getElementsByName("sobrepeso");
		for (var x = 0; x < checkboxSobrepeso.length; x ++) {
			if(checkboxSobrepeso[x].checked) {
				ckbSobrepeso=checkboxSobrepeso[x].id;
			}                               
		}
							
	//Checkbox Obesidad
	var ckbObesidad= "";
	var checkboxObesidad = document.getElementsByName("obesidad");
		for (var x = 0; x < checkboxObesidad.length; x ++) {
			if(checkboxObesidad[x].checked) {
				ckbObesidad=checkboxObesidad[x].id;
			}                               
		}
		
	var observacionesCrecimiento=document.getElementById("obs_crecimiento");
		
		/*////////////////RESPUESTAS VERIFICAR CRECIMIENTO///////////////////////////////*/
	//Checkbox Obeso
	var ckbObeso = "";
	var checkboxObeso = document.getElementsByName("obeso");
		for (var x = 0; x < checkboxObeso.length; x ++) {
			if(checkboxObeso[x].checked) {
				ckbObeso=checkboxObeso[x].id;
			}                               
		}
								
	//Checkbox Sobrepeso Respuesta
	var ckbSobrepesoR= "";
	var checkboxSobrepesoR = document.getElementsByName("sobrepeso_r");
		for (var x = 0; x < checkboxSobrepesoR.length; x ++) {
			if(checkboxSobrepesoR[x].checked) {
				ckbSobrepesoR=checkboxSobrepesoR[x].id;
			}                               
		}
		
	//Checkbox Desnutricion Severa
	var ckbDesnutricionSevera = "";
	var checkboxDesnutricionSevera = document.getElementsByName("desnutricion_severa");
		for (var x = 0; x < checkboxDesnutricionSevera.length; x ++) {
			if(checkboxDesnutricionSevera[x].checked) {
				ckbDesnutricionSevera=checkboxDesnutricionSevera[x].id;
			}                               
		}
									
	//Checkbox Desnutricion
	var ckbDesnutricion= "";
	var checkboxDesnutricion = document.getElementsByName("desnutricion");
		for (var x = 0; x < checkboxDesnutricion.length; x ++) {
			if(checkboxDesnutricion[x].checked) {
				ckbDesnutricion=checkboxDesnutricion[x].id;
			}                               
		}
		
	//Checkbox Riesgo Desnutricion Respuesta
	var ckbRiesgoDesnutricionR = "";
	var checkboxRiesgoDesnutricionR = document.getElementsByName("riesgo_desnutricion_r");
		for (var x = 0; x < checkboxRiesgoDesnutricionR.length; x ++) {
			if(checkboxRiesgoDesnutricionR[x].checked) {
				ckbRiesgoDesnutricionR=checkboxRiesgoDesnutricionR[x].id;
			}                               
		}
										
	//Checkbox Estado Nutricional
	var ckbEstadoNutricional= "";
	var checkboxEstadoNutricional = document.getElementsByName("estado_nutricional");
		for (var x = 0; x < checkboxEstadoNutricional.length; x ++) {
			if(checkboxEstadoNutricional[x].checked) {
				ckbEstadoNutricional=checkboxEstadoNutricional[x].id;
			}                               
		}
		
	/******************************************************************************************************/
	/*************************************VERIFICAR ANEMIA*************************************************/
	/******************************************************************************************************/
	//Checkbox Recibido Hierro
	var radRecibidoHierro = "";
	var radioRecibidoHierro = document.getElementsByName("rad_recibido_hierro");
		for (var x = 0; x < radioRecibidoHierro.length; x ++) {
			if(radioRecibidoHierro[x].checked) {
				radRecibidoHierro=radioRecibidoHierro[x].id;
			}                               
		}
	if (radRecibidoHierro==false) {
		alert("Seleccione si ha recibido hierro, en la Seccion Verificar Anemia");
	} else {
								
	var cuandoRecibidoHierro=document.getElementById("cuando_recibido_hierro").value;
	var cuantoTiempoRecibidoHierro=document.getElementById("cuanto_tiempo_recibido_hierro").value;
	var palidezPalmar=document.getElementById("palidez_palmar").value;
	if (palidezPalmar=="Seleccione") {
		alert("Elija la palidez palmar");
		document.getElementById("palidez_palmar").focus();
	} else {
	
	var palidezConjuntival=document.getElementById("palidez_conjuntival").value;
	if (palidezConjuntival=="Seleccione") {
		alert("Elija la palidez conjuntival");
		document.getElementById("palidez_conjuntival").focus();
	} else {
	
	var observacionesAnemia=document.getElementById("obs_anemia").value;
	
		/*////////////////RESPUESTAS VERIFICAR ANEMIA///////////////////////////////*/
	//Checkbox Anemia Severa
	var ckbAnemiaSevera= "";
	var checkboxAnemiaSevera = document.getElementsByName("anemia_severa");
		for (var x = 0; x < checkboxAnemiaSevera.length; x ++) {
			if(checkboxAnemiaSevera[x].checked) {
				ckbAnemiaSevera=checkboxAnemiaSevera[x].id;
			}                               
		}
		
	//Checkbox Anemia
	var ckbAnemia = "";
	var checkboxAnemia = document.getElementsByName("anemia");
		for (var x = 0; x < checkboxAnemia.length; x ++) {
			if(checkboxAnemia[x].checked) {
				ckbAnemia=checkboxAnemia[x].id;
			}                               
		}
												
	//Checkbox No Tiene Anemia
	var ckbNoTieneAnemia= "";
	var checkboxNoTieneAnemia = document.getElementsByName("no_tiene_anemia");
		for (var x = 0; x < checkboxNoTieneAnemia.length; x ++) {
			if(checkboxNoTieneAnemia[x].checked) {
				ckbNoTieneAnemia=checkboxNoTieneAnemia[x].id;
			}                               
		}
		
	/******************************************************************************************************/
	/*************************************VERIFICAR MALTRATO***********************************************/
	/******************************************************************************************************/
	var produjeronLesiones=document.getElementById("produjeron_lesiones").value;
	if (produjeronLesiones=="") {
		alert("Escriba como se produjeron lesiones");
		document.getElementById("produjeron_lesiones").focus();
	} else {
		
	//Radio Relata Maltrato
	var radRelataMaltrato = "";
	var radioRelataMaltrato = document.getElementsByName("rad_relata_maltrato");
		for (var x = 0; x < radioRelataMaltrato.length; x ++) {
			if(radioRelataMaltrato[x].checked) {
				radRelataMaltrato=radioRelataMaltrato[x].id;
			}                               
		}
	if (radRelataMaltrato==false) {
		alert("Seleccione si relata maltrato, en la Seccion Verificar Maltrato");
	} else {
													
	//Checkbox Fisico
	var ckbFisico= "";
	var checkboxFisico = document.getElementsByName("malt_fisico");
		for (var x = 0; x < checkboxFisico.length; x ++) {
			if(checkboxFisico[x].checked) {
				ckbFisico=checkboxFisico[x].id;
			}                               
		}	
		
	//Checkbox Sexual
	var ckbSexual = "";
	var checkboxSexual = document.getElementsByName("malt_sexual");
		for (var x = 0; x < checkboxSexual.length; x ++) {
			if(checkboxSexual[x].checked) {
				ckbSexual=checkboxSexual[x].id;
			}                               
		}
														
	//Checkbox Negligencia
	var ckbNegligencia= "";
	var checkboxNegligencia = document.getElementsByName("negligencia");
		for (var x = 0; x < checkboxNegligencia.length; x ++) {
			if(checkboxNegligencia[x].checked) {
				ckbNegligencia=checkboxNegligencia[x].id;
			}                               
		}	
		
	var quien_maltrato=document.getElementById("quien_maltrato").value;
	
	//Radio Incongruencia Trauma
	var radIncongruenciaTrauma = "";
	var radioIncongruenciaTrauma = document.getElementsByName("rad_incongruencia_trauma");
		for (var x = 0; x < radioIncongruenciaTrauma.length; x ++) {
			if(radioIncongruenciaTrauma[x].checked) {
				radIncongruenciaTrauma=radioIncongruenciaTrauma[x].id;
			}                               
		}
	if (radIncongruenciaTrauma==false) {
		alert("Seleccione si hay incongruencia para explicar un trauma significante, en la Seccion Verificar Maltrato");
	} else {
															
	//Radio Existe Incongruencia
	var radExisteIncongruencia= "";
	var radioExisteIncongruencia = document.getElementsByName("rad_existe_incongruencia");
		for (var x = 0; x < radioExisteIncongruencia.length; x ++) {
			if(radioExisteIncongruencia[x].checked) {
				radExisteIncongruencia=radioExisteIncongruencia[x].id;
			}                               
		}
	if (radExisteIncongruencia==false) {
		alert("Seleccione si existe incongruencia en lesion - edad - desarrollo del niño, en la Seccion Verificar Maltrato");
	} else {
		
	var diferentesVersiones=document.getElementById("diferentes_versiones").value;
																
	//Radio Tardia Consulta
	var radTardiaConsulta= "";
	var radioTardiaConsulta = document.getElementsByName("rad_tardia_consulta");
		for (var x = 0; x < radioTardiaConsulta.length; x ++) {
			if(radioTardiaConsulta[x].checked) {
				radTardiaConsulta=radioTardiaConsulta[x].id;
			}                               
		}
	if (radTardiaConsulta==false) {
		alert("Seleccione si es tardia la consulta, en la Seccion Verificar Maltrato");
	} else {
		
	var frecuenciaPegar=document.getElementById("frecuencia_pegar").value;
	if (frecuenciaPegar=="") {
		alert("Escriba con que frecuencia se ve obligado a pegarle a su hijo");
		document.getElementById("frecuencia_pegar").focus();
	} else {
	
	var desobedienteHijo=document.getElementById("desobediente_hijo").value;
	if (desobedienteHijo=="") {
		alert("Escriba que tan desobediente es su hijo que se ve obligado a pegarle");
		document.getElementById("desobediente_hijo").focus();
	} else {
	
	var comportamientoAnormalPadres=document.getElementById("comportamiento_anormal_padres").value;
	if (comportamientoAnormalPadres=="Seleccione") {
		alert("Elija el comportamiento anormal del padre");
		document.getElementById("comportamiento_anormal_padres").focus();
	} else {
		
	//Radio Descuidado Nino
	var radDescuidadoNino = "";
	var radioDescuidadoNino = document.getElementsByName("rad_descuidado_nino");
		for (var x = 0; x < radioDescuidadoNino.length; x ++) {
			if(radioDescuidadoNino[x].checked) {
				radDescuidadoNino=radioDescuidadoNino[x].id;
			}                               
		}
	if (radDescuidadoNino==false) {
		alert("Seleccione si esta descuidado el nino en su salud, en la Seccion Verificar Maltrato");
	} else {
		
	var porDescuidadoNino=document.getElementById("por_descuidado_nino").value;
	var descuidadoNinoEn=document.getElementById("descuidado_nino_en").value;
	var factorRiesgo=document.getElementById("factor_riesgo").value;
	if (factorRiesgo=="Seleccione") {
		alert("Elija el factor de riesgo");
		document.getElementById("factor_riesgo").focus();
	} else {
	
	//Radio Actitud Anormal
	var radActitudAnormal= "";
	var radioActitudAnormal = document.getElementsByName("rad_actitud_anormal");
		for (var x = 0; x < radioActitudAnormal.length; x ++) {
			if(radioActitudAnormal[x].checked) {
				radActitudAnormal=radioActitudAnormal[x].id;
			}                               
		}
	if (radActitudAnormal==false) {
		alert("Seleccione si el nino tiene actitud anormal, en la Seccion Verificar Maltrato");
	} else {
	
	//Checkbox Fracturas
	var ckbFracturas = "";
	var checkboxFracturas = document.getElementsByName("fracturas");
		for (var x = 0; x < checkboxFracturas.length; x ++) {
			if(checkboxFracturas[x].checked) {
				ckbFracturas=checkboxFracturas[x].id;
			}                               
		}
																		
	//Checkbox Hematomas
	var ckbHematomas = "";
	var checkboxHematomas = document.getElementsByName("hematomas");
		for (var x = 0; x < checkboxHematomas.length; x ++) {
			if(checkboxHematomas[x].checked) {
				ckbHematomas=checkboxHematomas[x].id;
			}                               
		}
		
	//Checkbox Hemorragias Retinianas
	var ckbHemorragiasRetinianas = "";
	var checkboxHemorragiasRetinianas = document.getElementsByName("hemorragias_retinianas");
		for (var x = 0; x < checkboxHemorragiasRetinianas.length; x ++) {
			if(checkboxHemorragiasRetinianas[x].checked) {
				ckbHemorragiasRetinianas=checkboxHemorragiasRetinianas[x].id;
			}                               
		}
																			
	//Checkbox Areas Ropas
	var ckbAreasRopa = "";
	var checkboxAreasRopa = document.getElementsByName("areas_ropa");
		for (var x = 0; x < checkboxAreasRopa.length; x ++) {
			if(checkboxAreasRopa[x].checked) {
				ckbAreasRopa=checkboxAreasRopa[x].id;
			}                               
		}
	
	//Checkbox Patron Simetrico
		var ckbPatronSimetrico = "";
		var checkboxPatronSimetrico = document.getElementsByName("patron_simetrico");
			for (var x = 0; x < checkboxPatronSimetrico.length; x ++) {
				if(checkboxPatronSimetrico[x].checked) {
					ckbPatronSimetrico=checkboxPatronSimetrico[x].id;
				}                               
			}
			
	//Checkbox Denota Objeto
	var ckbDenotaObjeto = "";
	var checkboxDenotaObjeto = document.getElementsByName("denota_objeto");
		for (var x = 0; x < checkboxDenotaObjeto.length; x ++) {
			if(checkboxDenotaObjeto[x].checked) {
				ckbDenotaObjeto=checkboxDenotaObjeto[x].id;
			}                               
		}
		
	//Checkbox En Espalda
	var ckbEnEspalda = "";
	var checkboxEnEspalda = document.getElementsByName("en_espalda");
		for (var x = 0; x < checkboxEnEspalda.length; x ++) {
			if(checkboxEnEspalda[x].checked) {
				ckbEnEspalda=checkboxEnEspalda[x].id;
			}                               
		}
		
	//Checkbox Equimosis
	var ckbxEquimosis = "";
	var checkboxEquimosis = document.getElementsByName("equimosis");
		for (var x = 0; x < checkboxEquimosis.length; x ++) {
			if(checkboxEquimosis[x].checked) {
				ckbxEquimosis=checkboxEquimosis[x].id;
			}                               
		}
		
	//Checkbox Hematomas Maltrato
	var ckbHematomasMaltrato = "";
	var checkboxHematomasMaltrato = document.getElementsByName("hematomas_maltrato");
		for (var x = 0; x < checkboxHematomasMaltrato.length; x ++) {
			if(checkboxHematomasMaltrato[x].checked) {
				ckbHematomasMaltrato=checkboxHematomasMaltrato[x].id;
			}                               
		}
		
	//Checkbox Lasceraciones
	var ckbLasceraciones = "";
	var checkboxLasceraciones = document.getElementsByName("lasceraciones");
		for (var x = 0; x < checkboxLasceraciones.length; x ++) {
			if(checkboxLasceraciones[x].checked) {
				ckbLasceraciones=checkboxLasceraciones[x].id;
			}                               
		}
		
	//Checkbox Mordiscos
	var ckbMordiscos = "";
	var checkboxMordiscos = document.getElementsByName("mordiscos");
		for (var x = 0; x < checkboxMordiscos.length; x ++) {
			if(checkboxMordiscos[x].checked) {
				ckbMordiscos=checkboxMordiscos[x].id;
			}                               
		}
		
	//Checkbox Cicatrices
	var ckbCicatrices = "";
	var checkboxCicatrices = document.getElementsByName("cicatrices");
		for (var x = 0; x < checkboxCicatrices.length; x ++) {
			if(checkboxCicatrices[x].checked) {
				ckbCicatrices=checkboxCicatrices[x].id;
			}                               
		}
		
	//Checkbox Diferente Evolucion
	var ckbDiferenteEvolucion = "";
	var checkboxDiferenteEvolucion = document.getElementsByName("diferente_evolucion");
		for (var x = 0; x < checkboxDiferenteEvolucion.length; x ++) {
			if(checkboxDiferenteEvolucion[x].checked) {
				ckbDiferenteEvolucion=checkboxDiferenteEvolucion[x].id;
			}                               
		}
		
	//Checkbox Sugestivas Maltrato
	var ckbSugestivasMaltrato = "";
	var checkboxSugestivasMaltrato = document.getElementsByName("sugestivas_maltrato");
		for (var x = 0; x < checkboxSugestivasMaltrato.length; x ++) {
			if(checkboxSugestivasMaltrato[x].checked) {
				ckbSugestivasMaltrato=checkboxSugestivasMaltrato[x].id;
			}                               
		}
		
	//Checkbox Costillas
	var ckbCostillas = "";
	var checkboxCostillas = document.getElementsByName("costillas");
		for (var x = 0; x < checkboxCostillas.length; x ++) {
			if(checkboxCostillas[x].checked) {
				ckbCostillas=checkboxCostillas[x].id;
			}                               
		}
		
	//Checkbox Huesos Largos
	var ckbHuesosLargos = "";
	var checkboxHuesosLargos = document.getElementsByName("huesos_largos");
		for (var x = 0; x < checkboxHuesosLargos.length; x ++) {
			if(checkboxHuesosLargos[x].checked) {
				ckbHuesosLargos=checkboxHuesosLargos[x].id;
			}                               
		}
		
	//Checkbox Espirales
	var ckbEspirales = "";
	var checkboxEspirales = document.getElementsByName("espirales");
		for (var x = 0; x < checkboxEspirales.length; x ++) {
			if(checkboxEspirales[x].checked) {
				ckbEspirales=checkboxEspirales[x].id;
			}                               
		}
		
	//Checkbox Oblicua
	var ckbOblicua = "";
	var checkboxOblicua = document.getElementsByName("oblicua");
		for (var x = 0; x < checkboxOblicua.length; x ++) {
			if(checkboxOblicua[x].checked) {
				ckbOblicua=checkboxOblicua[x].id;
			}                               
		}
		
	//Checkbox Metafisiarias
	var ckbMetafisiarias = "";
	var checkboxMetafisiarias = document.getElementsByName("metafisiarias");
		for (var x = 0; x < checkboxMetafisiarias.length; x ++) {
			if(checkboxMetafisiarias[x].checked) {
				ckbMetafisiarias=checkboxMetafisiarias[x].id;
			}                               
		}
		
	//Checkbox Esternon
	var ckbEsternon = "";
	var checkboxEsternon = document.getElementsByName("esternon");
		for (var x = 0; x < checkboxEsternon.length; x ++) {
			if(checkboxEsternon[x].checked) {
				ckbEsternon=checkboxEsternon[x].id;
			}                               
		}
		
	//Checkbox Escapula
	var ckbEscapula = "";
	var checkboxEscapula = document.getElementsByName("escapula");
		for (var x = 0; x < checkboxEscapula.length; x ++) {
			if(checkboxEscapula[x].checked) {
				ckbEscapula=checkboxEscapula[x].id;
			}                               
		}
		
	//Checkbox Menor Cinco Anos
	var ckbMenorCincoAnos = "";
	var checkboxMenorCincoAnos = document.getElementsByName("menor_cinco_anos");
		for (var x = 0; x < checkboxMenorCincoAnos.length; x ++) {
			if(checkboxMenorCincoAnos[x].checked) {
				ckbMenorCincoAnos=checkboxMenorCincoAnos[x].id;
			}                               
		}
		
	//Checkbox Trauma Visceral
	var ckbTraumaVisceral = "";
	var checkboxTraumaVisceral = document.getElementsByName("trauma_visceral");
		for (var x = 0; x < checkboxTraumaVisceral.length; x ++) {
			if(checkboxTraumaVisceral[x].checked) {
				ckbTraumaVisceral=checkboxTraumaVisceral[x].id;
			}                               
		}
		
	//Checkbox Trauma Grave
	var ckbTraumaGrave = "";
	var checkboxTraumaGrave = document.getElementsByName("trauma_grave");
		for (var x = 0; x < checkboxTraumaGrave.length; x ++) {
			if(checkboxTraumaGrave[x].checked) {
				ckbTraumaGrave=checkboxTraumaGrave[x].id;
			}                               
		}
		
	var lesionesSugestival=document.getElementById("lesiones_sugestival").value;
		
	//Checkbox Sangrado Vaginal
	var ckbSangradoVaginal = "";
	var checkboxSangradoVaginal = document.getElementsByName("sangrado_vaginal");
		for (var x = 0; x < checkboxSangradoVaginal.length; x ++) {
			if(checkboxSangradoVaginal[x].checked) {
				ckbSangradoVaginal=checkboxSangradoVaginal[x].id;
			}                               
		}
		
	//Checkbox Lasceracion Aguda
	var ckbLasceracionAguda = "";
	var checkboxLasceracionAguda = document.getElementsByName("lasceracion_aguda");
		for (var x = 0; x < checkboxLasceracionAguda.length; x ++) {
			if(checkboxLasceracionAguda[x].checked) {
				ckbLasceracionAguda=checkboxLasceracionAguda[x].id;
			}                               
		}
		
	//Checkbox LasceracionPerianal
	var ckbLasceracionPerianal = "";
	var checkboxLasceracionPerianal = document.getElementsByName("lasceracion_perianal");
		for (var x = 0; x < checkboxLasceracionPerianal.length; x ++) {
			if(checkboxLasceracionPerianal[x].checked) {
				ckbLasceracionPerianal=checkboxLasceracionPerianal[x].id;
			}                               
		}
		
	//Checkbox Ausencia Himen
	var ckbAusenciaHimen = "";
	var checkboxAusenciaHimen = document.getElementsByName("ausencia_himen");
		for (var x = 0; x < checkboxAusenciaHimen.length; x ++) {
			if(checkboxAusenciaHimen[x].checked) {
				ckbAusenciaHimen=checkboxAusenciaHimen[x].id;
			}                               
		}
		
	//Checkbox Himen Cicatrizado
	var ckbHimenCicatrizado = "";
	var checkboxHimenCicatrizado = document.getElementsByName("himen_cicatrizado");
		for (var x = 0; x < checkboxHimenCicatrizado.length; x ++) {
			if(checkboxHimenCicatrizado[x].checked) {
				ckbHimenCicatrizado=checkboxHimenCicatrizado[x].id;
			}                               
		}
		
	//Checkbox Cicatriz Navicular
	var ckbCicatrizNavicular = "";
	var checkboxCicatrizNavicular = document.getElementsByName("cicatriz_navicular");
		for (var x = 0; x < checkboxCicatrizNavicular.length; x ++) {
			if(checkboxCicatrizNavicular[x].checked) {
				ckbCicatrizNavicular=checkboxCicatrizNavicular[x].id;
			}                               
		}
		
	//Checkbox Ano Dilatado
	var ckbAnoDilatado = "";
	var checkboxAnoDilatado = document.getElementsByName("ano_dilatado");
		for (var x = 0; x < checkboxAnoDilatado.length; x ++) {
			if(checkboxAnoDilatado[x].checked) {
				ckbAnoDilatado=checkboxAnoDilatado[x].id;
			}                               
		}
		
	//Checkbox Halazgo Semen
	var ckbHalazgoSemen = "";
	var checkboxHalazgoSemen = document.getElementsByName("hallazgo_semen");
		for (var x = 0; x < checkboxHalazgoSemen.length; x ++) {
			if(checkboxHalazgoSemen[x].checked) {
				ckbHalazgoSemen=checkboxHalazgoSemen[x].id;
			}                               
		}
		
	//Checkbox Flujo Genital
	var ckbFlujoGenital = "";
	var checkboxFlujoGenital = document.getElementsByName("flujo_genital");
		for (var x = 0; x < checkboxFlujoGenital.length; x ++) {
			if(checkboxFlujoGenital[x].checked) {
				ckbFlujoGenital=checkboxFlujoGenital[x].id;
			}                               
		}
		
	//Checkbox Cuerpo Extrano
	var ckbCuerpoExtrano = "";
	var checkboxCuerpoExtrano = document.getElementsByName("cuerpo_extrano");
		for (var x = 0; x < checkboxCuerpoExtrano.length; x ++) {
			if(checkboxCuerpoExtrano[x].checked) {
				ckbCuerpoExtrano=checkboxCuerpoExtrano[x].id;
			}                               
		}
		
	//Checkbox Vesiculas Maltrato
	var ckbVesiculasMaltrato = "";
	var checkboxVesiculas = document.getElementsByName("vesiculas_maltrato");
		for (var x = 0; x < checkboxVesiculas.length; x ++) {
			if(checkboxVesiculas[x].checked) {
				ckbVesiculas=checkboxVesiculas[x].id;
			}                               
		}
		
	//Checkbox Juego Sexual
	var ckbJuegoSexual = "";
	var checkboxJuegoSexual = document.getElementsByName("juego_sexual");
		for (var x = 0; x < checkboxJuegoSexual.length; x ++) {
			if(checkboxJuegoSexual[x].checked) {
				ckbJuegoSexual=checkboxJuegoSexual[x].id;
			}                               
		}
		
	//Checkbox Boca Genitales
	var ckbBocaGenitales = "";
	var checkboxBocaGenitales = document.getElementsByName("boca_genitales");
		for (var x = 0; x < checkboxBocaGenitales.length; x ++) {
			if(checkboxBocaGenitales[x].checked) {
				ckbBocaGenitales=checkboxBocaGenitales[x].id;
			}                               
		}	
		
	//Checkbox VIH
	var ckbVIH = "";
	var checkboxVIH = document.getElementsByName("vih");
		for (var x = 0; x < checkboxVIH.length; x ++) {
			if(checkboxVIH[x].checked) {
				ckbVIH=checkboxVIH[x].id;
			}                               
		}
		
	//Checkbox Gonorrea
	var ckbGonorrea = "";
	var checkboxGonorrea = document.getElementsByName("gonorrea");
		for (var x = 0; x < checkboxGonorrea.length; x ++) {
			if(checkboxGonorrea[x].checked) {
				ckbGonorrea=checkboxGonorrea[x].id;
			}                               
		}
		
	//Checkbox Sifilis
	var ckbSifilis = "";
	var checkboxSifilis = document.getElementsByName("sifilis");
		for (var x = 0; x < checkboxSifilis.length; x ++) {
			if(checkboxSifilis[x].checked) {
				ckbSifilis=checkboxSifilis[x].id;
			}                               
		}
		
	//Checkbox Trichomona Vaginalis
	var ckbTrichomonaVaginalis = "";
	var checkboxTrichomonaVaginalis = document.getElementsByName("trichomona_vaginalis");
		for (var x = 0; x < checkboxTrichomonaVaginalis.length; x ++) {
			if(checkboxTrichomonaVaginalis[x].checked) {
				ckbTrichomonaVaginalis=checkboxTrichomonaVaginalis[x].id;
			}                               
		}
		
	//Checkbox Chlamydia Trachomatis
	var ckbChlamydiaTrachomatis = "";
	var checkboxChlamydiaTrachomatis = document.getElementsByName("chlamydia_trachomatis");
		for (var x = 0; x < checkboxChlamydiaTrachomatis.length; x ++) {
			if(checkboxChlamydiaTrachomatis[x].checked) {
				ckbChlamydiaTrachomatis=checkboxChlamydiaTrachomatis[x].id;
			}                               
		}
		
	//Checkbox Condilomatosis
	var ckbCondilomatosis = "";
	var checkboxCondilomatosis = document.getElementsByName("condilomatosis");
		for (var x = 0; x < checkboxCondilomatosis.length; x ++) {
			if(checkboxCondilomatosis[x].checked) {
				ckbCondilomatosis=checkboxCondilomatosis[x].id;
			}                               
		}
		
	//Checkbox Temeroso
	var ckbTemeroso = "";
	var checkboxTemeroso = document.getElementsByName("temeroso");
		for (var x = 0; x < checkboxTemeroso.length; x ++) {
			if(checkboxTemeroso[x].checked) {
				ckbTemeroso=checkboxTemeroso[x].id;
			}                               
		}
		
	//Checkbox Retraido
	var ckbRetraido = "";
	var checkboxRetraido = document.getElementsByName("retraido");
		for (var x = 0; x < checkboxRetraido.length; x ++) {
			if(checkboxRetraido[x].checked) {
				ckbRetraido=checkboxRetraido[x].id;
			}                               
		}
		
	//Checkbox Rechazo Adulto
	var ckbRechazoAdulto = "";
	var checkboxRechazoAdulto = document.getElementsByName("rechazo_adulto");
		for (var x = 0; x < checkboxRechazoAdulto.length; x ++) {
			if(checkboxRechazoAdulto[x].checked) {
				ckbRechazoAdulto=checkboxRechazoAdulto[x].id;
			}                               
		}
		
	//Checkbox Deprimido
	var ckbDeprimido = "";
	var checkboxDeprimido = document.getElementsByName("deprimido");
		for (var x = 0; x < checkboxDeprimido.length; x ++) {
			if(checkboxDeprimido[x].checked) {
				ckbDeprimido=checkboxDeprimido[x].id;
			}                               
		}
		
	//Checkbox Evita Contacto Visual
	var ckbEvitaContactoVisual = "";
	var checkboxEvitaContactoVisual = document.getElementsByName("evita_contacto_visual");
		for (var x = 0; x < checkboxEvitaContactoVisual.length; x ++) {
			if(checkboxEvitaContactoVisual[x].checked) {
				ckbEvitaContactoVisual=checkboxEvitaContactoVisual[x].id;
			}                               
		}
		
	//Checkbox Trastorno Sueño
	var ckbTrastornoSueno = "";
	var checkboxTrastornoSueno = document.getElementsByName("trastorno_sueno");
		for (var x = 0; x < checkboxTrastornoSueno.length; x ++) {
			if(checkboxTrastornoSueno[x].checked) {
				ckbTrastornoSueno=checkboxTrastornoSueno[x].id;
			}                               
		}
		
	//Checkbox Trastorno Alimentario
	var ckbTrastornoAlimentario = "";
	var checkboxTrastornoAlimentario = document.getElementsByName("trastorno_alimentario");
		for (var x = 0; x < checkboxTrastornoAlimentario.length; x ++) {
			if(checkboxTrastornoAlimentario[x].checked) {
				ckbTrastornoAlimentario=checkboxTrastornoAlimentario[x].id;
			}                               
		}
		
	//Checkbox Problemas Psicomaticos
	var ckbProblemasPsicomaticos = "";
	var checkboxProblemasPsicomaticos = document.getElementsByName("problemas_psicomaticos");
		for (var x = 0; x < checkboxProblemasPsicomaticos.length; x ++) {
			if(checkboxProblemasPsicomaticos[x].checked) {
				ckbProblemasPsicomaticos=checkboxProblemasPsicomaticos[x].id;
			}                               
		}
		
	//Checkbox Conductas Regresivas
	var ckbConductasRegresivas = "";
	var checkboxConductasRegresivas = document.getElementsByName("conductas_regresivas");
		for (var x = 0; x < checkboxConductasRegresivas.length; x ++) {
			if(checkboxConductasRegresivas[x].checked) {
				ckbConductasRegresivas=checkboxConductasRegresivas[x].id;
			}                               
		}
		
	//Checkbox Desarrollo Estancado
	var ckbDesarrolloEstancado = "";
	var checkboxDesarrolloEstancado = document.getElementsByName("desarrollo_estancado");
		for (var x = 0; x < checkboxDesarrolloEstancado.length; x ++) {
			if(checkboxDesarrolloEstancado[x].checked) {
				ckbDesarrolloEstancado=checkboxDesarrolloEstancado[x].id;
			}                               
		}
		
	//Checkbox Violencia Intrafamiliar
	var ckbViolenciaIntrafamiliar = "";
	var checkboxViolenciaIntrafamiliar = document.getElementsByName("violencia_intrafamiliar");
		for (var x = 0; x < checkboxViolenciaIntrafamiliar.length; x ++) {
			if(checkboxViolenciaIntrafamiliar[x].checked) {
				ckbViolenciaIntrafamiliar=checkboxViolenciaIntrafamiliar[x].id;
			}                               
		}
		
	//Checkbox Familia Caotica
	var ckbFamiliaCaotica = "";
	var checkboxFamiliaCaotica = document.getElementsByName("familia_caotica");
		for (var x = 0; x < checkboxFamiliaCaotica.length; x ++) {
			if(checkboxFamiliaCaotica[x].checked) {
				ckbFamiliaCaotica=checkboxFamiliaCaotica[x].id;
			}                               
		}
		
	//Checkbox Cuidadores Adictos
	var ckbCuidadoresAdictos = "";
	var checkboxCuidadoresAdictos = document.getElementsByName("cuidadores_adictos");
		for (var x = 0; x < checkboxCuidadoresAdictos.length; x ++) {
			if(checkboxCuidadoresAdictos[x].checked) {
				ckbCuidadoresAdictos=checkboxCuidadoresAdictos[x].id;
			}                               
		}
		
	var observacionesMaltrato=document.getElementById("obs_maltrato").value;
		
		/*////////////////RESPUESTAS VERIFICAR MALTRATO///////////////////////////////*/
	//Checkbox Maltrato Fisico Muy Grave
	var ckbMaltratoFisicoMuyGrave = "";
	var checkboxMaltratoFisicoMuyGrave = document.getElementsByName("maltrato_fisico_muy_grave");
		for (var x = 0; x < checkboxMaltratoFisicoMuyGrave.length; x ++) {
			if(checkboxMaltratoFisicoMuyGrave[x].checked) {
				ckbMaltratoFisicoMuyGrave=checkboxMaltratoFisicoMuyGrave[x].id;
			}                               
		}
		
	//Checkbox Abuso Grave
	var ckbAbusoGrave = "";
	var checkboxAbusoGrave = document.getElementsByName("abuso_grave");
		for (var x = 0; x < checkboxAbusoGrave.length; x ++) {
			if(checkboxAbusoGrave[x].checked) {
				ckbAbusoGrave=checkboxAbusoGrave[x].id;
			}                               
		}
		
	//Checkbox Maltrato Fisico
	var ckbMaltratoFisico = "";
	var checkboxMaltratoFisico = document.getElementsByName("maltrato_fisico");
		for (var x = 0; x < checkboxMaltratoFisico.length; x ++) {
			if(checkboxMaltratoFisico[x].checked) {
				ckbMaltratoFisico=checkboxMaltratoFisico[x].id;
			}                               
		}	
		
	//Checkbox Sospecha Abuso Sexual
	var ckbSospechaAbusoSexual = "";
	var checkboxSospechaAbusoSexual = document.getElementsByName("sospecha_abuso_sexual");
		for (var x = 0; x < checkboxSospechaAbusoSexual.length; x ++) {
			if(checkboxSospechaAbusoSexual[x].checked) {
				ckbSospechaAbusoSexual=checkboxSospechaAbusoSexual[x].id;
			}                               
		}
			
	//Checkbox Maltrato Emocional
	var ckbMaltratoEmocional = "";
	var checkboxMaltratoEmocional = document.getElementsByName("maltrato_emocional");
		for (var x = 0; x < checkboxMaltratoEmocional.length; x ++) {
			if(checkboxMaltratoEmocional[x].checked) {
				ckbMaltratoEmocional=checkboxMaltratoEmocional[x].id;
			}                               
		}
			
	//Checkbox Abandono
	var ckbAbandono = "";
	var checkboxAbandono = document.getElementsByName("abandono");
		for (var x = 0; x < checkboxAbandono.length; x ++) {
			if(checkboxAbandono[x].checked) {
				ckbAbandono=checkboxAbandono[x].id;
			}                               
		}
		
	//Checkbox No Sospecha Maltrato
	var ckbNoSospechaMaltrato = "";
	var checkboxNoSospechaMaltrato = document.getElementsByName("no_sospecha_maltrato");
		for (var x = 0; x < checkboxNoSospechaMaltrato.length; x ++) {
			if(checkboxNoSospechaMaltrato[x].checked) {
				ckbNoSospechaMaltrato=checkboxNoSospechaMaltrato[x].id;
			}                               
		}
		
	/******************************************************************************************************/
	/*************************************EVALUAR DESARROLLO***********************************************/
	/******************************************************************************************************/
	//Radio Antecedente Importante
	var radAntecedenteImportante = "";
	var radioAntecedenteImportante = document.getElementsByName("rad_antecedente_importante");
		for (var x = 0; x < radioAntecedenteImportante.length; x ++) {
			if(radioAntecedenteImportante[x].checked) {
				radAntecedenteImportante=radioAntecedenteImportante[x].id;
			}                               
		}
	if (radAntecedenteImportante==false) {
		alert("Seleccione si tiene algun antecedente importante en la seccion Desarrollo, en la Seccion Verificar Desarrollo");
	} else {
	
	var paraDesarrollo=document.getElementById("para_desarrollo").value;
	var algunFactorRiesgo=document.getElementById("algun_factor_riesgo").value;
	
	//Checkbox Realiza Condiciones Edad Uno
	var ckbRealizaCondEdadUno = "";
	var checkboxRealizaCondEdadUno = document.getElementsByName("realiza_cond_edad_uno");
		for (var x = 0; x < checkboxRealizaCondEdadUno.length; x ++) {
			if(checkboxRealizaCondEdadUno[x].checked) {
				ckbRealizaCondEdadUno=checkboxRealizaCondEdadUno[x].id;
			}                               
		}
	
	//Checkbox Realiza Condiciones Edad Dos
	var ckbRealizaCondEdadDos = "";
	var checkboxRealizaCondEdadDos = document.getElementsByName("realiza_cond_edad_dos");
		for (var x = 0; x < checkboxRealizaCondEdadDos.length; x ++) {
			if(checkboxRealizaCondEdadDos[x].checked) {
				ckbRealizaCondEdadDos=checkboxRealizaCondEdadDos[x].id;
			}                               
		}
		
	//Checkbox Realiza Condiciones Edad Tres
	var ckbRealizaCondEdadTres = "";
	var checkboxRealizaCondEdadTres = document.getElementsByName("realiza_cond_edad_tres");
		for (var x = 0; x < checkboxRealizaCondEdadTres.length; x ++) {
			if(checkboxRealizaCondEdadTres[x].checked) {
				ckbRealizaCondEdadTres=checkboxRealizaCondEdadTres[x].id;
			}                               
		}
		
	//Checkbox Realiza Condiciones Edad Cuatro
	var ckbRealizaCondEdadCuatro = "";
	var checkboxRealizaCondEdadCuatro = document.getElementsByName("realiza_cond_edad_cuatro");
		for (var x = 0; x < checkboxRealizaCondEdadCuatro.length; x ++) {
			if(checkboxRealizaCondEdadCuatro[x].checked) {
				ckbRealizaCondEdadCuatro=checkboxRealizaCondEdadCuatro[x].id;
			}                               
		}
		
	//Checkbox Ausencia Condiciones Edad Uno
	var ckbAusenciaCondEdadUno = "";
	var checkboxAusenciaCondEdadUno = document.getElementsByName("ausencia_cond_edad_uno");
		for (var x = 0; x < checkboxAusenciaCondEdadUno.length; x ++) {
			if(checkboxAusenciaCondEdadUno[x].checked) {
				ckbAusenciaCondEdadUno=checkboxAusenciaCondEdadUno[x].id;
			}                               
		}
	
	//Checkbox Ausencia Condiciones Edad Dos
	var ckbAusenciaCondEdadDos = "";
	var checkboxAusenciaCondEdadDos = document.getElementsByName("ausencia_cond_edad_dos");
		for (var x = 0; x < checkboxAusenciaCondEdadDos.length; x ++) {
			if(checkboxAusenciaCondEdadDos[x].checked) {
				ckbAusenciaCondEdadDos=checkboxAusenciaCondEdadDos[x].id;
			}                               
		}
		
	//Checkbox Ausencia Condiciones Edad Tres
	var ckbAusenciaCondEdadTres = "";
	var checkboxAusenciaCondEdadTres = document.getElementsByName("ausencia_cond_edad_tres");
		for (var x = 0; x < checkboxAusenciaCondEdadTres.length; x ++) {
			if(checkboxAusenciaCondEdadTres[x].checked) {
				ckbAusenciaCondEdadTres=checkboxAusenciaCondEdadTres[x].id;
			}                               
		}
		
	//Checkbox Ausencia Condiciones Edad Cuatro
	var ckbAusenciaCondEdadCuatro = "";
	var checkboxAusenciaCondEdadCuatro = document.getElementsByName("ausencia_cond_edad_cuatro");
		for (var x = 0; x < checkboxAusenciaCondEdadCuatro.length; x ++) {
			if(checkboxAusenciaCondEdadCuatro[x].checked) {
				ckbAusenciaCondEdadCuatro=checkboxAusenciaCondEdadCuatro[x].id;
			}                               
		}								
		
	//Checkbox Ausencia Condiciones Grupo Anterior Uno
	var ckbAusenciaCondGrupoAnteriorUno = "";
	var checkboxAusenciaCondGrupoAnteriorUno = document.getElementsByName("ausencia_cond_grupo_anterior_uno");
		for (var x = 0; x < checkboxAusenciaCondGrupoAnteriorUno.length; x ++) {
			if(checkboxAusenciaCondGrupoAnteriorUno[x].checked) {
				ckbAusenciaCondGrupoAnteriorUno=checkboxAusenciaCondGrupoAnteriorUno[x].id;
			}                               
		}
		
	//Checkbox Ausencia Condiciones Grupo Anterior Dos
	var ckbAusenciaCondGrupoAnteriorDos = "";
	var checkboxAusenciaCondGrupoAnteriorDos = document.getElementsByName("ausencia_cond_grupo_anterior_dos");
		for (var x = 0; x < checkboxAusenciaCondGrupoAnteriorDos.length; x ++) {
			if(checkboxAusenciaCondGrupoAnteriorDos[x].checked) {
				ckbAusenciaCondGrupoAnteriorDos=checkboxAusenciaCondGrupoAnteriorDos[x].id;
			}                               
		}
			
	//Checkbox Ausencia Condiciones Grupo Anterior Tres
	var ckbAusenciaCondGrupoAnteriorTres = "";
	var checkboxAusenciaCondGrupoAnteriorTres = document.getElementsByName("ausencia_cond_grupo_anterior_tres");
		for (var x = 0; x < checkboxAusenciaCondGrupoAnteriorTres.length; x ++) {
			if(checkboxAusenciaCondGrupoAnteriorTres[x].checked) {
				ckbAusenciaCondGrupoAnteriorTres=checkboxAusenciaCondGrupoAnteriorTres[x].id;
			}                               
		}
			
	//Checkbox Ausencia Condiciones Grupo Anterior Cuatro
	var ckbAusenciaCondGrupoAnteriorCuatro = "";
	var checkboxAusenciaCondGrupoAnteriorCuatro = document.getElementsByName("ausencia_cond_grupo_anterior_cuatro");
		for (var x = 0; x < checkboxAusenciaCondGrupoAnteriorCuatro.length; x ++) {
			if(checkboxAusenciaCondGrupoAnteriorCuatro[x].checked) {
				ckbAusenciaCondGrupoAnteriorCuatro=checkboxAusenciaCondGrupoAnteriorCuatro[x].id;
			}                               
		}
		
	var PCefalico=document.getElementById("PC").value;
	if (PCefalico=="") {
		alert("Escriba el perimetro cefalico en la seccion Desarrollo");
		document.getElementById("PC").focus();
	} else {

	var AlteracionesFenotipicas=document.getElementById("alteraciones_fenotipicas").value;
	if (AlteracionesFenotipicas=="") {
		alert("Escriba las alteraciones fenotipicas");
		document.getElementById("alteraciones_fenotipicas").focus();
	} else {
	
	//Checkbox Menos Dos DE
	var ckbMenosDosDE = "";
	var checkboxMenosDosDE = document.getElementsByName("menos_dos_de");
		for (var x = 0; x < checkboxMenosDosDE.length; x ++) {
			if(checkboxMenosDosDE[x].checked) {
				ckbMenosDosDE=checkboxMenosDosDE[x].id;
			}                               
		}
		
	//Checkbox Mas Dos DE
	var ckbMasDosDE = "";
	var checkboxMasDosDE = document.getElementsByName("mas_dos_de");
		for (var x = 0; x < checkboxMasDosDE.length; x ++) {
			if(checkboxMasDosDE[x].checked) {
				ckbMasDosDE=checkboxMasDosDE[x].id;
			}                               
		}
		
	var observacionesDesarrollo=document.getElementById("obs_desarrollo").value;
		
		/*////////////////RESPUESTAS EVALUAR DESARROLLO///////////////////////////////*/
	//Checkbox Probable Retraso Desarrollo
	var ckbProbRetrasoDesarrollo = "";
	var checkboxProbRetrasoDesarrollo = document.getElementsByName("prob_retraso_desarrollo");
		for (var x = 0; x < checkboxProbRetrasoDesarrollo.length; x ++) {
			if(checkboxProbRetrasoDesarrollo[x].checked) {
				ckbProbRetrasoDesarrollo=checkboxProbRetrasoDesarrollo[x].id;
			}                               
		}
		
	//Checkbox Riesgo Problema Desarrollo
	var ckbRiesgoProblemaDesarrollo = "";
	var checkboxRiesgoProblemaDesarrollo = document.getElementsByName("riesgo_problema_desarrollo");
		for (var x = 0; x < checkboxRiesgoProblemaDesarrollo.length; x ++) {
			if(checkboxRiesgoProblemaDesarrollo[x].checked) {
				ckbRiesgoProblemaDesarrollo=checkboxRiesgoProblemaDesarrollo[x].id;
			}                               
		}	
		
	//Checkbox Desarrollo Normal Riesgo
	var ckbDesarrolloNormalRiesgo = "";
	var checkboxDesarrolloNormalRiesgo = document.getElementsByName("desarrollo_normal_riesgo");
		for (var x = 0; x < checkboxDesarrolloNormalRiesgo.length; x ++) {
			if(checkboxDesarrolloNormalRiesgo[x].checked) {
				ckbDesarrolloNormalRiesgo=checkboxDesarrolloNormalRiesgo[x].id;
			}                               
		}
		
	//Checkbox Desarrollo Normal
	var ckbxDesarrolloNormal = "";
	var checkboxDesarrolloNormal = document.getElementsByName("desarrollo_normal");
		for (var x = 0; x < checkboxDesarrolloNormal.length; x ++) {
			if(checkboxDesarrolloNormal[x].checked) {
				ckbxDesarrolloNormal=checkboxDesarrolloNormal[x].id;
			}                               
		}
		
	/******************************************************************************************************/
	/*************************************ANTECEDENTES VACUNACION******************************************/
	/******************************************************************************************************/
		//Checkbox BCG Uno
		var ckbBCGUno = "";
		var checkboxBCGUno = document.getElementsByName("bcg_uno");
			for (var x = 0; x < checkboxBCGUno.length; x ++) {
				if(checkboxBCGUno[x].checked) {
					ckbBCGv=checkboxBCGUno[x].id;
				}                               
			}
			
		//Checkbox Hepatitis B RN
		var ckbHepatitisBRN = "";
		var checkboxHepatitisBRN = document.getElementsByName("hepatitis_b_rn");
			for (var x = 0; x < checkboxHepatitisBRN.length; x ++) {
				if(checkboxHepatitisBRN[x].checked) {
					ckbHepatitisBRN=checkboxHepatitisBRN[x].id;
				}                               
			}	
			
		//Checkbox Hepatitis B Uno
		var ckbHepatitisBUno = "";
		var checkboxHepatitisBUno = document.getElementsByName("hepatitis_b_uno");
			for (var x = 0; x < checkboxHepatitisBUno.length; x ++) {
				if(checkboxHepatitisBUno[x].checked) {
					ckbHepatitisBUno=checkboxHepatitisBUno[x].id;
				}                               
			}
			
		//Checkbox Hepatitis B Dos
		var ckbHepatitisBDos = "";
		var checkboxHepatitisBDos = document.getElementsByName("hepatitis_b_dos");
			for (var x = 0; x < checkboxHepatitisBDos.length; x ++) {
				if(checkboxHepatitisBDos[x].checked) {
					ckbHepatitisBDos=checkboxHepatitisBDos[x].id;
				}                               
			}	
			
		//Checkbox Hepatitis B Tres
		var ckbHepatitisBTres = "";
		var checkboxHepatitisBTres = document.getElementsByName("hepatitis_b_tres");
			for (var x = 0; x < checkboxHepatitisBTres.length; x ++) {
				if(checkboxHepatitisBTres[x].checked) {
					ckbHepatitisBTres=checkboxHepatitisBTres[x].id;
				}                               
			}
				
		//Checkbox DPT 1
		var ckbDPTUno = "";
		var checkboxDPTUno = document.getElementsByName("dpt_uno");
			for (var x = 0; x < checkboxDPTUno.length; x ++) {
				if(checkboxDPTUno[x].checked) {
					ckbDPTUno=checkboxDPTUno[x].id;
				}                               
			}	
				
		//Checkbox DPT 2
		var ckbDPTDos = "";
		var checkboxDPTDos = document.getElementsByName("dpt_dos");
			for (var x = 0; x < checkboxDPTDos.length; x ++) {
				if(checkboxDPTDos[x].checked) {
					ckbDPTDos=checkboxDPTDos[x].id;
				}                               
			}
				
		//Checkbox DPT 3
		var ckbDPTTres = "";
		var checkboxDPTTres = document.getElementsByName("dpt_tres");
			for (var x = 0; x < checkboxDPTTres.length; x ++) {
				if(checkboxDPTTres[x].checked) {
					ckbDPTTres=checkboxDPTTres[x].id;
				}                               
			}	
				
		//Checkbox DPT R1
		var ckbDPTRUno = "";
		var checkboxDPTRUno = document.getElementsByName("dpt_runo");
			for (var x = 0; x < checkboxDPTRUno.length; x ++) {
				if(checkboxDPTRUno[x].checked) {
					ckbDPTRUno=checkboxDPTRUno[x].id;
				}                               
			}
				
		//Checkbox DPT R2
		var ckbDPTRDos = "";
		var checkboxDPTRDos = document.getElementsByName("dpt_rdos");
			for (var x = 0; x < checkboxDPTRDos.length; x ++) {
				if(checkboxDPTRDos[x].checked) {
					ckbDPTRDos=checkboxDPTRDos[x].id;
				}                               
			}	
			
		//Checkbox VOP Uno
		var ckbVOPUno = "";
		var checkboxVOPUno = document.getElementsByName("vop_uno");
			for (var x = 0; x < checkboxVOPUno.length; x ++) {
				if(checkboxVOPUno[x].checked) {
					ckbVOPUno=checkboxVOPUno[x].id;
				}                               
			}
			
		//Checkbox VOP Dos
		var ckbVOPDos = "";
		var checkboxVOPDos = document.getElementsByName("VOP_dos");
			for (var x = 0; x < checkboxVOPDos.length; x ++) {
				if(checkboxVOPDos[x].checked) {
					ckbVOPDos=checkboxVOPDos[x].id;
				}                               
			}	
			
		//Checkbox VOP Tres
		var ckbVOPTres = "";
		var checkboxVOPTres = document.getElementsByName("VOP_tres");
			for (var x = 0; x < checkboxVOPTres.length; x ++) {
				if(checkboxVOPTres[x].checked) {
					ckbVOPTres=checkboxVOPTres[x].id;
				}                               
			}
			
		//Checkbox VOP R1
		var ckbVOPRUno = "";
		var checkboxVOPRUno = document.getElementsByName("VOP_runo");
			for (var x = 0; x < checkboxVOPRUno.length; x ++) {
				if(checkboxVOPRUno[x].checked) {
					ckbVOPRUno=checkboxVOPRUno[x].id;
				}                               
			}	
						
		//Checkbox VOP R2
		var ckbVOPRDos = "";
		var checkboxVOPRDos  = document.getElementsByName("VOP_rdos");
			for (var x = 0; x < checkboxVOPRDos .length; x ++) {
				if(checkboxVOPRDos [x].checked) {
					ckbVOPRDos=checkboxVOPRDos[x].id;
							}                               
						}
						
		//Checkbox SRP Uno
		var ckbSRPUno = "";
		var checkboxSRPUno = document.getElementsByName("srp_uno");
			for (var x = 0; x < checkboxSRPUno.length; x ++) {
				if(checkboxSRPUno[x].checked) {
					ckbSRPUno=checkboxSRPUno[x].id;
				}                               
			}	
			
		//Checkbox SRP Dos
		var ckbSRPDos = "";
		var checkboxSRPDos = document.getElementsByName("srp_dos");
				for (var x = 0; x < checkboxSRPDos.length; x ++) {
					if(checkboxSRPDos[x].checked) {
						ckbSRPDos=checkboxSRPDos[x].id;
					}                               
				}
							
		//Checkbox Rotavirus Uno
		var ckbRotavirusUno = "";
		var checkboxRotavirusUno = document.getElementsByName("rotavirus_uno");
				for (var x = 0; x < checkboxRotavirusUno.length; x ++) {
					if(checkboxRotavirusUno[x].checked) {
					ckbRotavirusUno=checkboxRotavirusUno[x].id;
					}                               
				}	
							
		//Checkbox Rotavirus Dos
		var ckbRotavirusDos = "";
		var checkboxRotavirusDos = document.getElementsByName("rotavirus_dos");
			for (var x = 0; x < checkboxRotavirusDos.length; x ++) {
				if(checkboxRotavirusDos[x].checked) {
					ckbRotavirusDos=checkboxRotavirusDos[x].id;
				}                               
			}
							
		//Checkbox Streptococo Neumoniae Uno
		var ckbStreptococoNeumoniaeUno = "";
		var checkboxStreptococoNeumoniaeUno = document.getElementsByName("streptococo_neumoniae_uno");
			for (var x = 0; x < checkboxStreptococoNeumoniaeUno.length; x ++) {
				if(checkboxStreptococoNeumoniaeUno[x].checked) {
					ckbStreptococoNeumoniaeUno=checkboxStreptococoNeumoniaeUno[x].id;
				}                               
			}
			
		//Checkbox Streptococo Neumoniae Dos
		var ckbStreptococoNeumoniaeDos = "";
		var checkboxStreptococoNeumoniaeDos = document.getElementsByName("streptococo_neumoniae_dos");
			for (var x = 0; x < checkboxStreptococoNeumoniaeDos.length; x ++) {
				if(checkboxStreptococoNeumoniaeDos[x].checked) {
					ckbStreptococoNeumoniaeDos=checkboxStreptococoNeumoniaeDos[x].id;
				}                               
			}
								
		//Checkbox Streptococo Neumoniae Tres
		var ckbStreptococoNeumoniaeTres = "";
		var checkboxStreptococoNeumoniaeTres = document.getElementsByName("streptococo_neumoniae_tres");
			for (var x = 0; x < checkboxStreptococoNeumoniaeTres.length; x ++) {
				if(checkboxStreptococoNeumoniaeTres[x].checked) {
					ckbStreptococoNeumoniaeTres=checkboxStreptococoNeumoniaeTres[x].id;
				}                               
			}	
								
		//Checkbox Haemophilus Uno
		var ckbHaemophilusUno = "";
		var checkboxHaemophilusUno = document.getElementsByName("haemophilus_uno");
			for (var x = 0; x < checkboxHaemophilusUno.length; x ++) {
				if(checkboxHaemophilusUno[x].checked) {
					ckbHaemophilusUno=checkboxHaemophilusUno[x].id;
				}                               
			}
		
		//Checkbox Haemophilus Dos
		var ckbHaemophilusDos = "";
		var checkboxHaemophilusDos = document.getElementsByName("haemophilus_dos");
			for (var x = 0; x < checkboxHaemophilusDos.length; x ++) {
				if(checkboxHaemophilusDos[x].checked) {
					ckbHaemophilusDos=checkboxHaemophilusDos[x].id;
				}                               
			}							
			
		//Checkbox Haemophilus Tres
		var ckbHaemophilusTres = "";
		var checkboxHaemophilusTres = document.getElementsByName("haemophilus_tres");
			for (var x = 0; x < checkboxHaemophilusTres.length; x ++) {
				if(checkboxHaemophilusTres[x].checked) {
					ckbHaemophilusTres=checkboxHaemophilusTres[x].id;
				}                               
			}
			
		//Checkbox Haemophilus R1
		var ckbHaemophilusRUno = "";
		var checkboxHaemophilusRUno = document.getElementsByName("haemophilus_runo");
			for (var x = 0; x < checkboxHaemophilusRUno.length; x ++) {
				if(checkboxHaemophilusRUno[x].checked) {
					ckbHaemophilusRUno=checkboxHaemophilusRUno[x].id;
				}                               
			}
		
		//Checkbox Haemophilus R2
		var ckbHaemophilusRDos = "";
		var checkboxHaemophilusRDos = document.getElementsByName("haemophilus_rdos");
			for (var x = 0; x < checkboxHaemophilusRDos.length; x ++) {
				if(checkboxHaemophilusRDos[x].checked) {
					ckbHaemophilusRDos=checkboxHaemophilusRDos[x].id;
				}                               
			}

		var influenzaDosis=document.getElementById("influenza_dosis").value;
		if (influenzaDosis=="") {
			alert("Escriba la ultima dosis de la influenza");
			document.getElementById("influenza_dosis").focus();
		} else {
			
		var fiebreAmarilla=document.getElementById("fiebre_amarilla").value;
		if (fiebreAmarilla=="") {
			alert("Escriba la edad en que dio la fiebre amarilla");
			document.getElementById("fiebre_amarilla").focus();
		} else {
		
		var otrasVacunas=document.getElementById("otras_vacunas").value;
		var vacunasPendientes=document.getElementById("vacunas_pendientes").value;
		var proximasVacunas=document.getElementById("proximas_vacunas").value;
		var tiempoProximaVacuna=document.getElementById("tiempo_proxima_vacuna").value;
		
	/******************************************************************************************************/
	/*************************************COMPLETAR EXAMEN FISICO******************************************/
	/******************************************************************************************************/
		var observacionesExamenFisico=document.getElementById("obs_examen_fisico").value;
		var otroProbDetectado=document.getElementById("otro_prob_detectado").value;
		
	/******************************************************************************************************/
	/*************************************EVALUAR ALIMENTACION*********************************************/
	/******************************************************************************************************/
		//Radio Leche Materna
		var radiLecheMaterna = "";
		var radioLecheMaterna = document.getElementsByName("rad_recibe_leche_mat");
			for (var x = 0; x < radioLecheMaterna.length; x ++) {
				if(radioLecheMaterna[x].checked) {
					radiLecheMaterna=radioLecheMaterna[x].id;
				}                               
			}
		if (radiLecheMaterna==false) {
			alert("Seleccione si recibe leche materna, en la Seccion Verificar Alimentacion");
		} else {
			
		var cuantasVecesLecheMat=document.getElementById("cuantas_veces_leche_mat").value;
		
		//Radio Pecho Noche
		var radPechoNoche = "";
		var radioPechoNoche = document.getElementsByName("rad_pecho_noche");
			for (var x = 0; x < radioPechoNoche.length; x ++) {
				if(radioPechoNoche[x].checked) {
					radPechoNoche=radioPechoNoche[x].id;
				}                               
			}
		if (radPechoNoche==false) {
			alert("Seleccione si recibe pecho en la noche, en la Seccion Verificar Alimentacion");
		} else {
		
		var extraeLeche=document.getElementById("extrae_leche").value;
		var guardaAdministraLeche=document.getElementById("guarda_administra_leche").value;
		
		//Radio Menor Seis Leche
		var radMenorSeisLeche = "";
		var radioMenorSeisLeche = document.getElementsByName("rad_menor_seis_leche");
			for (var x = 0; x < radioMenorSeisLeche.length; x ++) {
				if(radioMenorSeisLeche[x].checked) {
					radMenorSeisLeche=radioMenorSeisLeche[x].id;
				}                               
			}
		
		var cualesLecheMenorSeis=document.getElementById("cuales_leche_menor_seis").value;
		var cuantasVecesLecheMenorSeis=document.getElementById("cuantas_veces_leche_menor_seis").value;
		var conQueRecibeAlimento=document.getElementById("con_que_recibe_alimento").value;
		var quienDaComerAlimento=document.getElementById("quien_da_comer_alimento").value;
		var cuantasComidasAyer=document.getElementById("cuantas_comidas_ayer").value;
		var tamanoPorcionesAyer=document.getElementById("tamano_porciones_ayer").value;
		var cuantasComidasConsistenciaAyer=document.getElementById("cuantas_comidas_consistencia_ayer").value;
		var alimentosOrigenAnimal=document.getElementById("alimentos_origen_animal").value;
		var productosLacteos=document.getElementById("productos_lacteos").value;
		var comioLegumbres=document.getElementById("comio_legumbres").value;
		var comioVegetales=document.getElementById("comio_vegetales").value;
		var cantidadAceite=document.getElementById("cantidad_aceite").value;
		var quienDaComerAyerMayorSeis=document.getElementById("quien_da_comer_ayer_mayor_seis").value;
		var propioPlato=document.getElementById("propio_plato").value;
		var suplementacionAlimentos=document.getElementById("suplementacion_vitaminas").value;
		
		//Radio Esta Enfermo
		var radEstaEnfermo = "";
		var radioEstaEnfermo = document.getElementsByName("rad_esta_enfermo");
			for (var x = 0; x < radioEstaEnfermo.length; x ++) {
				if(radioEstaEnfermo[x].checked) {
					radEstaEnfermo=radioEstaEnfermo[x].id;
				}                               
			}
			
		var queComioEnfermedad=document.getElementById("que_comio_enfermedad").value;
			
		//Radio Es Obeso
		var radEsObeso = "";
		var radioEsObeso = document.getElementsByName("rad_es_obeso");
			for (var x = 0; x < radioEsObeso.length; x ++) {
				if(radioEsObeso[x].checked) {
					radEsObeso=radioEsObeso[x].id;
				}                               
			}
		
		var padresObesos=document.getElementById("padres_obesos").value;
		var ninoHaceEjercicio=document.getElementById("nino_hace_ejercicio").value;
		var programaNutricional=document.getElementById("programa_nutricional").value;
		var observacionesAlimentacion=document.getElementById("obs_alimentacion").value;
		var problemaDetectadoAlimentacion=document.getElementById("prob_detectado_alimentacion").value;
		var recomendacionesAlimentaciones=document.getElementById("recomendaciones_alimentaciones").value;
		
		/******************************************************************************************************/
		/*************************************RECOMENDACIONES**************************************************/
		/******************************************************************************************************/
		//var diagnosticoPaci=document.getElementById("txtNomDiagnos").value;
		//var codigoPac=document.getElementById("txtCodDiagnostico").value;
		var tratamiento=document.getElementById("tratar_pac").value;
		var signosAlarma=document.getElementById("signos_alarma").value;
		var consultaControl=document.getElementById("consulta_control").value;
		var dondeConsultaControl=document.getElementById("donde_consulta_control").value;
		var volverNinoSano=document.getElementById("volver_nino_sano").value;
		var referidoConsultas=document.getElementById("referido_consulta").value;
		var recomendacionesNino=document.getElementById("recomendaciones_nino").value;
		var recomendacionBuenTrato=document.getElementById("recomendaciones_buen_trato").value;
		
		//Radio Recibio Vitamina A
		var radRecVitaminaA = "";
		var radioRecVitaminaA = document.getElementsByName("rad_recibio_vitamina_a");
			for (var x = 0; x < radioRecVitaminaA.length; x ++) {
				if(radioRecVitaminaA[x].checked) {
					radRecVitaminaA=radioRecVitaminaA[x].id;
				}                               
			}
			
		var proxDosisVitaminaA=document.getElementById("prox_dosis_vitamina_a").value;
		
		//Radio Recibio Albendazol
		var radRecAlbendazol = "";
		var radioRecAlbendazol = document.getElementsByName("rad_recibio_albendazol");
			for (var x = 0; x < radioRecAlbendazol.length; x ++) {
				if(radioRecAlbendazol[x].checked) {
					radRecAlbendazol=radioRecAlbendazol[x].id;
				}                               
			}
			
		var proxDosisAlbendazol=document.getElementById("prox_dosis_albendazol").value;
		
		//Radio Recibio Hierro
		var radRecHierro = "";
		var radioRecHierro = document.getElementsByName("rad_recibio_hierro");
			for (var x = 0; x < radioRecHierro.length; x ++) {
				if(radioRecHierro[x].checked) {
					radRecHierro=radioRecHierro[x].id;
				}                               
			}
			
		var cuandoHierro=document.getElementById("cuando_hierro").value;
		var volverRecibirHierro=document.getElementById("volver_recibir_hierro").value;
		
		//Radio Recibir Zinc
		var radRecZinc = "";
		var radioRecZinc = document.getElementsByName("rad_recibir_zinc");
			for (var x = 0; x < radioRecZinc.length; x ++) {
				if(radioRecZinc[x].checked) {
					radRecZinc=radioRecZinc[x].id;
				}                               
			}
			
		var cuantoTiempoZinc=document.getElementById("cuanto_tiempo_zinc").value;
		var iniciaZinc=document.getElementById("inicia_zinc").value;
		
		var CodReporte="";
		
		
		
				ajax=getXMLObject();
			ajax.open("POST","AiepiMultiplePacientes",true);

			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					CodReporte=document.getElementById("txtCodReporte").value=ajax.responseText;
					//activarelementoFormulario("BotonAntecedentes");
					alert("Ingreso Exitoso");
					mostrarInformesAIEPI2a5(CodReporte);
					window.location.reload();
					//RedireccionFormatoCex(CodPaciente);
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=2.2&motiConsulta=" + motiConsulta
					+ "&CodPaciente=" + CodPaciente
					+ "&CodUsuario=" +  CodUsuario
					+ "&CodAdmision=" + CodAdmision
/*Encabezado*/		+ "&anteNatales=" + anteNatales
					+ "&APF=" + APF
					+ "&temperaturas=" + temperaturas
					+ "&frecuenciaCardiacas=" + frecuenciaCardiacas
					+ "&frecuenciaRespiratorias=" + frecuenciaRespiratorias
					+ "&tallas=" + tallas
					+ "&peso=" + peso
					+ "&perimetroCefalicos=" + perimetroCefalicos
					+ "&IMC=" + IMC
/*Ckb Sig Peligro*/	+ "&ckbNoBebePecho=" + ckbNoBebePecho
					+ "&ckbxLetargico=" + ckbxLetargico
					+ "&ckbVomitaTodo=" + ckbVomitaTodo
					+ "&ckbConvulsiones=" + ckbConvulsiones
					+ "&observacionesSignosPeligro=" + observacionesSignosPeligro
					+ "&ckbEnfMuyGrave=" + ckbEnfMuyGrave	
/*Dif Repirar*/		+ "&radiDificultadRespirar=" + radiDificultadRespirar
					+ "&desdeCuandoTos=" + desdeCuandoTos
					+ "&radEpisodioSibilancias=" + radEpisodioSibilancias
					+ "&radSibilanciasRecurrentes=" + radSibilanciasRecurrentes
					+ "&radCuadroGripal=" + radCuadroGripal
					+ "&radAntecedentesPrematuridad=" + radAntecedentesPrematuridad
					+ "&respiracionesMinutos=" + respiracionesMinutos
/*Ckb Dif Respirar*/+ "&ckbRespiracionRapida=" + ckbRespiracionRapida
					+ "&ckbxTirajeSubcostal=" + ckbxTirajeSubcostal
					+ "&ckbSaturacionOxigeno=" + ckbSaturacionOxigeno
					+ "&ckbTirajeSupraclavicular=" + ckbTirajeSupraclavicular
					+ "&ckbxEstridor=" + ckbxEstridor
					+ "&ckbSibilancias=" + ckbSibilancias
					+ "&ckbApnea=" + ckbApnea
					+ "&ckbIncapacidadHablar=" + ckbIncapacidadHablar
					+ "&ckbSomnoliento=" + ckbSomnoliento
					+ "&ckbConfuso=" + ckbConfuso
					+ "&ckbAgitado=" + ckbAgitado
					+ "&observacionesTos=" + observacionesTos
/*Resp Dif Respira*/+ "&ckbGrupGrave=" + ckbGrupGrave
					+ "&ckbBronquiolitisGrave=" + ckbBronquiolitisGrave
					+ "&ckbSibilanciaGrave=" + ckbSibilanciaGrave
					+ "&ckbCrup=" + ckbCrup
					+ "&ckbxSibilancia=" + ckbxSibilancia
					+ "&ckbNeumoniaGrave=" + ckbNeumoniaGrave
					+ "&ckbNeumonia=" + ckbNeumonia
					+ "&ckbTos=" + ckbTos
/*Tiene Diarrea*/	+ "&radTieneDiarrea=" + radTieneDiarrea
					+ "&desdeCuandoDiarrea=" + desdeCuandoDiarrea
					+ "&radiSangreHeces=" + radiSangreHeces
					+ "&radiVomito=" + radiVomito
					+ "&numVomitos=" + numVomitos
					+ "&numDiarreasVC=" + numDiarreasVC
					+ "&numDiarreasC=" + numDiarreasC
/*Ckb Diarrea*/		+ "&ckbComatoso=" + ckbComatoso
					+ "&ckbIntranquilo=" + ckbIntranquilo
					+ "&ckbxOjosHundidos=" + ckbxOjosHundidos
					+ "&ckbBebeMal=" + ckbBebeMal
					+ "&ckbBebeAvidamente=" + ckbBebeAvidamente
					+ "&pliegueCutaneos=" + pliegueCutaneos
					+ "&observacionesDiarrea=" + observacionesDiarrea
/*Resp Diarrea*/	+ "&ckbDeshidratacionGrave=" + ckbDeshidratacionGrave
					+ "&ckbGradoDeshidratacion=" + ckbGradoDeshidratacion
					+ "&ckbAltoRiesgoDeshidratacion=" + ckbAltoRiesgoDeshidratacion
					+ "&ckbSinDeshidratacion=" + ckbSinDeshidratacion
					+ "&ckbDiarreaPersistenteGrave=" + ckbDiarreaPersistenteGrave
					+ "&ckbDiarreaPersistente=" + ckbDiarreaPersistente
					+ "&ckbDisenteria=" + ckbDisenteria
/*Tiene Fiebre*/	+ "&radTieneFiebre=" + radTieneFiebre
					+ "&desdeCuandoFiebre=" + desdeCuandoFiebre
					+ "&radFiebreTO=" + radFiebreTO
					+ "&radFiebreTN=" + radFiebreTN
					+ "&radViveQuinceDias=" + radViveQuinceDias
					+ "&ckbZonaDengue=" + ckbZonaDengue
					+ "&zonaMalaria=" + zonaMalaria
/*Ckb Fiebre*/		+ "&ckbRigidezNuca=" + ckbRigidezNuca
					+ "&ckbAparienciaEnfermo=" + ckbAparienciaEnfermo
					+ "&ckbManifestacionesSangrado=" + ckbManifestacionesSangrado
					+ "&ckbAspectoToxico=" + ckbAspectoToxico
					+ "&respuestaSocial=" + respuestaSocial
					+ "&piel=" + piel
					+ "&ckbErupcionCutanea=" + ckbErupcionCutanea
					+ "&ckbDolorAbdominal=" + ckbDolorAbdominal
					+ "&ckbCefalea=" + ckbCefalea
					+ "&ckbMialgias=" + ckbMialgias
					+ "&ckbArtralgias=" + ckbArtralgias
					+ "&ckbDolorRetroocular=" + ckbDolorRetroocular
					+ "&ckbPostracion=" + ckbPostracion
					+ "&ckbPTorniquete=" + ckbPTorniquete
					+ "&ckbLipotimia=" + ckbLipotimia
					+ "&ckbHepatomegalia=" + ckbHepatomegalia
					+ "&ckbPulsoRapido=" + ckbPulsoRapido
					+ "&ckbxLlenadoCapilar=" + ckbxLlenadoCapilar
					+ "&ckbAscitis=" + ckbAscitis
					+ "&ckbDisminucionDiuresis=" + ckbDisminucionDiuresis
					+ "&ckbCuadroHematico=" + ckbCuadroHematico			
					+ "&ckbLeucocitos=" + ckbLeucocitos
					+ "&ckbNeutrofilos=" + ckbNeutrofilos
					+ "&ckbPlaquetas=" + ckbPlaquetas
					+ "&ckbParcialOrina=" + ckbParcialOrina
					+ "&ckbGotaGruesa=" + ckbGotaGruesa
					+ "&observacionesFiebre=" + observacionesFiebre
/*Resp Fiebre*/		+ "&ckbEnfFebrilRiesgo=" + ckbEnfFebrilRiesgo
					+ "&ckbEnfFebrilRiesgoIntermedio=" + ckbEnfFebrilRiesgoIntermedio
					+ "&ckbEnfFebrilRiesgoBajo=" + ckbEnfFebrilRiesgoBajo
					+ "&ckbMalariaComplicada=" + ckbMalariaComplicada
					+ "&ckbMalaria=" + ckbMalaria
					+ "&ckbDengueGrave=" + ckbDengueGrave
					+ "&ckbDengueSignosAlarma=" + ckbDengueSignosAlarma
					+ "&ckbProbableDengue=" + ckbProbableDengue
/*Oido*/			+ "&radTieneProbOido=" + radTieneProbOido
					+ "&radTieneDolorOido=" + radTieneDolorOido
					+ "&ckbTieneSupuracion=" + ckbTieneSupuracion
					+ "&desdeCuandoSupuracion=" + desdeCuandoSupuracion
					+ "&numEpisodiosPrevios=" + numEpisodiosPrevios
/*Ckb Oido*/		+ "&ckbTumefaccionDolorosa=" + ckbTumefaccionDolorosa
					+ "&ckbTimpanoRojo=" + ckbTimpanoRojo
					+ "&ckbxSupuracionOido=" + ckbxSupuracionOido
					+ "&observacionesOido=" + observacionesOido
/*Resp Oido*/		+ "&ckbMastoiditis=" + ckbMastoiditis
					+ "&ckbOtitisMediaCronica=" + ckbOtitisMediaCronica
					+ "&ckbOtitisMediaRecurrente=" + ckbOtitisMediaRecurrente
					+ "&ckbOtitisMediaAguda=" + ckbOtitisMediaAguda
					+ "&ckbNoTieneOtitis=" + ckbNoTieneOtitis
/*Garganta*/		+ "&radTieneProbGarganta=" + radTieneProbGarganta
					+ "&radTieneDolorGarganta=" + radTieneDolorGarganta
/*Ckb Garganta*/	+ "&ckbGangliosCuello=" + ckbGangliosCuello
					+ "&ckbAmigdalasEritematosas=" + ckbAmigdalasEritematosas
					+ "&ckbExudadoBlanquecino=" + ckbExudadoBlanquecino
					+ "&observacionesGarganta=" + observacionesGarganta
/*Resp Garganta*/	+ "&ckbFaringoamigdalitis=" + ckbFaringoamigdalitis
					+ "&ckbEstreptococica=" + ckbEstreptococica
					+ "&ckbFaringoamigdalitisViral=" + ckbFaringoamigdalitisViral
					+ "&ckbNoTieneFaringoamigdalitis=" + ckbNoTieneFaringoamigdalitis
/*Salud Bucal*/		+ "&radTieneDolorComer=" + radTieneDolorComer
					+ "&radTieneDolorDiente=" + radTieneDolorDiente
					+ "&radTraumaCara=" + radTraumaCara
					+ "&radTieneCaries=" + radTieneCaries
					+ "&radLimpiaBocaManana=" + radLimpiaBocaManana
					+ "&radLimpiaBocaTarde=" + radLimpiaBocaTarde
					+ "&radLimpiaBocaNoche=" + radLimpiaBocaNoche
					+ "&radLimpiaDientes=" + radLimpiaDientes
					+ "&radNinoSolo=" + radNinoSolo
					+ "&radCepillo=" + radCepillo	
					+ "&radCrema=" + radCrema
					+ "&radSeda=" + radSeda
					+ "&radiUtilizaChupo=" + radiUtilizaChupo
					+ "&ultimaConsultaOdontologica=" + ultimaConsultaOdontologica
/*Ckb Salud Bucal*/	+ "&ckbInflamacionLabio=" + ckbInflamacionLabio
					+ "&ckbNoInvolucraSurco=" + ckbNoInvolucraSurco
					+ "&ckbEnrojecimiento=" + ckbEnrojecimiento
					+ "&ckbInflamacionEncia=" + ckbInflamacionEncia
					+ "&ckbLocalizado=" + ckbLocalizado
					+ "&ckbGeneralizado=" + ckbGeneralizado
					+ "&ckbDeformacionEncia=" + ckbDeformacionEncia
					+ "&ckbExudadoPus=" + ckbExudadoPus
					+ "&ckbVesiculas=" + ckbVesiculas
					+ "&ckbUlceras=" + ckbUlceras
					+ "&placas=" + placas
					+ "&ckbFractura=" + ckbFractura
					+ "&ckbMovilidad=" + ckbMovilidad
					+ "&ckbDesplazamiento=" + ckbDesplazamiento
					+ "&ckbExtrusion=" + ckbExtrusion
					+ "&ckbIntrusion=" + ckbIntrusion
					+ "&ckbAvulsion=" + ckbAvulsion
					+ "&herida=" + herida
					+ "&ckbManchasBlancas=" + ckbManchasBlancas
					+ "&ckbCafes=" + ckbCafes
					+ "&ckbCariesCavitacionales=" + ckbCariesCavitacionales
					+ "&ckbPlacaBacteriana=" + ckbPlacaBacteriana
					+ "&observacionesSaludBucal=" + observacionesSaludBucal
/*Resp Salud Bucal*/+ "&ckbCelulitisFacial=" + ckbCelulitisFacial
					+ "&ckbEnfBucalGrave=" + ckbEnfBucalGrave
					+ "&ckbTraumaBucodental=" + ckbTraumaBucodental
					+ "&ckbEstomatitis=" + ckbEstomatitis
					+ "&ckbEnfDental=" + ckbEnfDental
					+ "&ckbAltoRiesgoEnfBucal=" + ckbAltoRiesgoEnfBucal
					+ "&ckbBajoRiesgoEnfBucal=" + ckbBajoRiesgoEnfBucal
/*Ver Crecimiento*/	+ "&radEmanacionVisible=" + radEmanacionVisible
					+ "&pesosEdad=" + pesosEdad
					+ "&radEdemaPies=" + radEdemaPies
					+ "&apariencia=" + apariencia
					+ "&IMCEdad=" + IMCEdad
					+ "&tallaEdad=" + tallaEdad
					+ "&pesosTalla=" + pesosTalla
					+ "&tendenciasPeso=" + tendenciasPeso
/*Ckb Crecimiento*/	+ "&ckbDesnutricionGlobalSevera=" + ckbDesnutricionGlobalSevera
					+ "&ckbDesnutricionGlobal=" + ckbDesnutricionGlobal
					+ "&ckbRiesgoDesnutricion=" + ckbRiesgoDesnutricion
					+ "&ckbPesoAdecuadoEdad=" + ckbPesoAdecuadoEdad
					+ "&ckbDesnutricionCronica=" + ckbDesnutricionCronica
					+ "&ckbRiesgoDNT=" + ckbRiesgoDNT
					+ "&ckbTallaAdecuadaEdad=" + ckbTallaAdecuadaEdad
					+ "&ckbDesnutricionAguda=" + ckbDesnutricionAguda
					+ "&ckbDNTAguda=" + ckbDNTAguda
					+ "&ckbPesoAdecuadoTalla=" + ckbPesoAdecuadoTalla
					+ "&ckbSobrepeso=" + ckbSobrepeso
					+ "&ckbObesidad=" + ckbObesidad
					+ "&observacionesCrecimiento=" + observacionesCrecimiento
/*Resp Crecimiento*/+ "&ckbObeso=" + ckbObeso
					+ "&ckbSobrepesoR=" + ckbSobrepesoR
					+ "&ckbDesnutricionSevera=" + ckbDesnutricionSevera
					+ "&ckbDesnutricion=" + ckbDesnutricion
					+ "&ckbRiesgoDesnutricionR=" + ckbRiesgoDesnutricionR
					+ "&ckbEstadoNutricional=" + ckbEstadoNutricional	
/*Ver Anemia*/		+ "&radRecibidoHierro=" + radRecibidoHierro
					+ "&cuandoRecibidoHierro=" + cuandoRecibidoHierro
					+ "&cuantoTiempoRecibidoHierro=" + cuantoTiempoRecibidoHierro
					+ "&palidezPalmar=" + palidezPalmar
					+ "&palidezConjuntival=" + palidezConjuntival
					+ "&observacionesAnemia=" + observacionesAnemia
/*Resp Anemia*/		+ "&ckbAnemiaSevera=" + ckbAnemiaSevera
					+ "&ckbAnemia=" + ckbAnemia
					+ "&ckbNoTieneAnemia=" + ckbNoTieneAnemia
/*Ver Maltrato*/	+ "&produjeronLesiones=" + produjeronLesiones
					+ "&radRelataMaltrato=" + radRelataMaltrato
					+ "&ckbFisico=" + ckbFisico
					+ "&ckbSexual=" + ckbSexual
					+ "&ckbNegligencia=" + ckbNegligencia
					+ "&quien_maltrato=" + quien_maltrato
					+ "&radIncongruenciaTrauma=" + radIncongruenciaTrauma
					+ "&radExisteIncongruencia=" + radExisteIncongruencia
					+ "&diferentesVersiones=" + diferentesVersiones
					+ "&radTardiaConsulta=" + radTardiaConsulta
					+ "&frecuenciaPegar=" + frecuenciaPegar
					+ "&desobedienteHijo=" + desobedienteHijo
					+ "&comportamientoAnormalPadres=" + comportamientoAnormalPadres
					+ "&radDescuidadoNino=" + radDescuidadoNino
					+ "&porDescuidadoNino=" + porDescuidadoNino
					+ "&descuidadoNinoEn=" + descuidadoNinoEn
					+ "&factorRiesgo=" + factorRiesgo
					+ "&radActitudAnormal=" + radActitudAnormal
/*Ckb Maltrato*/	+ "&ckbFracturas=" + ckbFracturas
					+ "&ckbHematomas=" + ckbHematomas
					+ "&ckbHemorragiasRetinianas=" + ckbHemorragiasRetinianas
					+ "&ckbAreasRopa=" + ckbAreasRopa
					+ "&ckbPatronSimetrico=" + ckbPatronSimetrico
					+ "&ckbDenotaObjeto=" + ckbDenotaObjeto
					+ "&ckbEnEspalda=" + ckbEnEspalda
					+ "&ckbxEquimosis=" + ckbxEquimosis
					+ "&ckbHematomasMaltrato=" + ckbHematomasMaltrato
					+ "&ckbLasceraciones=" + ckbLasceraciones
					+ "&ckbMordiscos=" + ckbMordiscos
					+ "&ckbCicatrices=" + ckbCicatrices					
					+ "&ckbDiferenteEvolucion=" + ckbDiferenteEvolucion
					+ "&ckbSugestivasMaltrato=" + ckbSugestivasMaltrato
					+ "&ckbCostillas=" + ckbCostillas
					+ "&ckbHuesosLargos=" + ckbHuesosLargos
					+ "&ckbEspirales=" + ckbEspirales
					+ "&ckbOblicua=" + ckbOblicua
					+ "&ckbMetafisiarias=" + ckbMetafisiarias
					+ "&ckbEsternon=" + ckbEsternon
					+ "&ckbEscapula=" + ckbEscapula
					+ "&ckbMenorCincoAnos=" + ckbMenorCincoAnos
					+ "&ckbTraumaVisceral=" + ckbTraumaVisceral
					+ "&ckbTraumaGrave=" + ckbTraumaGrave
					+ "&lesionesSugestival=" + lesionesSugestival
					+ "&ckbSangradoVaginal=" + ckbSangradoVaginal
					+ "&ckbLasceracionAguda=" + ckbLasceracionAguda
					+ "&ckbLasceracionPerianal=" + ckbLasceracionPerianal
					+ "&ckbAusenciaHimen=" + ckbAusenciaHimen
					+ "&ckbHimenCicatrizado=" + ckbHimenCicatrizado
					+ "&ckbCicatrizNavicular=" + ckbCicatrizNavicular
					+ "&ckbAnoDilatado=" + ckbAnoDilatado
					+ "&ckbHalazgoSemen=" + ckbHalazgoSemen
					+ "&ckbFlujoGenital=" + ckbFlujoGenital
					+ "&ckbCuerpoExtrano=" + ckbCuerpoExtrano
					+ "&ckbVesiculasMaltrato=" + ckbVesiculasMaltrato
					+ "&ckbJuegoSexual=" + ckbJuegoSexual
					+ "&ckbBocaGenitales=" + ckbBocaGenitales
					+ "&ckbVIH=" + ckbVIH
					+ "&ckbGonorrea=" + ckbGonorrea
					+ "&ckbSifilis=" + ckbSifilis
					+ "&ckbTrichomonaVaginalis=" + ckbTrichomonaVaginalis
					+ "&ckbChlamydiaTrachomatis=" + ckbChlamydiaTrachomatis
					+ "&ckbCondilomatosis=" + ckbCondilomatosis
					+ "&ckbTemeroso=" + ckbTemeroso
					+ "&ckbRetraido=" + ckbRetraido
					+ "&ckbRechazoAdulto=" + ckbRechazoAdulto
					+ "&ckbDeprimido=" + ckbDeprimido
					+ "&ckbEvitaContactoVisual=" + ckbEvitaContactoVisual
					+ "&ckbTrastornoSueno=" + ckbTrastornoSueno
					+ "&ckbTrastornoAlimentario=" + ckbTrastornoAlimentario
					+ "&ckbProblemasPsicomaticos=" + ckbProblemasPsicomaticos
					+ "&ckbConductasRegresivas=" + ckbConductasRegresivas
					+ "&ckbDesarrolloEstancado=" + ckbDesarrolloEstancado
					+ "&ckbViolenciaIntrafamiliar=" + ckbViolenciaIntrafamiliar
					+ "&ckbFamiliaCaotica=" + ckbFamiliaCaotica
					+ "&ckbCuidadoresAdictos=" + ckbCuidadoresAdictos
					+ "&observacionesMaltrato=" + observacionesMaltrato
/*Resp Maltrato*/	+ "&ckbMaltratoFisicoMuyGrave=" + ckbMaltratoFisicoMuyGrave
					+ "&ckbAbusoGrave=" + ckbAbusoGrave
					+ "&ckbMaltratoFisico=" + ckbMaltratoFisico
					+ "&ckbSospechaAbusoSexual=" + ckbSospechaAbusoSexual
					+ "&ckbMaltratoEmocional=" + ckbMaltratoEmocional
					+ "&ckbAbandono=" + ckbAbandono
					+ "&ckbNoSospechaMaltrato=" + ckbNoSospechaMaltrato
/*Eva Desarrollo*/	+ "&radAntecedenteImportante=" + radAntecedenteImportante
					+ "&paraDesarrollo=" + paraDesarrollo
					+ "&algunFactorRiesgo=" + algunFactorRiesgo
/*Ckb Ev Desarroll*/+ "&ckbRealizaCondEdadUno=" + ckbRealizaCondEdadUno
					+ "&ckbRealizaCondEdadDos=" + ckbRealizaCondEdadDos
					+ "&ckbRealizaCondEdadTres=" + ckbRealizaCondEdadTres
					+ "&ckbRealizaCondEdadCuatro=" + ckbRealizaCondEdadCuatro
					+ "&ckbAusenciaCondEdadUno=" + ckbAusenciaCondEdadUno
					+ "&ckbAusenciaCondEdadDos=" + ckbAusenciaCondEdadDos
					+ "&ckbAusenciaCondEdadTres=" + ckbAusenciaCondEdadTres
					+ "&ckbAusenciaCondEdadCuatro=" + ckbAusenciaCondEdadCuatro
					+ "&ckbAusenciaCondGrupoAnteriorUno=" + ckbAusenciaCondGrupoAnteriorUno
					+ "&ckbAusenciaCondGrupoAnteriorDos=" + ckbAusenciaCondGrupoAnteriorDos
					+ "&ckbAusenciaCondGrupoAnteriorTres=" + ckbAusenciaCondGrupoAnteriorTres
					+ "&ckbAusenciaCondGrupoAnteriorCuatro=" + ckbAusenciaCondGrupoAnteriorCuatro
					+ "&PCefalico=" + PCefalico
					+ "&AlteracionesFenotipicas=" + AlteracionesFenotipicas
					+ "&ckbMenosDosDE=" + ckbMenosDosDE
					+ "&ckbMasDosDE=" + ckbMasDosDE
					+ "&observacionesDesarrollo=" + observacionesDesarrollo
/*Resp Ev Desarrol*/+ "&ckbProbRetrasoDesarrollo=" + ckbProbRetrasoDesarrollo
					+ "&ckbRiesgoProblemaDesarrollo=" + ckbRiesgoProblemaDesarrollo
					+ "&ckbDesarrolloNormalRiesgo=" + ckbDesarrolloNormalRiesgo
					+ "&ckbxDesarrolloNormal=" + ckbxDesarrolloNormal
/*Ant Vacunacion*/	+ "&ckbBCGUno=" + ckbBCGUno
					+ "&ckbHepatitisBRN=" + ckbHepatitisBRN
					+ "&ckbHepatitisBUno=" + ckbHepatitisBUno
					+ "&ckbHepatitisBDos=" + ckbHepatitisBDos
					+ "&ckbHepatitisBTres=" + ckbHepatitisBTres
					+ "&ckbDPTUno=" + ckbDPTUno
					+ "&ckbDPTDos=" + ckbDPTDos
					+ "&ckbDPTTres=" + ckbDPTTres
					+ "&ckbDPTRUno=" + ckbDPTRUno
					+ "&ckbDPTRDos=" + ckbDPTRDos
					+ "&ckbVOPUno=" + ckbVOPUno
					+ "&ckbVOPDos=" + ckbVOPDos
					+ "&ckbVOPTres=" + ckbVOPTres
					+ "&ckbVOPRUno=" + ckbVOPRUno
					+ "&ckbVOPRDos=" + ckbVOPRDos
					+ "&ckbSRPUno=" + ckbSRPUno
					+ "&ckbSRPDos=" + ckbSRPDos
					+ "&ckbRotavirusUno=" + ckbRotavirusUno
					+ "&ckbRotavirusDos=" + ckbRotavirusDos
					+ "&ckbStreptococoNeumoniaeUno=" + ckbStreptococoNeumoniaeUno					
					+ "&ckbStreptococoNeumoniaeDos=" + ckbStreptococoNeumoniaeDos
					+ "&ckbStreptococoNeumoniaeTres=" + ckbStreptococoNeumoniaeTres
					+ "&ckbHaemophilusUno=" + ckbHaemophilusUno
					+ "&ckbHaemophilusDos=" + ckbHaemophilusDos
					+ "&ckbHaemophilusTres=" + ckbHaemophilusTres
					+ "&ckbHaemophilusRUno=" + ckbHaemophilusRUno
					+ "&ckbHaemophilusRDos=" + ckbHaemophilusRDos
					+ "&influenzaDosis=" + influenzaDosis
					+ "&fiebreAmarilla=" + fiebreAmarilla
					+ "&otrasVacunas=" + otrasVacunas
					+ "&vacunasPendientes=" + vacunasPendientes
					+ "&proximasVacunas=" + proximasVacunas
					+ "&tiempoProximaVacuna=" + tiempoProximaVacuna
/*Comp Exa Fisico*/	+ "&observacionesExamenFisico=" + observacionesExamenFisico
					+ "&otroProbDetectado=" + otroProbDetectado
/*Ev Alimentacion*/	+ "&radiLecheMaterna=" + radiLecheMaterna
					+ "&cuantasVecesLecheMat=" + cuantasVecesLecheMat
					+ "&radPechoNoche=" + radPechoNoche
					+ "&extraeLeche=" + extraeLeche
					+ "&guardaAdministraLeche=" + guardaAdministraLeche
					+ "&radMenorSeisLeche=" + radMenorSeisLeche
					+ "&cualesLecheMenorSeis=" + cualesLecheMenorSeis
					+ "&cuantasVecesLecheMenorSeis=" + cuantasVecesLecheMenorSeis					
					+ "&conQueRecibeAlimento=" + conQueRecibeAlimento
					+ "&quienDaComerAlimento=" + quienDaComerAlimento
					+ "&cuantasComidasAyer=" + cuantasComidasAyer
					+ "&tamanoPorcionesAyer=" + tamanoPorcionesAyer
					+ "&cuantasComidasConsistenciaAyer=" + cuantasComidasConsistenciaAyer
					+ "&alimentosOrigenAnimal=" + alimentosOrigenAnimal
					+ "&productosLacteos=" + productosLacteos
					+ "&comioLegumbres=" + comioLegumbres
					+ "&comioVegetales=" + comioVegetales
					+ "&cantidadAceite=" + cantidadAceite
					+ "&quienDaComerAyerMayorSeis=" + quienDaComerAyerMayorSeis
					+ "&propioPlato=" + propioPlato
					+ "&suplementacionAlimentos=" + suplementacionAlimentos
					+ "&radEstaEnfermo=" + radEstaEnfermo
					+ "&queComioEnfermedad=" + queComioEnfermedad
					+ "&radEsObeso=" + radEsObeso
					+ "&padresObesos=" + padresObesos
					+ "&ninoHaceEjercicio=" + ninoHaceEjercicio
					+ "&programaNutricional=" + programaNutricional
					+ "&observacionesAlimentacion=" + observacionesAlimentacion
					+ "&problemaDetectadoAlimentacion=" + problemaDetectadoAlimentacion
					+ "&recomendacionesAlimentaciones=" + recomendacionesAlimentaciones
/*Recomendaciones*/	//+ "&diagnosticoPaci=" + diagnosticoPaci
					//+ "&codigoPac=" + codigoPac
					+ "&tratamiento=" + tratamiento
					+ "&signosAlarma=" + signosAlarma
					+ "&consultaControl=" + consultaControl
					+ "&dondeConsultaControl=" + dondeConsultaControl
					+ "&volverNinoSano=" + volverNinoSano	
					+ "&referidoConsultas=" + referidoConsultas
					+ "&recomendacionesNino=" + recomendacionesNino
					+ "&recomendacionBuenTrato=" + recomendacionBuenTrato
					+ "&radRecVitaminaA=" + radRecVitaminaA
					+ "&proxDosisVitaminaA=" + proxDosisVitaminaA
					+ "&radRecAlbendazol=" + radRecAlbendazol
					+ "&proxDosisAlbendazol=" + proxDosisAlbendazol
					+ "&radRecHierro=" + radRecHierro
					+ "&cuandoHierro=" + cuandoHierro
					+ "&volverRecibirHierro=" + volverRecibirHierro
					+ "&radRecZinc=" + radRecZinc
					+ "&cuantoTiempoZinc=" + cuantoTiempoZinc
					+ "&iniciaZinc=" + iniciaZinc); //Posting txtname to Servlet*/	
			}//Mot Consulta
			}//Ant Natales
			}//APF
			}//Temperatura
			}//Frecuencia Cardiaca
			}//Frecuencia Respiratoria
			}//Talla
			}//Peso
			}//Perimetro Cefalico
			}//IMC
			}//Rad Dificultad Respirar
			}//Rad Tiene Diarrrea
			}//Rad Tiene Fiebre
			}//Rad Prob Oido
			}//Rad Prob Garganta
			}//Rad Dolor Comer
			}//Rad Dolor Diente
			}//Rad Trauma Cara
			}//Rad Tiene Caries
			}//Rad Limpia Boca Mañana
			}//Rad Limpia Boca Tarde
			}//Rad Limpia Boca Noche
			}//Rad Limpia Dientes
			}//Rad Niño Solo
			}//Rad Cepillo
			}//Rad Crema
			}//Rad Seda
			}//Rad Chupo
			}//Rad Ultima Consulta Odontologica
			}//Rad Emanacion Visible
			}//Peso Edad
			}//Rad Edema Pies
			}//Apariencia
			}//IMC Edad
			}//Talla Edad
			}//Peso Talla
			}//Tendencia Peso
			}//Rad Recibido Hierro
			}//Palidez Palmar
			}//Palidez Conjuntival
			}//Produjeron Lesiones
			}//Rad Relata Maltrato
			}//Incongruencia Trauma
			}//Incongreuncia Lesion
			}//Tardía Consulta
			}//Frecuencia Pega Hijo
			}//Desobediente Hijo
			}//Comportamiento Anormal Padres
			}//Descuidado Niño Salud
			}//Factor Riesgo
			}//Actitud Anormal Niño
			}//Ant Importante Desarrollo
			}//Perimetro Cefalico Desarrollo
			}//Alteraciones fenotipicas
			}//Influenza Ultima Dosis
			}//Fiebre Amarilla Edad
			}//Rad Leche Materna
			}//Rad Recibe Pecho Noche
}
	/***********************************************************************/
	/***************************FIN GUARDAR INFORME 2-5 AIEPI***************/
	/***********************************************************************/

	/***********************************************************************/
	/***************************MOSTRAR INFORME 2-5 AIEPI*******************/
	/***********************************************************************/
function mostrarInformesAIEPI2a5(idcodInforme){
	pagina="aiepi_reporte2a5.jsp?CodInfPYPPlanFam="+idcodInforme;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Informe_Aiepi_2a5",opciones);
}
	/***********************************************************************/
	/***************************FIN MOSTRAR INFORME 2-5 AIEPI***************/
	/***********************************************************************/



	/***********************************************************************/
	/**********************GUARDAR INFORME EMBARAZADAS AIEPI****************/
	/***********************************************************************/
function GuardarAiepiEmbarazadas(){	
	
	var CodPaciente=document.getElementById("CodPac").value;
	var CodUsuario=document.getElementById("txtCodusuario").value;
	var CodAdmision=document.getElementById("CodAdmision").value;
	
	/******************************************************************************************************/
	/*************************************ANTECEDENTES*****************************************************/
	/******************************************************************************************************/
	var motConsulta=document.getElementById("mot_consulta").value;
	if (motConsulta=="") {
		alert("Escribir el motivo de la enfermedad actual");
		document.getElementById("mot_consulta").focus();
	} else {
		
	var antEmbarazo=document.getElementById("ant_embarazo").value;
	if (antEmbarazo=="") {
		alert("Escribir los antecedentes del embarazo");
		document.getElementById("ant_embarazo").focus();
	} else {

	var pesoNacer=document.getElementById("peso_nacer").value;
	if (pesoNacer=="") {
		alert("Escriba el peso al nacer");
		document.getElementById("peso_nacer").focus();
	} else {
	
	var tallaNacer=document.getElementById("talla_nacer").value;
	if (tallaNacer=="") {
		alert("Escriba la talla al nacer");
		document.getElementById("talla_nacer").focus();
	} else {
		
	var edadGestacional=document.getElementById("edad_gestacional").value;
	if (edadGestacional=="") {
		alert("Escriba la edad gestacional");
		document.getElementById("edad_gestacional").focus();
	} else {
		
	var hemoclasificacion=document.getElementById("hemoclasificacion").value;
	if (hemoclasificacion=="") {
		alert("Escriba la hemoclasificacion");
		document.getElementById("hemoclasificacion").focus();
	} else {
	
	var pesoActual=document.getElementById("peso_actual").value;
	if (pesoActual=="") {
		alert("Escriba el peso actual");
		document.getElementById("peso_actual").focus();
	} else {
		
	var talla=document.getElementById("talla").value;
	if (talla=="") {
		alert("Escriba la talla");
		document.getElementById("talla").focus();
	} else {
		
	var perimetroCefalico=document.getElementById("perimetro_cefalico").value;
	if (perimetroCefalico=="") {
		alert("Escriba el perimetro cefalico");
		document.getElementById("perimetro_cefalico").focus();
	} else {
		
	var frecuenciaCardiaca=document.getElementById("frecuencia_cardiaca").value;
	if (frecuenciaCardiaca=="") {
		alert("Escriba la frecuencia cardiaca");
		document.getElementById("frecuencia_cardiaca").focus();
	} else {
		
	var frecuenciaRespiratoria=document.getElementById("frecuencia_respiratoria").value;
	if (frecuenciaRespiratoria=="") {
		alert("Escriba la frecuencia respiratoria");
		document.getElementById("frecuencia_respiratoria").focus();
	} else {
		
	var temperatura=document.getElementById("temperatura").value;
	if (temperatura=="") {
		alert("Escriba la temperatura");
		document.getElementById("temperatura").focus();
	} else {
		
	var centigrados=document.getElementById("centigrados").value;
	if (centigrados=="") {
		alert("Escriba los centigrados");
		document.getElementById("centigrados").focus();
	} else {
			
	/******************************************************************************************************/
	/*************************************RIESGO GESTACION*************************************************/
	/******************************************************************************************************/
	//Radio Control Prenatal
	var radContPrenatal = "";
	var radioContPrenatal = document.getElementsByName("rad_control_prenatal");
		for (var x = 0; x < radioContPrenatal.length; x ++) {
			if(radioContPrenatal[x].checked) {
				radContPrenatal=radioContPrenatal[x].id;
			}                               
		}
	if (radContPrenatal==false) {
		alert("Seleccione si ha tenido control prenatal en la seccion Verificar Riesgo Gestacion");
	} else {
		
	var numControlPrenatal=document.getElementById("num_control_prenatal").value;

	//Radio Fiebre
	var radFiebre = "";
	var radioFiebre = document.getElementsByName("rad_fiebre");
		for (var x = 0; x < radioFiebre.length; x ++) {
			if(radioFiebre[x].checked) {
				radFiebre=radioFiebre[x].id;
			}                               
		}
	if (radFiebre==false) {
		alert("Seleccione si ha tenido fiebre");
	} else {	
		
	//Radio Hipotermia
	var radHipotermia = "";
	var radioHipotermia = document.getElementsByName("rad_hipotermia");
		for (var x = 0; x < radioHipotermia.length; x ++) {
			if(radioHipotermia[x].checked) {
				radHipotermia=radioHipotermia[x].id;
			}
		}
	if (radHipotermia==false) {
		alert("Seleccione si ha tenido hipotermia");
	} else {
			
	//Radio Convulsiones
	var radConvulsiones = "";
	var radioConvulsiones = document.getElementsByName("rad_convulsiones");
		for (var x = 0; x < radioConvulsiones.length; x ++) {
			if(radioConvulsiones[x].checked) {
				radConvulsiones=radioConvulsiones[x].id;
			}                               
		}
	if (radConvulsiones==false) {
		alert("Seleccione si ha tenido convulsiones");
	} else {
				
	//Radio Vomito
	var radVomito = "";
	var radioVomito = document.getElementsByName("rad_vomito");
		for (var x = 0; x < radioVomito.length; x ++) {
			if(radioVomito[x].checked) {
				radVomito=radioVomito[x].id;
			}                               
		}
	if (radVomito==false) {
		alert("Seleccione si ha tenido vomito");
	} else {
		
	var vomitaTodo=document.getElementById("vomita_todo").value;	
	
	//Radio Diarrea
	var radDiarrea = "";
	var radioDiarrea = document.getElementsByName("rad_diarrea");
		for (var x = 0; x < radioDiarrea.length; x ++) {
			if(radioDiarrea[x].checked) {
				radDiarrea=radioDiarrea[x].id;
			}                               
		}
	if (radDiarrea==false) {
		alert("Seleccione si tiene diarrea");
	} else {

	var cuandoDiarrea=document.getElementById("cuando_diarrea").value;
	
	//Radio Dificultad Respirar
	var radDificultadRespirar = "";
	var radioDificultadRespirar = document.getElementsByName("rad_dificultad_respirar");
		for (var x = 0; x < radioDificultadRespirar.length; x ++) {
			if(radioDificultadRespirar[x].checked) {
				radDificultadRespirar=radioDificultadRespirar[x].id;
			}                               
		}
	if (radDificultadRespirar==false) {
		alert("Seleccione si tiene dificultad para respirar");
	} else {
		
	var expliqueDificultadRespirar=document.getElementById("exp_dificultad_respirar").value;
	
	//Radio Sangre Heces
	var radSangreHeces = "";
	var radioSangreHeces = document.getElementsByName("rad_sangre_heces");
		for (var x = 0; x < radioSangreHeces.length; x ++) {
			if(radioSangreHeces[x].checked) {
				radSangreHeces=radioSangreHeces[x].id;
			}                               
		}
	if (radSangreHeces==false) {
		alert("Seleccione si hay sangre en las heces");
	} else {
		
	var panalesOrinados=document.getElementById("panales_orinados").value;
	if (panalesOrinados==false) {
		alert("Escriba cuantos panales ha orinado en las ultimas 24 horas");
	} else {
	
	//Checkbox Mueve Solo Estimulo
	var ckbMueveSolo = "";
	var checkboxMueveSolo = document.getElementsByName("mueve_solo");
		for (var x = 0; x < checkboxMueveSolo.length; x ++) {
			if(checkboxMueveSolo[x].checked) {
				ckbMueveSolo=checkboxMueveSolo[x].id;
			}                               
		}
	
	//Checkbox Letargico
	var ckbLetargico = "";
	var checkboxLetargico = document.getElementsByName("letargico");
		for (var x = 0; x < checkboxLetargico.length; x ++) {
			if(checkboxLetargico[x].checked) {
				ckbLetargico=checkboxLetargico[x].id;
			}                               
		}
	
	//Checkbox Luce Mal
	var ckbLuceMal = "";
	var checkboxLuceMal = document.getElementsByName("luce_mal");
		for (var x = 0; x < checkboxLuceMal.length; x ++) {
			if(checkboxLuceMal[x].checked) {
				ckbLuceMal=checkboxLuceMal[x].id;
			}                               
		}
		
	//Checkbox Irritable
	var ckbIrritable = "";
	var checkboxIrritable = document.getElementsByName("irritable");
		for (var x = 0; x < checkboxIrritable.length; x ++) {
			if(checkboxIrritable[x].checked) {
				ckbIrritable=checkboxIrritable[x].id;
			}                               
		}	
		
	//Checkbox Palidez
	var ckbPalidez = "";
	var checkboxPalidez = document.getElementsByName("palidez");
		for (var x = 0; x < checkboxPalidez.length; x ++) {
			if(checkboxPalidez[x].checked) {
				ckbPalidez=checkboxPalidez[x].id;
			}                               
		}	
			
	//Checkbox Cianosis
	var ckbCianosis = "";
	var checkboxCianosis = document.getElementsByName("cianosis");
		for (var x = 0; x < checkboxCianosis.length; x ++) {
			if(checkboxCianosis[x].checked) {
				ckbCianosis=checkboxCianosis[x].id;
			}                               
		}
		
	//Checkbox Apneas
	var ckbApneas = "";
	var checkboxApneas = document.getElementsByName("apneas");
		for (var x = 0; x < checkboxApneas.length; x ++) {
			if(checkboxApneas[x].checked) {
				ckbApneas=checkboxApneas[x].id;
			}                               
		}
		
	//Checkbox Bilirrubinas
	var ckbBilirrubinas = "";
	var checkboxBilirrubinas = document.getElementsByName("bilirrubinas");
		for (var x = 0; x < checkboxBilirrubinas.length; x ++) {
			if(checkboxBilirrubinas[x].checked) {
				ckbBilirrubinas=checkboxBilirrubinas[x].id;
			}                               
		}
		
	//Checkbox Estridor
	var ckbEstridor = "";
	var checkboxEstridor = document.getElementsByName("estridor");
		for (var x = 0; x < checkboxEstridor.length; x ++) {
			if(checkboxEstridor[x].checked) {
				ckbEstridor=checkboxEstridor[x].id;
			}                               
		}
		
	//Checkbox Frecuencia Respiratoria
	var ckbFR = "";
	var checkboxFR = document.getElementsByName("fr");
		for (var x = 0; x < checkboxFR.length; x ++) {
			if(checkboxFR[x].checked) {
				ckbFR=checkboxFR[x].id;
			}                               
		}
		
	//Checkbox Aleteo Nasal
	var ckbAleteoNasal = "";
	var checkboxAleteoNasal = document.getElementsByName("aleteo_nasal");
		for (var x = 0; x < checkboxAleteoNasal.length; x ++) {
			if(checkboxAleteoNasal[x].checked) {
				ckbAleteoNasal=checkboxAleteoNasal[x].id;
			}                               
		}	
		
	//Checkbox Quejido
	var ckbQuejido = "";
	var checkboxQuejido = document.getElementsByName("quejido");
		for (var x = 0; x < checkboxQuejido.length; x ++) {
			if(checkboxQuejido[x].checked) {
				ckbQuejido=checkboxQuejido[x].id;
			}                               
		}
		
	//Checkbox Sibilancia
	var ckbSibilancia = "";
	var checkboxSibilancia = document.getElementsByName("sibilancia");
		for (var x = 0; x < checkboxSibilancia.length; x ++) {
			if(checkboxSibilancia[x].checked) {
				ckbSibilancia=checkboxSibilancia[x].id;
			}                               
		}
	
	//Checkbox Frecuencia Cardiaca
	var ckbFC = "";
	var checkboxFC = document.getElementsByName("fc");
		for (var x = 0; x < checkboxFC.length; x ++) {
			if(checkboxFC[x].checked) {
				ckbFC=checkboxFC[x].id;
			}                               
		}
		
	//Checkbox Supuracion Oido
	var ckbSupuracionOido = "";
	var checkboxSupuracionOido = document.getElementsByName("supuracion_oido");
		for (var x = 0; x < checkboxSupuracionOido.length; x ++) {
			if(checkboxSupuracionOido[x].checked) {
				ckbSupuracionOido=checkboxSupuracionOido[x].id;
			}                               
		}
		
	//Checkbox Tiraje Subcostal
	var ckbTirajeSubcostal = "";
	var checkboxTirajeSubcostal = document.getElementsByName("tiraje_subcostal");
		for (var x = 0; x < checkboxTirajeSubcostal.length; x ++) {
			if(checkboxTirajeSubcostal[x].checked) {
				ckbTirajeSubcostal=checkboxTirajeSubcostal[x].id;
			}                               
		}
	
	//Checkbox Secrecion Purulenta Conjuntival
	var ckbSecPurulentaConjuntival = "";
	var checkboxSecPurulentaConjuntival = document.getElementsByName("sec_purulenta_conjuntival");
		for (var x = 0; x < checkboxSecPurulentaConjuntival.length; x ++) {
			if(checkboxSecPurulentaConjuntival[x].checked) {
				ckbSecPurulentaConjuntival=checkboxSecPurulentaConjuntival[x].id;
			}                               
		}
		
	//Checkbox Edema Palpebral
	var ckbEdemaPalpebral = "";
	var checkboxEdemaPalpebral = document.getElementsByName("edema_palpebral");
		for (var x = 0; x < checkboxEdemaPalpebral.length; x ++) {
			if(checkboxEdemaPalpebral[x].checked) {
				ckbEdemaPalpebral=checkboxEdemaPalpebral[x].id;
			}                               
		}
		
	//Checkbox Eritema Periumbilical
	var ckbEritemaPeriumbilical = "";
	var checkboxEritemaPeriumbilical = document.getElementsByName("eritema_periumbilical");
		for (var x = 0; x < checkboxEritemaPeriumbilical.length; x ++) {
			if(checkboxEritemaPeriumbilical[x].checked) {
				ckbEritemaPeriumbilical=checkboxEritemaPeriumbilical[x].id;
			}                               
		}
		
	var pustulasPiel=document.getElementById("pustulas_piel").value;
		
	//Checkbox Secrecion Purulenta Ombligo
	var ckbSecPurulentaOmbligo = "";
	var checkboxSecPurulentaOmbligo = document.getElementsByName("sec_purulenta_ombligo");
		for (var x = 0; x < checkboxSecPurulentaOmbligo.length; x ++) {
			if(checkboxSecPurulentaOmbligo[x].checked) {
				ckbSecPurulentaOmbligo=checkboxSecPurulentaOmbligo[x].id;
			}                               
		}
		
	//Checkbox Equimosis
	var ckbEquimosis = "";
	var checkboxEquimosis = document.getElementsByName("equimosis");
		for (var x = 0; x < checkboxEquimosis.length; x ++) {
			if(checkboxEquimosis[x].checked) {
				ckbEquimosis=checkboxEquimosis[x].id;
			}                               
		}
		
	//Checkbox Petequias
	var ckbPetequias = "";
	var checkboxPetequias = document.getElementsByName("petequias");
		for (var x = 0; x < checkboxPetequias.length; x ++) {
			if(checkboxPetequias[x].checked) {
				ckbPetequias=checkboxPetequias[x].id;
			}                               
		}
		
	//Checkbox Placas Blanquecinas
	var ckbPlacasBlanquecinas = "";
	var checkboxPlacasBlanquecinas = document.getElementsByName("placas_blanquecinas");
		for (var x = 0; x < checkboxPlacasBlanquecinas.length; x ++) {
			if(checkboxPlacasBlanquecinas[x].checked) {
				ckbPlacasBlanquecinas=checkboxPlacasBlanquecinas[x].id;
			}                               
		}
		
	//Checkbox Hemorragia
	var ckbHemorragia = "";
	var checkboxHemorragia = document.getElementsByName("hemorragia");
		for (var x = 0; x < checkboxHemorragia.length; x ++) {
			if(checkboxHemorragia[x].checked) {
				ckbHemorragia=checkboxHemorragia[x].id;
			}                               
		}
	
	//Checkbox Llenado Capilar
	var ckbLlenadoCapilar = "";
	var checkboxLlenadoCapilar = document.getElementsByName("llenado_capilar");
		for (var x = 0; x < checkboxLlenadoCapilar.length; x ++) {
			if(checkboxLlenadoCapilar[x].checked) {
				ckbLlenadoCapilar=checkboxLlenadoCapilar[x].id;
			}                               
		}
		
	//Checkbox Distension Abdominal
	var ckbDistensionAbdominal = "";
	var checkboxDistensionAbdominal = document.getElementsByName("distension_abdominal");
		for (var x = 0; x < checkboxDistensionAbdominal.length; x ++) {
			if(checkboxDistensionAbdominal[x].checked) {
				ckbDistensionAbdominal=checkboxDistensionAbdominal[x].id;
			}                               
		}
		
	//Checkbox Fontanela Abombada
	var ckbFontanelaAbombada = "";
	var checkboxFontanelaAbombada = document.getElementsByName("fontanela_abombada");
		for (var x = 0; x < checkboxFontanelaAbombada.length; x ++) {
			if(checkboxFontanelaAbombada[x].checked) {
				ckbFontanelaAbombada=checkboxFontanelaAbombada[x].id;
			}                               
		}
		
	//Checkbox Ojos Hundidos
	var ckbOjosHundidos = "";
	var checkboxOjosHundidos = document.getElementsByName("ojos_hundidos");
		for (var x = 0; x < checkboxOjosHundidos.length; x ++) {
			if(checkboxOjosHundidos[x].checked) {
				ckbOjosHundidos=checkboxOjosHundidos[x].id;
			}                               
		}
		
	var estadoGeneral=document.getElementById("estado_general").value;
	var pliegueCutaneo=document.getElementById("pliegue_cutaneo").value;

		/*////////////////RESPUESTAS ENFERMEDAD GRAVE O INFECCION LOCAL///////////////////////////////*/
	//Checkbox Enfermedad Grave
	var ckbEnfermedadGrave = "";
	var checkboxEnfermedadGrave = document.getElementsByName("enfermedad_grave");
		for (var x = 0; x < checkboxEnfermedadGrave.length; x ++) {
			if(checkboxEnfermedadGrave[x].checked) {
				ckbEnfermedadGrave=checkboxEnfermedadGrave[x].id;
			}                               
		}
		
	//Checkbox Infeccion Local
	var ckbInfeccionLocal = "";
	var checkboxInfeccionLocal = document.getElementsByName("infeccion_local");
		for (var x = 0; x < checkboxInfeccionLocal.length; x ++) {
			if(checkboxInfeccionLocal[x].checked) {
				ckbInfeccionLocal=checkboxInfeccionLocal[x].id;
			}                               
		}
		
	//Checkbox No Enfermedad Grave No Infeccion Local
	var ckbNoEnfNoInf = "";
	var checkboxNoEnfNoInf = document.getElementsByName("no_enf_grave_no_inf_local");
		for (var x = 0; x < checkboxNoEnfNoInf.length; x ++) {
			if(checkboxNoEnfNoInf[x].checked) {
				ckbNoEnfNoInf=checkboxNoEnfNoInf[x].id;
			}                               
		}
		
	//Checkbox Deshidratacion
	var ckbDeshidratacion = "";
	var checkboxDeshidratacion = document.getElementsByName("deshidratacion");
		for (var x = 0; x < checkboxDeshidratacion.length; x ++) {
			if(checkboxDeshidratacion[x].checked) {
				ckbDeshidratacion=checkboxDeshidratacion[x].id;
			}                               
		}
		
	//Checkbox No Deshidratacion
	var ckbNoDeshidratacion = "";
	var checkboxNoDeshidratacion = document.getElementsByName("no_deshidratacion");
		for (var x = 0; x < checkboxNoDeshidratacion.length; x ++) {
			if(checkboxNoDeshidratacion[x].checked) {
				ckbNoDeshidratacion=checkboxNoDeshidratacion[x].id;
			}                               
		}
		
	//Checkbox Diarrea Prolongada
	var ckbDiarreaProlongada = "";
	var checkboxDiarreaProlongada = document.getElementsByName("diarrea_prolongada");
		for (var x = 0; x < checkboxDiarreaProlongada.length; x ++) {
			if(checkboxDiarreaProlongada[x].checked) {
				ckbDiarreaProlongada=checkboxDiarreaProlongada[x].id;
			}                               
		}
		
	//Checkbox Diarrea Sangre
	var ckbDiarreaSangre = "";
	var checkboxDiarreaSangre = document.getElementsByName("diarrea_sangre");
		for (var x = 0; x < checkboxDiarreaSangre.length; x ++) {
			if(checkboxDiarreaSangre[x].checked) {
				ckbDiarreaSangre=checkboxDiarreaSangre[x].id;
			}                               
		}	
	
	/******************************************************************************************************/
	/*************************************CRECIMIENTO Y PRÁCTICAS DE ALIMENTACIÓN**************************/
	/******************************************************************************************************/
	//Radio Dificultad Alimentarse
	var radDificultadAlimentarse = "";
	var radioDificultadAlimentarse = document.getElementsByName("rad_dificultad_alimentarse");
		for (var x = 0; x < radioDificultadAlimentarse.length; x ++) {
			if(radioDificultadAlimentarse[x].checked) {
				radDificultadAlimentarse=radioDificultadAlimentarse[x].id;
			}                               
		}
	if (radDificultadAlimentarse==false) {
		alert("Seleccione si tiene alguna dificultad para alimentarse");
	} else {
		
	var cualDifAlimentarse=document.getElementById("cual_dificultad_alimentarse").value;
	
	//Radio Dejado Comer
	var radDejadoComer = "";
	var radioDejadoComer = document.getElementsByName("rad_ha_dejado_comer");
		for (var x = 0; x < radioDejadoComer.length; x ++) {
			if(radioDejadoComer[x].checked) {
				radDejadoComer=radioDejadoComer[x].id;
			}                               
		}
	if (radDejadoComer==false) {
		alert("Seleccione si ha dejado de comer");
	} else {
		
	var desdeCuandoDejadoComer=document.getElementById("desde_cuando_ha_dejado_comer").value;
	
	//Radio Leche Materna
	var radLecheMaterna = "";
	var radioLecheMaterna = document.getElementsByName("rad_leche_materna");
		for (var x = 0; x < radioLecheMaterna.length; x ++) {
			if(radioLecheMaterna[x].checked) {
				radLecheMaterna=radioLecheMaterna[x].id;
			}                               
		}
	if (radLecheMaterna==false) {
		alert("Seleccione si se alimenta con leche materna");
	} else {
	
	var formaExclusiva=document.getElementById("forma_exclusiva").value;
	var cuantasVecesFormaExclusiva=document.getElementById("cuantas_veces_forma_exclusiva").value;
	
	//Radio Utiliza Chupo
	var radUtilizaChupo = "";
	var radioUtilizaChupo = document.getElementsByName("rad_utiliza_chupo");
		for (var x = 0; x < radioUtilizaChupo.length; x ++) {
			if(radioUtilizaChupo[x].checked) {
				radUtilizaChupo=radioUtilizaChupo[x].id;
			}                               
		}
	if (radUtilizaChupo==false) {
		alert("Seleccione si utiliza chupo");
	} else {
	
	//Radio Otra Leche
	var radOtraLeche = "";
	var radioOtraLeche = document.getElementsByName("rad_otra_leche");
		for (var x = 0; x < radioOtraLeche.length; x ++) {
			if(radioOtraLeche[x].checked) {
				radOtraLeche=radioOtraLeche[x].id;
			}                               
		}
	if (radOtraLeche==false) {
		alert("Seleccione si recibe otra leche, otro aliemnto o bebidas");
	} else {
		
	var cualesFrecuenciaOtraLeche=document.getElementById("cuales_frecuencia_otra_leche").value;
	var preparaOtraLeche=document.getElementById("prepara_otra_leche").value;
	var queUtilizaAlimentarlo=document.getElementById("que_utiliza_alimentarlo").value;
	var pesoEdad=document.getElementById("peso_edad").value;
	if (pesoEdad=="") {
		alert("Escriba el peso para la edad");
		document.getElementById("peso_edad").focus();
	} else {

	var pesoTalla=document.getElementById("peso_talla").value;
	if (pesoTalla=="") {
		alert("Escriba el peso para la talla");
		document.getElementById("peso_talla").focus();
	} else {

	var tendenciaPeso=document.getElementById("tendencia_peso").value;
	if (tendenciaPeso=="") {
		alert("Elija la tendencia para el peso");
		document.getElementById("tendencia_peso").focus();
	} else {
	
	//Checkbox Boca Abierta
	var ckbBocaAbierta = "";
	var checkboxBocaAbierta = document.getElementsByName("boca_abierta");
		for (var x = 0; x < checkboxBocaAbierta.length; x ++) {
			if(checkboxBocaAbierta[x].checked) {
				ckbBocaAbierta=checkboxBocaAbierta[x].id;
			}                               
		}	
		
	//Checkbox Toca Seno
	var ckbTocaSeno = "";
	var checkboxTocaSeno = document.getElementsByName("toca_seno");
		for (var x = 0; x < checkboxTocaSeno.length; x ++) {
			if(checkboxTocaSeno[x].checked) {
				ckbTocaSeno=checkboxTocaSeno[x].id;
			}                               
		}	
		
	//Checkbox Labio Inferior
	var ckbLabioInferior = "";
	var checkboxLabioInferior = document.getElementsByName("labio_inferior");
		for (var x = 0; x < checkboxLabioInferior.length; x ++) {
			if(checkboxLabioInferior[x].checked) {
				ckbLabioInferior=checkboxLabioInferior[x].id;
			}                               
		}
		
	//Checkbox Areola Labio
	var ckbAreolaLabio = "";
	var checkboxAreolaLabio = document.getElementsByName("areola_labio");
		for (var x = 0; x < checkboxAreolaLabio.length; x ++) {
			if(checkboxAreolaLabio[x].checked) {
				ckbAreolaLabio=checkboxAreolaLabio[x].id;
			}                               
		}
		
	//Checkbox Cabeza Cuerpo Derecho
	var ckbCabCuerpoDerecho = "";
	var checkboxCabCuerpoDerecho = document.getElementsByName("cab_cuerpo_derecho");
		for (var x = 0; x < checkboxCabCuerpoDerecho.length; x ++) {
			if(checkboxCabCuerpoDerecho[x].checked) {
				ckbCabCuerpoDerecho=checkboxCabCuerpoDerecho[x].id;
			}                               
		}
		
	//Checkbox Direccion Pezon
	var ckbDireccionPezon = "";
	var checkboxDireccionPezon = document.getElementsByName("direccion_pezon");
		for (var x = 0; x < checkboxDireccionPezon.length; x ++) {
			if(checkboxDireccionPezon[x].checked) {
				ckbDireccionPezon=checkboxDireccionPezon[x].id;
			}                               
		}
		
	//Checkbox Hijo Frente Madre
	var ckbHijoFrenteMadre = "";
	var checkboxHijoFrenteMadre = document.getElementsByName("hijo_frente_madre");
		for (var x = 0; x < checkboxHijoFrenteMadre.length; x ++) {
			if(checkboxHijoFrenteMadre[x].checked) {
				ckbHijoFrenteMadre=checkboxHijoFrenteMadre[x].id;
			}                               
		}
		
	//Checkbox Madre Sostiene Cuerpo
	var ckbMadreSostieneCuerpo = "";
	var checkboxMadreSostieneCuerpo = document.getElementsByName("madre_sostiene_cuerpo");
		for (var x = 0; x < checkboxMadreSostieneCuerpo.length; x ++) {
			if(checkboxMadreSostieneCuerpo[x].checked) {
				ckbMadreSostieneCuerpo=checkboxMadreSostieneCuerpo[x].id;
			}                               
		}
		
	//Checkbox Lenta Profunda
	var ckbLentaProfunda = "";
	var checkboxLentaProfunda = document.getElementsByName("lenta_profunda");
		for (var x = 0; x < checkboxLentaProfunda.length; x ++) {
			if(checkboxLentaProfunda[x].checked) {
				ckbLentaProfunda=checkboxLentaProfunda[x].id;
			}                               
		}
	
		/*////////////////RESPUESTAS ALIMENTACION///////////////////////////////*/
	//Checkbox Problema Severo Alimentacion
	var ckbProbSeveroAlimentacion = "";
	var checkboxProbSeveroAlimentacion = document.getElementsByName("prob_severo_alimentacion");
		for (var x = 0; x < checkboxProbSeveroAlimentacion.length; x ++) {
			if(checkboxProbSeveroAlimentacion[x].checked) {
				ckbProbSeveroAlimentacion=checkboxProbSeveroAlimentacion[x].id;
			}                               
		}	
		
	//Checkbox Peso Muy Bajo
	var ckbPesoMuyBajo = "";
	var checkboxPesoMuyBajo = document.getElementsByName("peso_muy_bajo");
		for (var x = 0; x < checkboxPesoMuyBajo.length; x ++) {
			if(checkboxPesoMuyBajo[x].checked) {
				ckbPesoMuyBajo=checkboxPesoMuyBajo[x].id;
			}                               
		}
		
	//Checkbox Problemas Alimentacion
	var ckbProblemasAlimentacion = "";
	var checkboxProblemasAlimentacion = document.getElementsByName("problemas_alimentacion");
		for (var x = 0; x < checkboxProblemasAlimentacion.length; x ++) {
			if(checkboxProblemasAlimentacion[x].checked) {
				ckbProblemasAlimentacion=checkboxProblemasAlimentacion[x].id;
			}                               
		}
		
	//Checkbox Peso Bajo
	var ckbPesoBajo = "";
	var checkboxPesoBajo = document.getElementsByName("peso_bajo");
		for (var x = 0; x < checkboxPesoBajo.length; x ++) {
			if(checkboxPesoBajo[x].checked) {
				ckbPesoBajo=checkboxPesoBajo[x].id;
			}                               
		}
		
	//Checkbox Practicas Alimentacion
	var ckbPracticasAlimentacion = "";
	var checkboxPracticasAlimentacion = document.getElementsByName("practicas_alimentacion");
		for (var x = 0; x < checkboxPracticasAlimentacion.length; x ++) {
			if(checkboxPracticasAlimentacion[x].checked) {
				ckbPracticasAlimentacion=checkboxPracticasAlimentacion[x].id;
			}                               
		}
		
	/******************************************************************************************************/
	/*************************************VERIFICAR DESARROLLO*********************************************/
	/******************************************************************************************************/
	//Radio Parientes Padres
	var radParientesPadres = "";
	var radioParientesPadres = document.getElementsByName("rad_parientes_padres");
		for (var x = 0; x < radioParientesPadres.length; x ++) {
			if(radioParientesPadres[x].checked) {
				radParientesPadres=radioParientesPadres[x].id;
			}                               
		}
	if (radParientesPadres==false) {
		alert("Seleccione si son parientes los padres");
	} else {
		
	//Radio Familiar Problema Mental
	var radFamProblemaMental = "";
	var radioFamProblemaMental = document.getElementsByName("rad_familiar_problema_mental");
		for (var x = 0; x < radioFamProblemaMental.length; x ++) {
			if(radioFamProblemaMental[x].checked) {
				radFamProblemaMental=radioFamProblemaMental[x].id;
			}                               
		}
	if (radFamProblemaMental==false) {
		alert("Seleccione si hay un familiar con problema mental o físico");
	} else {
		
	var quienCuidaNino=document.getElementById("quien_cuida_nino").value;
	if (quienCuidaNino=="") {
		alert("EScriba quien cuida al niño");
		document.getElementById("quien_cuida_nino").focus();
	} else {
	
	var desarrolloNino=document.getElementById("desarrollo_nino").value;
	if (desarrolloNino=="") {
		alert("Escriba como ve el desarrollo del niño");
		document.getElementById("desarrollo_nino").focus();
	} else {
	
	var antNatales=document.getElementById("ant_natales").value;
	if (antNatales=="") {
		alert("Escriba los antecedentes prenatales, natales y postnatales");
		document.getElementById("ant_natales").focus();
	} else {

	var alteracionFenitipica=document.getElementById("alteracion_fenitipica").value;
	if (alteracionFenitipica=="") {
		alert("Escriba la alteración fenitípica");
		document.getElementById("alteracion_fenitipica").focus();
	} else {
	
	var PC=document.getElementById("pc").value;
	if (PC=="") {
		alert("Escriba el perimetro cefalico en la sección Desarrollo");
		document.getElementById("pc").focus();
	} else {
	
	var PCE=document.getElementById("pc_e").value;
	if (PCE=="") {
		alert("Escriba el perimetro cefalico para la edad en la sección Desarrollo");
		document.getElementById("pc_e").focus();
	} else {
	
	//Checkbox Reflejo Moro
	var ckbReflejoMoro = "";
	var checkboxReflejoMoro = document.getElementsByName("reflejo_moro");
		for (var x = 0; x < checkboxReflejoMoro.length; x ++) {
			if(checkboxReflejoMoro[x].checked) {
				ckbReflejoMoro=checkboxReflejoMoro[x].id;
			}                               
		}	
		
	//Checkbox Reflejo Succion
	var ckbReflejoSuccion = "";
	var checkboxReflejoSuccion = document.getElementsByName("reflejo_succion");
		for (var x = 0; x < checkboxReflejoSuccion.length; x ++) {
			if(checkboxReflejoSuccion[x].checked) {
				ckbReflejoSuccion=checkboxReflejoSuccion[x].id;
			}                               
		}	
		
	//Checkbox Reflejo Cocleo
	var ckbReflejoCocleo = "";
	var checkboxReflejoCocleo = document.getElementsByName("reflejo_cocleo");
		for (var x = 0; x < checkboxReflejoCocleo.length; x ++) {
			if(checkboxReflejoCocleo[x].checked) {
				ckbReflejoCocleo=checkboxReflejoCocleo[x].id;
			}                               
		}
		
	//Checkbox Manos Cerradas
	var ckbManosCerradas = "";
	var checkboxManosCerradas = document.getElementsByName("manos_cerradas");
		for (var x = 0; x < checkboxManosCerradas.length; x ++) {
			if(checkboxManosCerradas[x].checked) {
				ckbManosCerradas=checkboxManosCerradas[x].id;
			}                               
		}
		
	//Checkbox Brazos Piernas Flexionadas
	var ckbBraPierFlexionadas = "";
	var checkboxBraPierFlexionadas = document.getElementsByName("brazos_piernas_flexionadas");
		for (var x = 0; x < checkboxBraPierFlexionadas.length; x ++) {
			if(checkboxBraPierFlexionadas[x].checked) {
				ckbBraPierFlexionadas=checkboxBraPierFlexionadas[x].id;
			}                               
		}
		
	//Checkbox Vocaliza
	var ckbVocaliza = "";
	var checkboxVocaliza = document.getElementsByName("vocaliza");
		for (var x = 0; x < checkboxVocaliza.length; x ++) {
			if(checkboxVocaliza[x].checked) {
				ckbVocaliza=checkboxVocaliza[x].id;
			}                               
		}
		
	//Checkbox Sonrisa Social
	var ckbSonrisaSocial = "";
	var checkboxSonrisaSocial = document.getElementsByName("sonrisa_social");
		for (var x = 0; x < checkboxSonrisaSocial.length; x ++) {
			if(checkboxSonrisaSocial[x].checked) {
				ckbSonrisaSocial=checkboxSonrisaSocial[x].id;
			}                               
		}
		
	//Checkbox Movimiento Piernas
	var ckbMovimientoPiernas = "";
	var checkboxMovimientoPiernas = document.getElementsByName("movimiento_piernas");
		for (var x = 0; x < checkboxMovimientoPiernas.length; x ++) {
			if(checkboxMovimientoPiernas[x].checked) {
				ckbMovimientoPiernas=checkboxMovimientoPiernas[x].id;
			}                               
		}
		
	//Checkbox Sigue Objetos
	var ckbSigueObjetos = "";
	var checkboxSigueObjetos = document.getElementsByName("sigue_objetos");
		for (var x = 0; x < checkboxSigueObjetos.length; x ++) {
			if(checkboxSigueObjetos[x].checked) {
				ckbSigueObjetos=checkboxSigueObjetos[x].id;
			}                               
		}
	
		/*////////////////RESPUESTAS DESARROLLO///////////////////////////////*/
	//Checkbox Retraso Desarrollo
	var ckbRetrasoDesarrollo = "";
	var checkboxRetrasoDesarrollo = document.getElementsByName("retraso_desarrollo");
		for (var x = 0; x < checkboxRetrasoDesarrollo.length; x ++) {
			if(checkboxRetrasoDesarrollo[x].checked) {
				ckbRetrasoDesarrollo=checkboxRetrasoDesarrollo[x].id;
			}                               
		}
		
	//Checkbox Riesgo Problema
	var ckbRiesgoProblema = "";
	var checkboxRiesgoProblema = document.getElementsByName("riesgo_problema");
		for (var x = 0; x < checkboxRiesgoProblema.length; x ++) {
			if(checkboxRiesgoProblema[x].checked) {
				ckbRiesgoProblema=checkboxRiesgoProblema[x].id;
			}                               
		}
		
	//Checkbox Desarrollo Normal
	var ckbDesarrolloNormal = "";
	var checkboxDesarrolloNormal = document.getElementsByName("desarrollo_normal");
		for (var x = 0; x < checkboxDesarrolloNormal.length; x ++) {
			if(checkboxDesarrolloNormal[x].checked) {
				ckbDesarrolloNormal=checkboxDesarrolloNormal[x].id;
			}                               
		}
		
	/******************************************************************************************************/
	/*************************************DIAGNOSTICOS Y RECOMENDACIONES***********************************/
	/******************************************************************************************************/
	var completarExamenFisico=document.getElementById("completar_examen_fisico").value;
	//var diagnosticoExamenFisico=document.getElementById("diagnostico_examen_fisico").value;
	var diagnosticoPac=document.getElementById("diagnostico_pac").value;
	var codigoCiePac=document.getElementById("codigo_cie_pac").value;
	var tratarPac=document.getElementById("tratar_pac").value;
	var volverInmediato=document.getElementById("volver_inmediato").value;
	var recienNacido=document.getElementById("recien_nacido").value;
	var madre=document.getElementById("madre").value;
	var ninoSano=document.getElementById("nino_sano").value;
	var referidoConsulta=document.getElementById("referido_consulta").value;
	var programaVacunacion=document.getElementById("programa_vacunacion").value;
	var recomendacionesBuenTrato=document.getElementById("recomendaciones_buen_trato").value;
	var otrasRecomendaciones=document.getElementById("otras_recomendaciones").value;
	
	var CodReporte="";	
	
			ajax=getXMLObject();
			ajax.open("POST","AiepiMultiplePacientes",true);

			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					CodReporte=document.getElementById("txtCodReporte").value=ajax.responseText;
					//activarelementoFormulario("BotonAntecedentes");
					alert("Ingreso Exitoso");
					mostrarInformesAIEPI0a2(CodReporte);
					RedireccionFormato(CodPaciente);
					//window.location="hic_SeleccionarPacientes.jsp?pacientes="+CodPaciente;
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=2.3&motConsulta=" + motConsulta
					+ "&CodPaciente=" + CodPaciente
					+ "&CodUsuario=" +  CodUsuario
					+ "&CodAdmision=" + CodAdmision
/*Encabezado*/		+ "&antEmbarazo=" + antEmbarazo
					+ "&pesoNacer=" + pesoNacer
					+ "&tallaNacer=" + tallaNacer
					+ "&edadGestacional=" + edadGestacional
					+ "&hemoclasificacion=" + hemoclasificacion
					+ "&pesoActual=" + pesoActual
					+ "&talla=" + talla
					+ "&perimetroCefalico=" + perimetroCefalico
					+ "&frecuenciaCardiaca=" + frecuenciaCardiaca
					+ "&frecuenciaRespiratoria=" + frecuenciaRespiratoria
					+ "&temperatura=" + temperatura
					+ "&centigrados=" + centigrados
					
/*RIESGO GESTACION*/+ "&radContPrenatal=" + radContPrenatal
					+ "&numControlPrenatal=" + numControlPrenatal
					+ "&radHipotermia=" + radHipotermia
					+ "&radConvulsiones=" + radConvulsiones
					+ "&radVomito=" + radVomito
					+ "&vomitaTodo=" + vomitaTodo
					+ "&radDiarrea=" + radDiarrea
					+ "&cuandoDiarrea=" + cuandoDiarrea
					+ "&radDificultadRespirar=" + radDificultadRespirar
					+ "&expliqueDificultadRespirar=" + expliqueDificultadRespirar
					+ "&radSangreHeces=" + radSangreHeces
					+ "&panalesOrinados=" + panalesOrinados
/*Ckb Enfermedad*/	+ "&ckbMueveSolo=" + ckbMueveSolo
					+ "&ckbLetargico=" + ckbLetargico
					+ "&ckbLuceMal=" + ckbLuceMal
					+ "&ckbIrritable=" + ckbIrritable
					+ "&ckbPalidez=" + ckbPalidez
					+ "&ckbCianosis=" + ckbCianosis
					+ "&ckbApneas=" + ckbApneas
					+ "&ckbBilirrubinas=" + ckbBilirrubinas
					+ "&ckbEstridor=" + ckbEstridor
					+ "&ckbFR=" + ckbFR
					+ "&ckbAleteoNasal=" + ckbAleteoNasal
					+ "&ckbQuejido=" + ckbQuejido
					+ "&ckbSibilancia=" + ckbSibilancia
					+ "&ckbFC=" + ckbFC
					+ "&ckbSupuracionOido=" + ckbSupuracionOido
					+ "&ckbTirajeSubcostal=" + ckbTirajeSubcostal
					+ "&ckbSecPurulentaConjuntival=" + ckbSecPurulentaConjuntival
					+ "&ckbEdemaPalpebral=" + ckbEdemaPalpebral
					+ "&ckbEritemaPeriumbilical=" + ckbEritemaPeriumbilical
					+ "&pustulasPiel=" + pustulasPiel
					+ "&ckbSecPurulentaOmbligo=" + ckbSecPurulentaOmbligo
					+ "&ckbEquimosis=" + ckbEquimosis
					+ "&ckbPetequias=" + ckbPetequias
					+ "&ckbPlacasBlanquecinas=" + ckbPlacasBlanquecinas
					+ "&ckbHemorragia=" + ckbHemorragia
					+ "&ckbLlenadoCapilar=" + ckbLlenadoCapilar
					+ "&ckbDistensionAbdominal=" + ckbDistensionAbdominal
					+ "&ckbFontanelaAbombada=" + ckbFontanelaAbombada
					+ "&ckbOjosHundidos=" + ckbOjosHundidos
					+ "&estadoGeneral=" + estadoGeneral
					+ "&pliegueCutaneo=" + pliegueCutaneo
/*Resp Enfermedad*/	+ "&ckbEnfermedadGrave=" + ckbEnfermedadGrave
					+ "&ckbInfeccionLocal=" + ckbInfeccionLocal
					+ "&ckbNoEnfNoInf=" + ckbNoEnfNoInf
					+ "&ckbDeshidratacion=" + ckbDeshidratacion
					+ "&ckbNoDeshidratacion=" + ckbNoDeshidratacion
					+ "&ckbDiarreaProlongada=" + ckbDiarreaProlongada
					+ "&ckbDiarreaSangre=" + ckbDiarreaSangre
/*Alimentacion*/	+ "&radDificultadAlimentarse=" + radDificultadAlimentarse
					+ "&cualDifAlimentarse=" + cualDifAlimentarse
					+ "&radDejadoComer=" + radDejadoComer
					+ "&desdeCuandoDejadoComer=" + desdeCuandoDejadoComer
					+ "&radLecheMaterna=" + radLecheMaterna
					+ "&formaExclusiva=" + formaExclusiva
					+ "&cuantasVecesFormaExclusiva=" + cuantasVecesFormaExclusiva
					+ "&radUtilizaChupo=" + radUtilizaChupo
					+ "&radOtraLeche=" + radOtraLeche
					+ "&cualesFrecuenciaOtraLeche=" + cualesFrecuenciaOtraLeche
					+ "&preparaOtraLeche=" + preparaOtraLeche
					+ "&queUtilizaAlimentarlo=" + queUtilizaAlimentarlo
					+ "&pesoEdad=" + pesoEdad
					+ "&pesoTalla=" + pesoTalla
					+ "&tendenciaPeso=" + tendenciaPeso
/*Ckb Alimentacion*/+ "&ckbBocaAbierta=" + ckbBocaAbierta
					+ "&ckbTocaSeno=" + ckbTocaSeno
					+ "&ckbLabioInferior=" + ckbLabioInferior
					+ "&ckbAreolaLabio=" + ckbAreolaLabio
					+ "&ckbCabCuerpoDerecho=" + ckbCabCuerpoDerecho
					+ "&ckbDireccionPezon=" + ckbDireccionPezon
					+ "&ckbHijoFrenteMadre=" + ckbHijoFrenteMadre
					+ "&ckbMadreSostieneCuerpo=" + ckbMadreSostieneCuerpo
					+ "&ckbLentaProfunda=" + ckbLentaProfunda
/*Resp Alimentacio*/+ "&ckbProbSeveroAlimentacion=" + ckbProbSeveroAlimentacion
					+ "&ckbPesoMuyBajo=" + ckbPesoMuyBajo
					+ "&ckbProblemasAlimentacion=" + ckbProblemasAlimentacion
					+ "&ckbPesoBajo=" + ckbPesoBajo
					+ "&ckbPracticasAlimentacion=" + ckbPracticasAlimentacion					
/*Desarrollo*/		+ "&radParientesPadres=" + radParientesPadres
					+ "&radFamProblemaMental=" + radFamProblemaMental
					+ "&quienCuidaNino=" + quienCuidaNino
					+ "&desarrolloNino=" + desarrolloNino
					+ "&antNatales=" + antNatales
					+ "&alteracionFenitipica=" + alteracionFenitipica
					+ "&PC=" + PC
					+ "&PCE=" + PCE
/*Ckb Desarrollo*/	+ "&ckbReflejoMoro=" + ckbReflejoMoro
					+ "&ckbReflejoSuccion=" + ckbReflejoSuccion
					+ "&ckbReflejoCocleo=" + ckbReflejoCocleo
					+ "&ckbManosCerradas=" + ckbManosCerradas
					+ "&ckbBraPierFlexionadas=" + ckbBraPierFlexionadas
					+ "&ckbVocaliza=" + ckbVocaliza
					+ "&ckbSonrisaSocial=" + ckbSonrisaSocial
					+ "&ckbMovimientoPiernas=" + ckbMovimientoPiernas
					+ "&ckbSigueObjetos=" + ckbSigueObjetos
/*Resp Desarrollo*/	+ "&ckbRetrasoDesarrollo=" + ckbRetrasoDesarrollo
					+ "&ckbRiesgoProblema=" + ckbRiesgoProblema
					+ "&ckbDesarrolloNormal=" + ckbDesarrolloNormal
/*Recomendaciones*/	+ "&completarExamenFisico=" + completarExamenFisico
					//+ "&diagnosticoExamenFisico=" + diagnosticoExamenFisico
					+ "&diagnosticoPac=" + diagnosticoPac
					+ "&codigoCiePac=" + codigoCiePac
					+ "&tratarPac=" + tratarPac
					+ "&volverInmediato=" + volverInmediato
					+ "&recienNacido=" + recienNacido
					+ "&madre=" + madre
					+ "&ninoSano=" + ninoSano
					+ "&referidoConsulta=" + referidoConsulta
					+ "&programaVacunacion=" + programaVacunacion
					+ "&recomendacionesBuenTrato=" + recomendacionesBuenTrato
					+ "&otrasRecomendaciones=" + otrasRecomendaciones); //Posting txtname to Servlet*/	
			}//Mot Consulta
			}//Ant Embarazo
			}//Peso Nacer
			}//Talla Nacer
			}//Edad Gestacional
			}//Hemoclasificacion
			}//Peso Actual
			}//Talla
			}//Perimetro Cefalico
			}//Frecuancia Cardiaca
			}//Frecuencia Respiratoria
			}//Temperatura
			}//Centigrados
	
			}//Rad Control Prenatal
			}//Rad Fiebre
			}//Rad Hipotermia
			}//Rad Convulsiones
			}//Rad Vomito
			}//Rad Diarrea
			}//Rad Dificultad Respirar
			}//Rad Sangre Heces
			}//Pañales Orinados
			}//Rad Dificultad Alimentarse
			}//Rad Dejado Comer
			}//Rad Leche Materna
			}//Rad Utiliza Chupo
			}//Rad Otra Leche
			}//Peso Edad
			}//Peso Talla
			}//Tendencia Peso
			}//Rad Parientes Padres
			}//Rad Familiar Problema Mental
			}//Quien Cuida Nino
			}//Desarrollo Niño
			}//Ant Natales
			}//Alteracion Fenitipica
			}//PC
			}//PCE
}
/***********************************************************************/
/*****************FIN GUARDAR INFORME EMBARAZADAS AIEPI*****************/
/***********************************************************************/

function SoloNumeros(e){
	//alert();
	var letras1="0123456789.";
	var caracter="";
	var returnval=e.value;
	var code=e.KeyCode;
	
	for (var i=0; i < returnval.length; i++) {
		caracter = returnval.substring(i, i + 1);
		code=caracter.KeyCode;
		if((code==209)||(code==241)){
			//alert("code"+code);
		}else{
			if(letras1.indexOf(caracter) != -1) {
				var a=e.value;			
				if((a==".")||(a=="0..")){
					alert('El Campo Solo Acepta Valores Numericos.');
					e.value="";
					e.focus();
					e.select();
				}
			}else{
				alert('El Campo Solo Acepta Valores Numericos.');
				e.value="";
				e.focus();
				e.select();
			}
		}

	}
}

function fecha(){
	  var time1 = new Date()
	  var anio = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((anio < 10) ? "0" : "") + anio
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	  document.getElementById('txtFecha').value = temp1
	  id = setTimeout("fecha()",1000)
	  }

function hora(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txtHora').value = temp;
	  id = setTimeout("hora()",1000)
	  }

/*function RedireccionFormatoCex(CodPac){
	var NomDiv=CodPac;	
	var usuario=document.getElementById("txtCodusuario").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			document.getElementById("HistoriaPaciente").innerHTML = ajax.responseText;
			document.getElementById("CodPac").value=CodPac;
			window.location.reload();
			//FormatosCE();
		}
	};
		
 	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RedireccionFormatoCex&CodiPaciente="+CodPac+"&CodiUsuario="+usuario); //Posting txtname to Servlet*/
//}

function reload(){
	window.location.reload();
}

function FormatosCE(){
	DivValor=document.getElementById('HistoriaPaciente');
	var usuario=document.getElementById("txtCodusuario").value; 
	var CodPaciente=document.getElementById("CodPac").value;
	ajax=getXMLObject();
	    ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {			
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
				CargarFormatosCE();
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=19&CodUsuario="+usuario+"&CodiPaciente="+CodPaciente); //Posting txtname to Servlet
	    
}

function CargarFormatosCE(){
	var CodPac=document.getElementById("CodPac").value;
	var CodHorarioMedico=document.getElementById("CodHorarioMedico").value;
	var CodUsuario=document.getElementById("txtCodusuario").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita");
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById('FormatosPaciente').innerHTML = ajax.responseText;
			document.getElementById("btnVerFormatos").disabled=false;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	//alert("valor=21&CodigoPaciente="+CodPac+"&CodHorarioMedico="+CodHorarioMedico+"&CodUsuario="+CodUsuario);
	ajax.send("valor=21&CodigoPaciente="+CodPac+"&CodHorarioMedico="+CodHorarioMedico+"&CodUsuario="+CodUsuario);
}
 
/* function ConsultarAiepi0a2(){
		DivValor=document.getElementById('HistoriaPaciente');
		CodPaciente=document.getElementById("CodPac").value;
		ajax=getXMLObject();
		    ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
		    ajax.onreadystatechange  = function(){
		    	if (ajax.readyState == 4) {				
		    		Contenido=ajax.responseText;
					DivValor.innerHTML=Contenido;
		  	   	}
			  }
		  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=4.1&CodPaciente="+CodPaciente); //Posting txtname to Servlet
		}
 
 function ConsultarAiepi2a5(){
		DivValor=document.getElementById('HistoriaPaciente');
		CodPaciente=document.getElementById("CodPac").value;
		ajax=getXMLObject();
		    ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
		    ajax.onreadystatechange  = function(){
		    	if (ajax.readyState == 4) {				
		    		Contenido=ajax.responseText;
					DivValor.innerHTML=Contenido;
		  	   	}
			  }
		  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=4.2&CodPaciente="+CodPaciente); //Posting txtname to Servlet
		}*/
 
 function ConsultarAiepiGeneral(){
		DivValor=document.getElementById('HistoriaPaciente');
		CodPaciente=document.getElementById("CodPac").value;
		ajax=getXMLObject();
		    ajax.open("POST","AiepiMultiplePacientes",true); //getname will be the servlet name
		    ajax.onreadystatechange  = function(){
		    	if (ajax.readyState == 4) {				
		    		Contenido=ajax.responseText;
					DivValor.innerHTML=Contenido;
		  	   	}
			  }
		  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=4.5&CodPaciente="+CodPaciente); //Posting txtname to Servlet
		}
 
 /*function MostrarAiepiEmbarazadas(){
		DivValor=document.getElementById('HistoriaPaciente');
		CodPaciente=document.getElementById("CodPac").value;
		ajax=getXMLObject();
		    ajax.open("POST","AiepiMultiplePacientes",true); //getname will be the servlet name
		    ajax.onreadystatechange  = function(){
		    	if (ajax.readyState == 4) {				
		    		Contenido=ajax.responseText;
					DivValor.innerHTML=Contenido;
		  	   	}
			  }
		  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=AiepiEmb&CodPaciente="+CodPaciente); //Posting txtname to Servlet
		}*/