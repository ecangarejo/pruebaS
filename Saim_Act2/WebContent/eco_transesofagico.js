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

var generopaciente =""; 
var codpacientediagnostico ="";
function BuscarPaciente(){
	
	var TipoDocumento = document.getElementById("cbafiliacion").value;
	var NumeroDocumento = document.getElementById("txtnumdoc").value;
	
	if (NumeroDocumento == "") {
		alert("Escriba Numero Documento.");
		document.getElementById('txtnumdoc').focus();
	} else {
		
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrearTransesofagico", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var datos = ajax.responseText.split("|");
				if (datos[0] == 1) {
					//cargar_canvas_odontograma();
					document.getElementById('datos_ingreso_paciente').innerHTML = datos[2];
					codpacientediagnostico = datos[1];
					desactivarelementoFormulario("cbafiliacion");
		            desactivarelementoFormulario("txtnumdoc");
		            desactivarelementoFormulario("btnBuscarPac");
		            //aparecerElemento("informe_formulario_general");
		            //datos_inf_general();
		            
		            
		           // vistapreviaFormularios();
		         
				} else {
					if (codpacientediagnostico.length <= 0) {
						document.getElementById('datos_ingreso_paciente').innerHTML = datos[1];
					   	window.location.href = "adm_IngresarDemografico.jsp";
					} else {
						alert(datos[1]);
					}

				}

			}
			
		};
		ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
        ajax.send("valor=1&TipoDocumento=" + TipoDocumento
		+ "&NumeroDocumento=" + NumeroDocumento); // Posting txtname
       //alert("valor=1&TipoDocumento=" + TipoDocumento + "&NumeroDocumento=" + NumeroDocumento);
	}
}


//funcion que recarga la pagina
function anular_informe(){
	window.location.reload();
	
}

//funcion que toma todos los datos del informe y los envia al controlador
function enviar_informe(){
	var usu = recopilarinfotextbox("txtUsuario");
	var indicacion_DATA= recopilarValueCombobox("cmbIndicacion");
	
	if(indicacion_DATA=="Seleccione"){
		indicacion_DATA="0";
	
	
	}
	
	var observaciones_DATA= "";
	observaciones_DATA +=recopilarinfotextbox("txtEcoAurIzq")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtEcoValMitral")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtEcoVentIzq")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtEcoValAort")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtEcoAorta")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtEcoSeptum")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtVentDer")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtEcoAurDer")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtEcoValTri")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtValPul")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtEcoPericardio")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtEcoObs")+"&";
	observaciones_DATA +=recopilarinfotextbox("txtEcoCon")+"&";
	
	
	ajax = getXMLObject();
	ajax.open("POST","ControlCrearTransesofagico", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var resultado = ajax.responseText.split(';');
			
			alert(resultado[1]);
			
			if (resultado[0]=="1"){
				mostrarInformesEcotrans(resultado[2]);
			
				
			}else{
				alert("ERROR AL GENERAR INFORME");
			}
			
			
			window.location.reload();
		}						
	};
    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send(
	// Datos generales Informe
	"codpaciente=" + codpacientediagnostico
	+ "&codusuario=" +  recopilarinfotextbox("txtUsuario")
	+ "&indicacion="+ indicacion_DATA
	+ "&observaciones="+encodeURIComponent(observaciones_DATA)
	// Dato opcion envio
	+ "&valor=2"
    );
	
	
}


function mostrarInformesEcotrans(idcodInforme){
	
	pagina="eco_reporte_trans.jsp?CodInformeEcotrans="+idcodInforme;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Informe_ecotrans",opciones);
}

function check_no(id,idText){
	desaparecerElemento(id);
	reemplazarinfotextbox(idText, "")
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

function reemplazarinfotextbox(idText, valor) {

	document.getElementById(idText).value = valor;
}

function recopilarinfotextbox(idText) {
	//alert(idText);
	return document.getElementById(idText).value;
}

function tamanoinfotextbox(idText) {
	return document.getElementById(idText).value.length;
}


function recopilarTextCombobox(idCombo) {
		var index = document.getElementById(idCombo).selectedIndex;
		var seleccion = document.getElementById(idCombo).options[index].text;
		return seleccion;
}

function recopilarValueCombobox(idCombo) {
   	//alert(idCombo);
		var index = document.getElementById(idCombo).selectedIndex;
		var seleccion = document.getElementById(idCombo).options[index].value;
		return seleccion;
}

function recopilarindexCombobox(idCombo) {
		var index = document.getElementById(idCombo).selectedIndex;
		return index;
}

function reemplazarindexCombobox(idCombo, valor) {
	document.getElementById(idCombo).selectedIndex = valor;
}


function verEstadoCheckbox(grupocheck) {
	for ( var i = 0; ele = document.getElementsByName(grupocheck)[i]; i++) {
		if (ele.checked == 1) {
			return true;
		}
	}
	return false;
}

function recopilarinfoRadios(grupoRadios) { 

	  var Cadena = "";
	  for (var i = 0; ele = document.getElementsByName(grupoRadios)[i]; i++) 
		  if (ele.checked == true) { 
			  Cadena = ele.value; 
	      } 
	 return Cadena;
}

function reiniciarRadiosButtons(grupoRadios, valor){ // reiniciar todos los radio buttons con su valor por default

	  var radList = document.getElementsByName(grupoRadios);
	  for (var i = 0; i < radList.length; i++) {
	  if(radList[i].value == valor){
		  radList[i].checked = true;  
   }
}
		 
}
