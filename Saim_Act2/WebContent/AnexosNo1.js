function getXMLObject(){ // XML OBJECT
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

//Verifica si el paciente se encuentra en la base de datos, de los contrario
//lo envia IngresarDemografico.jsp para ingresar los datos del paciente
function pacienteActivo() {
	var TipoDocumento = document.getElementById("cbafiliacion").value;
	var NumeroDocumento = document.getElementById("txtnumdoc").value;
	
	if (NumeroDocumento == "") {
		alert("Escriba Numero Documento.");
		document.getElementById('txtnumdoc').focus();
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlAnexoNo1", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var datos = ajax.responseText.split("|");
				codpacientediagnostico = datos[1];
				if (datos[0] == 1) {
					aparecerElemento("datos");
					document.getElementById('datos_ingreso_paciente').innerHTML = datos[2];
					aparecerElemento("informacion inconsistente");
					
					desactivarelementoFormulario("cbafiliacion");
					desactivarelementoFormulario("txtnumdoc");
					} else {
					if (codpacientediagnostico == 0) {
						document.getElementById("datos_ingreso_paciente").innerHTML = datos[1];
						window.location.href = "adm_IngresarDemografico.jsp";
					} else {
						alert(datos[1]);
					}
				}
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&TipoDocumento=" + TipoDocumento+ "&NumeroDocumento=" + NumeroDocumento); 
	}
}

function habilitar(check,campo) {
	if(document.getElementById(check).checked){
		document.getElementById(campo).disabled=false;
		document.getElementById(campo).focus();
	}else{
		document.getElementById(campo).disabled=true;
	}
}

//Guarda los Datos 
function GuardarDatos() {
	var codpaciente = document.getElementById("paciente").value;
	var codusuario = document.getElementById("txtUsuario").value;
	var tipoinconsistencia = document.getElementById("TInconsistencia").value;
	var cobertura = document.getElementById("cobertura").value;
	var primerapellido = document.getElementById("papellido").value;
	var segundoapellido = document.getElementById("sapellido").value;
	var primernombre = document.getElementById("pnombre").value;
	var segundonombre = document.getElementById("snombre").value;
	var tipodocumento = document.getElementById("tidentificacion").value;
	var numerodocumento = document.getElementById("ndidentificacion").value;
	var fechanacimiento = document.getElementById("fechanacimiento").value;
	var observacion = document.getElementById("Observacion").value;
	

	//verifica si los datos a insertar no se encuentren vacios
	if ((tipoinconsistencia == "SELECCIONE") || (observacion == "")) {
		alert("Llene Todos Los Campos Requeridos.");
		
	} else {
		if(primerapellido==""){
			if(segundoapellido==""){
				if(primernombre==""){
					if(segundonombre==""){
						if(tipodocumento==""){
							if(numerodocumento==""){
								if(fechanacimiento==""){
									alert ("Debe seleccionar al menos una de las variables presuntamente incorrectas");
								}else{
									ajax = getXMLObject();
									ajax.open("POST", "ControlAnexoNo1", true);
									ajax.onreadystatechange = function() {
										if (ajax.readyState == 4) {
											alert("Ingreso Exitoso.");
											window.location.href = "AnexosTecnicos.jsp";
										}
								}
								ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
								ajax.send("valor=2&codpaciente=" + codpaciente + "&codusuario="
										+ codusuario + "&tipoinconsistencia=" + tipoinconsistencia + "&cobertura=" + cobertura
										+ "&primerapellido=" + primerapellido + "&segundoapellido=" + segundoapellido
										+ "&primernombre=" +primernombre + "&segundonombre=" + segundonombre
										+ "&tipodocumento=" + tipodocumento + "&numerodocumento=" + numerodocumento
										+ "&fechanacimiento=" + fechanacimiento + "&observacion=" + observacion);
								}
							}else{
								ajax = getXMLObject();
								ajax.open("POST", "ControlAnexoNo1", true);
								ajax.onreadystatechange = function() {
									if (ajax.readyState == 4) {
										alert("Ingreso Exitoso.");
										window.location.href = "AnexosTecnicos.jsp";
									}
							}
							ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
							ajax.send("valor=2&codpaciente=" + codpaciente + "&codusuario="
									+ codusuario + "&tipoinconsistencia=" + tipoinconsistencia + "&cobertura=" + cobertura
									+ "&primerapellido=" + primerapellido + "&segundoapellido=" + segundoapellido
									+ "&primernombre=" +primernombre + "&segundonombre=" + segundonombre
									+ "&tipodocumento=" + tipodocumento + "&numerodocumento=" + numerodocumento
									+ "&fechanacimiento=" + fechanacimiento + "&observacion=" + observacion);
							}
						}else{
							ajax = getXMLObject();
							ajax.open("POST", "ControlAnexoNo1", true);
							ajax.onreadystatechange = function() {
								if (ajax.readyState == 4) {
									alert("Ingreso Exitoso.");
									window.location.href = "AnexosTecnicos.jsp";
								}
						}
						ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
						ajax.send("valor=2&codpaciente=" + codpaciente + "&codusuario="
								+ codusuario + "&tipoinconsistencia=" + tipoinconsistencia + "&cobertura=" + cobertura
								+ "&primerapellido=" + primerapellido + "&segundoapellido=" + segundoapellido
								+ "&primernombre=" +primernombre + "&segundonombre=" + segundonombre
								+ "&tipodocumento=" + tipodocumento + "&numerodocumento=" + numerodocumento
								+ "&fechanacimiento=" + fechanacimiento + "&observacion=" + observacion);
						}
					}else{
						ajax = getXMLObject();
						ajax.open("POST", "ControlAnexoNo1", true);
						ajax.onreadystatechange = function() {
							if (ajax.readyState == 4) {
								alert("Ingreso Exitoso.");
								window.location.href = "AnexosTecnicos.jsp";
							}
					}
					ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=2&codpaciente=" + codpaciente + "&codusuario="
							+ codusuario + "&tipoinconsistencia=" + tipoinconsistencia + "&cobertura=" + cobertura
							+ "&primerapellido=" + primerapellido + "&segundoapellido=" + segundoapellido
							+ "&primernombre=" +primernombre + "&segundonombre=" + segundonombre
							+ "&tipodocumento=" + tipodocumento + "&numerodocumento=" + numerodocumento
							+ "&fechanacimiento=" + fechanacimiento + "&observacion=" + observacion);
					}
				}else{
					ajax = getXMLObject();
					ajax.open("POST", "ControlAnexoNo1", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
							alert("Ingreso Exitoso.");
							window.location.href = "AnexosTecnicos.jsp";
						}
				}
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=2&codpaciente=" + codpaciente + "&codusuario="
						+ codusuario + "&tipoinconsistencia=" + tipoinconsistencia + "&cobertura=" + cobertura
						+ "&primerapellido=" + primerapellido + "&segundoapellido=" + segundoapellido
						+ "&primernombre=" +primernombre + "&segundonombre=" + segundonombre
						+ "&tipodocumento=" + tipodocumento + "&numerodocumento=" + numerodocumento
						+ "&fechanacimiento=" + fechanacimiento + "&observacion=" + observacion);
				}
			}else{
				ajax = getXMLObject();
				ajax.open("POST", "ControlAnexoNo1", true);
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						alert("Ingreso Exitoso.");
						window.location.href = "AnexosTecnicos.jsp";
					}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=2&codpaciente=" + codpaciente + "&codusuario="
					+ codusuario + "&tipoinconsistencia=" + tipoinconsistencia + "&cobertura=" + cobertura
					+ "&primerapellido=" + primerapellido + "&segundoapellido=" + segundoapellido
					+ "&primernombre=" +primernombre + "&segundonombre=" + segundonombre
					+ "&tipodocumento=" + tipodocumento + "&numerodocumento=" + numerodocumento
					+ "&fechanacimiento=" + fechanacimiento + "&observacion=" + observacion);
			}
		}else{
			ajax = getXMLObject();
			ajax.open("POST", "ControlAnexoNo1", true);
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					alert("Ingreso Exitoso.");
					window.location.href = "AnexosTecnicos.jsp";
				}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2&codpaciente=" + codpaciente + "&codusuario="
				+ codusuario + "&tipoinconsistencia=" + tipoinconsistencia + "&cobertura=" + cobertura
				+ "&primerapellido=" + primerapellido + "&segundoapellido=" + segundoapellido
				+ "&primernombre=" +primernombre + "&segundonombre=" + segundonombre
				+ "&tipodocumento=" + tipodocumento + "&numerodocumento=" + numerodocumento
				+ "&fechanacimiento=" + fechanacimiento + "&observacion=" + observacion);
		}
		}
}

//Verifica si el paciente se encuentra en la base de datos, de los contrario
//lo envia IngresarDemografico.jsp para ingresar los datos del paciente
function consultar() {
	var TipoDocumento = document.getElementById("cbafiliacion").value;
	var NumeroDocumento = document.getElementById("txtnumdoc").value;
	
	if (NumeroDocumento == "") {
		alert("Escriba Numero Documento.");
		document.getElementById('txtnumdoc').focus();
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlAnexoNo1", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById('datos').innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&TipoDocumento=" + TipoDocumento+ "&NumeroDocumento=" + NumeroDocumento); 
	}
}

function Reporte(CodAnexo){
	 pagina="AnexoNo1Reporte.jsp?CodAnexo="+CodAnexo;				
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
}

//muestra el td con la identificacion a mostrar
function aparecerElemento(id) {
	document.getElementById(id).style.display = "inline";
}

//desactiva el td con la identificacion
function desactivarelementoFormulario(idCombo) {
	document.getElementById(idCombo).disabled = true;
}