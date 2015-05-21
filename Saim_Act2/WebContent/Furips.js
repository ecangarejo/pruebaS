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
var sw=0;
var sw1=0;
var sw2=0;
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
		ajax.open("POST", "ControlFurips", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var datos = ajax.responseText.split("|");
				codpacientediagnostico = datos[1];
				if (datos[0] == 1) {
					document.getElementById('datos_ingreso_paciente').innerHTML = datos[2];
					aparecerElemento("datos_victima");
					aparecerElemento("datos_sitio");
					
					desactivarelementoFormulario("cbafiliacion");
					desactivarelementoFormulario("txtnumdoc");
					desactivarelementoFormulario("btnBuscarPac");
					} else {
					if (codpacientediagnostico == 0) {
						document.getElementById("datos_ingreso_paciente").innerHTML = datos[2];
						//window.location.href = "adm_IngresarDemografico.jsp";
					} 
				/*	else {
						alert(datos[1]);
					}*/
				}
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&TipoDocumento=" + TipoDocumento+ "&NumeroDocumento=" + NumeroDocumento); 
	}
}

//Genera el reporte de los accidentes de transito
function Reporte(CodFrips){
		 pagina="Furips_Reporte.jsp?CodFrips="+CodFrips;				
		 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		 window.open(pagina,"NUEVA",opciones);
}

//Abre una ventana donde se van a realizar las modificaciones
function Editar(CodFrips){
	pagina="Anexos_FuripsModificar.jsp?CodFrips="+CodFrips;
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=yes, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina,"Modificacion_Furips",opciones);
}

//Genera el reporte de los otros eventos
function ReporteOtro(CodFrips){
	 pagina="Furips_ReporteOtro.jsp?CodFrips="+CodFrips;				
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
}

//Muestra datos de los registros ingresados en la base de datos
function Furips(){
	var fec_inicial = document.getElementById("desde1").value;
	var fec_final = document.getElementById("hasta1").value;

	if ((fec_inicial == "")||(fec_final == "")) {
		alert("Seleccione una rango de fechas.");
	} else {
		ajax=getXMLObject();
		ajax.open("POST","ControlFurips",true); 
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
			document.getElementById('datos').innerHTML = ajax.responseText;
			}
	    }
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=19&fec_inicial="+fec_inicial+"&fec_final="+fec_final); 
	}
}

//Verifica si el propietario se encuentra en la base de datos, de los contrario
//se habilita datos_ingreso_propietario para ingresar los datos del propietario
function propietarioActivo() {
	var TipDoc = document.getElementById("tipdoc").value;
	var NumDoc = document.getElementById("txtnd").value;

	if (NumDoc == "") {
		alert("Escriba Numero Documento.");
		document.getElementById('txtnd').focus();
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlFurips", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var datos = ajax.responseText.split("|");
				codpacientediagnostico = datos[1];
				if (datos[0] == 1) {
					document.getElementById('datos_ingreso_propietario').innerHTML = datos[2];
					
					aparecerElemento("ced_conductor");	
					
					desactivarelementoFormulario("ced_propietario");
					sw=1;
				} else {
					if (codpacientediagnostico == 0) {
						document.getElementById("datos_ingreso_propietario").innerHTML = datos[1];
						
						desactivarelementoFormulario("ced_propietario");
						
						aparecerElemento("datos_propietariovehiculo");
					} else {
						alert(datos[1]);
					}
				}
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=5&TipDoc=" + TipDoc+ "&NumDoc=" + NumDoc); 
	}
}

//Verifica si el conductor se encuentra en la base de datos, de los contrario
//se habilita datos_ingreso_conductor para ingresar los datos del conductor
function ConductorActivo() {
	var tdconductor = document.getElementById("tdconductor").value;
	var txtconductor = document.getElementById("txtconductor").value;

	if (txtconductor == "") {
		alert("Escriba Numero Documento.");
		document.getElementById('txtconductor').focus();
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlFurips", true); 
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var datos = ajax.responseText.split("|");
				codpacientediagnostico = datos[1];
				if (datos[0] == 1) {
					document.getElementById('datos_ingreso_conductor').innerHTML = datos[2];
					desactivarelementoFormulario("ced_conductor");
					aparecerElemento("guardar");
					sw1=1;
				} else {
					if (codpacientediagnostico == 0) {
						document.getElementById("datos_ingreso_conductor").innerHTML = datos[1];
						
						desactivarelementoFormulario("ced_conductor");
						
						aparecerElemento("datos_conductorvehiculo");
						aparecerElemento("guardar");
					} else {
						alert(datos[1]);
					}
				}
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=8&tdconductor=" + tdconductor+ "&txtconductor=" + txtconductor); // Posting txtname
	}
}

//Verifica si el medico se encuentra en la base de datos, de los contrario
//se habilita datos_ingreso_medico para ingresar los datos del medico
function MedicoActivo() {
	var tdmedico = document.getElementById("tipmedico").value;
	var txtmedico = document.getElementById("txtmedico").value;

	if (txtmedico == "") {
		alert("Escriba Numero Documento.");
		document.getElementById('txtmedico').focus();
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlFurips", true); 
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var datos = ajax.responseText.split("|");
				codpacientediagnostico = datos[1];
				if (datos[0] == 1) {
					document.getElementById('datos_ingreso_medico').innerHTML = datos[2];
			
					desactivarelementoFormulario("ced_medico");
					sw2=1;
				} else {
					if (codpacientediagnostico == 0) {
						document.getElementById("datos_ingreso_medico").innerHTML = datos[1];
						
						desactivarelementoFormulario("ced_medico");
						
						aparecerElemento("medico");
					} else {
						alert(datos[1]);
					}
				}
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=10&tdmedico=" + tdmedico+ "&txtmedico=" + txtmedico); // Posting txtname
	}
}

//muestra el td con la identificacion a mostrar
function aparecerElemento(id) {
	document.getElementById(id).style.display = "inline";
}

//desactiva el td con la identificacion
function desactivarelementoFormulario(idCombo) {
	document.getElementById(idCombo).disabled = true;
}

//desactiva el td con la identificacion
function aparecerelementoFormulario(idCombo) {
	document.getElementById(idCombo).disabled = false;
}

// Guarda los Datos del sitio donde ocurrio el evento
function GuardarDatos() {
	var fec_radicado = document.getElementById("fec_radicado").value;
	var num_radicado = document.getElementById("radicado").value;
	var ant_radicado = document.getElementById("antradicado").value;
	var factura = document.getElementById("factura").value;
	var tipo_documento = document.getElementById("cbafiliacion").value;
	var num_documento = document.getElementById("txtnumdoc").value;
	var evento = document.getElementById("eventonaturaleza").value;
	var dir_ocurrencia = document.getElementById("DirOcurrencia").value;
	var fec_evento = document.getElementById("fec_evento").value;
	var hora = document.getElementById("hora").value;
	var dpto_evento = document.getElementById("cbdep").value;
	var mun_evento = document.getElementById("cbmun").value;
	var zona = document.getElementById("zona").value;
	var descripcion = document.getElementById("Descripcion").value;
	var usuario = document.getElementById("txtUsuario").value;
	var admision = document.getElementById("admision").value;

	// Radio Condicion para capturar la condicion del accidentado ya sea Conductor,Peaton,Ciclista u Ocupante
	var condicion = "";
	var radiocondicion = document.getElementsByName("condicion");
	for ( var x = 0; x < radiocondicion.length; x++) {
		if (radiocondicion[x].checked) {
			condicion = radiocondicion[x].id;
		}
	}

	//verifica si los datos a insertar no se encuentren vacios
	if ((tipo_documento == "") || (num_documento == "")|| (condicion == "") || (evento == "Seleccione")|| (dir_ocurrencia == "") 
			|| (fec_evento == "")|| (hora == "Seleccione")||(zona == "Seleccione")|| (descripcion == "")||(dpto_evento == "SELECCIONE")|| (mun_evento == "Seleccione")) {
		alert("Llene Todos Los Campos Requeridos.");
		
	} else {
			if (num_radicado==""){
				num_radicado= "t"+num_documento;
			}
			ajax = getXMLObject();
			ajax.open("POST", "ControlFurips", true);
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					alert("Ingreso Exitoso.");
				
				// condicional para verificar si es un accidente de transito muestre los demas campos a llenar
				// datos del vehiculo,propietario y conductor
				if (evento == "1") {
					aparecerElemento("datos_vehiculo");
					aparecerElemento("ced_propietario");
					aparecerElemento("ced_conductor");
					
					oculta('datos_sitio');
					oculta('datos_victima');					
				}else{
					aparecerElemento("ver_remision");
					aparecerElemento("ver_amparo");
					aparecerElemento("certificacion_medica");
					aparecerElemento("ced_medico");
					aparecerElemento("amparo_reclama");
					
					oculta('datos_sitio');
					oculta('datos_victima');
				}
				document.getElementById('codigo_furips').innerHTML = ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&fec_radicado=" + fec_radicado + "&num_radicado="
				+ num_radicado + "&ant_radicado=" + ant_radicado + "&factura="
				+ factura + "&tipo_documento=" + tipo_documento
				+ "&num_documento=" + num_documento + "&condicion=" + condicion
				+ "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia
				+ "&fec_evento=" + fec_evento + "&hora=" + hora
				+ "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento
				+ "&zona=" + zona + "&descripcion=" + descripcion+ "&usuario=" + usuario+ "&admision=" + admision);
	}
}

// Guarda los Datos del accidente de transito
function GuardarDatosAccidente() {
	var furips = document.getElementById("furips").value;
	var radicado = document.getElementById("radicado").value;
	var num_documento = document.getElementById("txtnumdoc").value;
	var aseguramiento = document.getElementById("aseguramiento").value;
	var marca = document.getElementById("marca").value;
	var placa = document.getElementById("placa").value;
	var servicio = document.getElementById("servicio").value;
	var CodAseguradora = document.getElementById("CodAseguradora").value;
	var Numpoliza = document.getElementById("Numpoliza").value;
	var desde = document.getElementById("desde").value;
	var hasta = document.getElementById("hasta").value;
	//ced propietario
	var propietario = document.getElementById("txtnd").value;
	//datos propietario
	var tdpropietario = document.getElementById("tipodocumento").value;
	var numpropietario = document.getElementById("NumDocumento").value;
	var PrimerApellido = document.getElementById("PrimerApellido").value;
	var SegundoApellido = document.getElementById("SegundoApellido").value;
	var PrimerNombre = document.getElementById("PrimerNombre").value;
	var SegundoNombre = document.getElementById("SegundoNombre").value;
	var DirPropietario = document.getElementById("DirPropietario").value;
	var DptoPropietario = document.getElementById("dpto").value;
	var MunPropietario = document.getElementById("municipio").value;
	var TelPropietario = document.getElementById("TelPropietario").value;
	//ced conductor
	var conductor =document.getElementById("txtconductor").value;
	//datos conductor
	var tdConductor = document.getElementById("tdConductor").value;
	var NDconductor = document.getElementById("NDconductor").value;
	var PAConductor = document.getElementById("PAConductor").value;
	var SAConductor = document.getElementById("SAConductor").value;
	var PNConductor = document.getElementById("PNConductor").value;
	var SNConductor = document.getElementById("SNConductor").value;
	var DirConductor = document.getElementById("DirConductor").value;
	var TelConductor = document.getElementById("TelConductor").value;
	var dp = document.getElementById("dp").value;
	var mun = document.getElementById("mun").value;
	// Radio Autoridad, captura si hubo o no intervencion de la autoridad
	var Autoridad = "";
	var radioAutoridad = document.getElementsByName("Autoridad");
	for ( var x = 0; x < radioAutoridad.length; x++) {
		if (radioAutoridad[x].checked) {
			Autoridad = radioAutoridad[x].id;
		}
	}

	// Radio Excedente, captura si hubo o no cobro de excedente de poliza
	var Excedente = "";
	var radioExcedente = document.getElementsByName("ExcPoliza");
	for ( var y = 0; y < radioExcedente.length; y++) {
		if (radioExcedente[y].checked) {
			Excedente = radioExcedente[y].id;
		}
	}
	
	//verifica que los datos a ingresar no se encuentren vacios
	if ((num_documento == "")|| (aseguramiento == "SELECCIONE")  ) {
		alert("Llene Todos Los Campos Requeridos.");
	} else{
		/*if (radicado=="") {
			radicado="t"+num_documento;
		}*/
		
		if((aseguramiento=="Vehiculo Fantasma")||(aseguramiento=="Vehiculo Fuga")){
			if((Autoridad == "")||(Excedente == "")){
				alert("Por favor llene los campos queridos.");
			}else{
			 //ingresa los datos en la tabla furips_datosaccidente
					ajax = getXMLObject();
					ajax.open("POST", "ControlFurips", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
							alert("Ingreso Exitoso.");
								
							aparecerElemento("ver_remision");
							aparecerElemento("ver_amparo");
							aparecerElemento("certificacion_medica");
							aparecerElemento("ced_medico");
							aparecerElemento("amparo_reclama");
							
							
							oculta('datos_vehiculo');
							oculta('ced_propietario');
							oculta('datos_ingreso_propietario');
							oculta('datos_propietariovehiculo');
							oculta('ced_conductor');
							oculta('datos_ingreso_conductor');
							oculta('datos_conductorvehiculo');
							oculta('guardar');
						}
					}
					ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=6&furips=" + furips + "&aseguramiento="
						+ aseguramiento + "&marca=" + marca + "&placa=" + placa
						+ "&servicio=" + '' + "&CodAseguradora=" + ''
						+ "&NumPoliza=" + Numpoliza + "&Autoridad=" + Autoridad
						+ "&desde=" + desde + "&hasta=" + hasta + "&Excedente="
						+ Excedente + "&propietario=" + 0
						+ "&conductor=" + 0 );
					
			}	
		}else{
			if ((sw==1)&& (sw1==1)){ //ingresa los datos en la tabla furips_datosaccidente
			ajax = getXMLObject();
			ajax.open("POST", "ControlFurips", true);
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					alert("Ingreso Exitoso.");
						
					aparecerElemento("ver_remision");
					aparecerElemento("ver_amparo");
					aparecerElemento("certificacion_medica");
					aparecerElemento("ced_medico");
					aparecerElemento("amparo_reclama");
					
					
					oculta('datos_vehiculo');
					oculta('ced_propietario');
					oculta('datos_ingreso_propietario');
					oculta('datos_propietariovehiculo');
					oculta('ced_conductor');
					oculta('datos_ingreso_conductor');
					oculta('datos_conductorvehiculo');
					oculta('guardar');
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=6&furips=" + furips + "&aseguramiento="
				+ aseguramiento + "&marca=" + marca + "&placa=" + placa
				+ "&servicio=" + servicio + "&CodAseguradora=" + CodAseguradora
				+ "&NumPoliza=" + Numpoliza + "&Autoridad=" + Autoridad
				+ "&desde=" + desde + "&hasta=" + hasta + "&Excedente="
				+ Excedente + "&propietario=" + propietario
				+ "&conductor=" + conductor );
			
		}else if ((sw==0)&& (sw1==1)) {//ingresa los datos en la tabla de furips_datosaccidente y furips_datospropietario
			ajax = getXMLObject();
			ajax.open("POST", "ControlFurips", true);
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					alert("Ingreso Exitoso.");
					
					aparecerElemento("ver_remision");
					aparecerElemento("ver_amparo");
					aparecerElemento("certificacion_medica");
					aparecerElemento("ced_medico");
					aparecerElemento("amparo_reclama");
					
					
					oculta('datos_vehiculo');
					oculta('ced_propietario');
					oculta('datos_ingreso_propietario');
					oculta('datos_propietariovehiculo');
					oculta('ced_conductor');
					oculta('datos_ingreso_conductor');
					oculta('datos_conductorvehiculo');
					oculta('guardar');
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=7&furips=" + furips + "&aseguramiento="
				+ aseguramiento + "&marca=" + marca + "&placa=" + placa
				+ "&servicio=" + servicio + "&CodAseguradora=" + CodAseguradora
				+ "&NumPoliza=" + Numpoliza + "&Autoridad=" + Autoridad
				+ "&desde=" + desde + "&hasta=" + hasta + "&Excedente="
				+ Excedente + "&tdpropietario=" + tdpropietario
				+ "&numpropietario=" + numpropietario + "&PrimerApellido=" + PrimerApellido
				+ "&SegundoApellido=" + SegundoApellido + "&PrimerNombre=" + PrimerNombre
				+ "&SegundoNombre=" + SegundoNombre + "&DirPropietario=" + DirPropietario
				+ "&DptoPropietario=" + DptoPropietario + "&DptoPropietario=" + DptoPropietario
				+ "&MunPropietario=" + MunPropietario + "&TelPropietario=" + TelPropietario
				+ "&conductor=" + conductor );
			
		}else if ((sw==0)&& (sw1==0)) { //ingresa los datos en la tabla de furips_datosaccidente, furips_datospropietario y furips_datosconductor
			ajax = getXMLObject();
			ajax.open("POST", "ControlFurips", true);
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					alert("Ingreso Exitoso.");
					
					aparecerElemento("ver_remision");
					aparecerElemento("ver_amparo");
					aparecerElemento("certificacion_medica");
					aparecerElemento("ced_medico");
					aparecerElemento("amparo_reclama");
					
					oculta('datos_vehiculo');
					oculta('ced_propietario');
					oculta('datos_ingreso_propietario');
					oculta('datos_propietariovehiculo');
					oculta('ced_conductor');
					oculta('datos_ingreso_conductor');
					oculta('datos_conductorvehiculo');
					oculta('guardar');
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=4&furips=" + furips + "&aseguramiento="
				+ aseguramiento + "&marca=" + marca + "&placa=" + placa
				+ "&servicio=" + servicio + "&CodAseguradora=" + CodAseguradora
				+ "&NumPoliza=" + Numpoliza + "&Autoridad=" + Autoridad
				+ "&desde=" + desde + "&hasta=" + hasta + "&Excedente="
				+ Excedente + "&tdpropietario=" + tdpropietario
				+ "&numpropietario=" + numpropietario + "&PrimerApellido=" + PrimerApellido
				+ "&SegundoApellido=" + SegundoApellido + "&PrimerNombre=" + PrimerNombre
				+ "&SegundoNombre=" + SegundoNombre + "&DirPropietario=" + DirPropietario
				+ "&DptoPropietario=" + DptoPropietario + "&DptoPropietario=" + DptoPropietario
				+ "&MunPropietario=" + MunPropietario + "&TelPropietario=" + TelPropietario
				+ "&tdConductor=" + tdConductor + "&NDconductor=" + NDconductor
				+ "&PAConductor=" + PAConductor + "&SAConductor=" + SAConductor
				+ "&PNConductor=" + PNConductor + "&SNConductor=" + SNConductor
				+ "&DirConductor=" + DirConductor + "&TelConductor=" + TelConductor
				+ "&dp=" + dp + "&mun=" + mun );
			
		}else if ((sw==1)&& (sw1==0)) {//ingresa los datos en la tabla de furips_datosaccidente y furips_datosconductor
			ajax = getXMLObject();
			ajax.open("POST", "ControlFurips", true);
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					alert("Ingreso Exitoso.");
					
					aparecerElemento("ver_remision");
					aparecerElemento("ver_amparo");
					aparecerElemento("certificacion_medica");
					aparecerElemento("ced_medico");
					aparecerElemento("amparo_reclama");
					
					oculta('datos_vehiculo');
					oculta('ced_propietario');
					oculta('datos_ingreso_propietario');
					oculta('datos_propietariovehiculo');
					oculta('ced_conductor');
					oculta('datos_ingreso_conductor');
					oculta('datos_conductorvehiculo');
					oculta('guardar');
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=9&furips=" + furips + "&aseguramiento="
				+ aseguramiento + "&marca=" + marca + "&placa=" + placa
				+ "&servicio=" + servicio + "&CodAseguradora=" + CodAseguradora
				+ "&NumPoliza=" + Numpoliza + "&Autoridad=" + Autoridad
				+ "&desde=" + desde + "&hasta=" + hasta 
				+ "&Excedente="+ Excedente + "&propietario=" + propietario
				+ "&tdConductor=" + tdConductor + "&NDconductor=" + NDconductor
				+ "&PAConductor=" + PAConductor + "&SAConductor=" + SAConductor
				+ "&PNConductor=" + PNConductor + "&SNConductor=" + SNConductor
				+ "&DirConductor=" + DirConductor + "&TelConductor=" + TelConductor
				+ "&dp=" + dp + "&mun=" + mun );
		}
	}
}
}

//Guarda Otros Datos del accidente de transito
function GuardarDatosOtros() {
	var radicado = document.getElementById("furips").value;
	var num_documento = document.getElementById("txtnumdoc").value;
	//datos remision
	var fec_remision=document.getElementById("fec_remision").value;
	var hora_remision=document.getElementById("hora_remision").value;
	var PrestadorRemite=document.getElementById("PrestadorRemite").value;
	var CodInsRemite=document.getElementById("CodInsRemite").value;
	var ProfesionalRemite=document.getElementById("ProfesionalRemite").value;
	var CargoRemite=document.getElementById("CargoRemite").value;
	var fec_aceptacion=document.getElementById("fec_aceptacion").value;
	var hora_aceptacion=document.getElementById("hora_aceptacion").value;
	var PrestadorRecibe=document.getElementById("PrestadorRecibe").value;
	var CodInsRecibe=document.getElementById("CodInsRecibe").value;
	var ProfesionalRecibe=document.getElementById("ProfesionalRecibe").value;
	var CargoRecibe=document.getElementById("CargoRecibe").value;
	//datos amparo transporte
	var placatrans=document.getElementById("placatrans").value;
	var transdesde=document.getElementById("transdesde").value;
	var transhasta=document.getElementById("transhasta").value;
	var transporte=document.getElementById("transporte").value;	
	var lugarvictima=document.getElementById("lugarvictima").value;
	//datos certificacion medica
	var fec_Ingreso=document.getElementById("fec_Ingreso").value;
	var hora_ingreso=document.getElementById("hora_ingreso").value;
	var fec_Egreso=document.getElementById("fec_Egreso").value;
	var hora_egreso=document.getElementById("hora_egreso").value;
	var DiagPrincipalIngreso=document.getElementById("DiagPrincipalIngreso").value;
	var DiagPrincipalEgreso=document.getElementById("DiagPrincipalEgreso").value;
	var OtroDiagIngreso=document.getElementById("OtroDiagIngreso").value;
	var OtroDiagEgreso=document.getElementById("OtroDiagEgreso").value;
	var OtroDiagIngreso2=document.getElementById("OtroDiagIngreso2").value;
	var OtroDiagEgreso2=document.getElementById("OtroDiagEgreso2").value;
	//ced medico
	var tipmedico=document.getElementById("tipmedico").value;
	var txtmedico=document.getElementById("txtmedico").value;
	//datos medico
	var PAMedico=document.getElementById("PAMedico").value;
	var SAMedico=document.getElementById("SAMedico").value;
	var PNMedico=document.getElementById("PNMedico").value;
	var SNMedico=document.getElementById("SNMedico").value;
	var tdmedico=document.getElementById("tdmedico").value;
	var NDMedico=document.getElementById("NDMedico").value;
	var RegistroMedico=document.getElementById("RegistroMedico").value;
	//amparo que reclama
	var GastoMedicoFacturado=document.getElementById("GastoMedicoFacturado").value;
	var GastoMedicoFosyga=document.getElementById("GastoMedicoFosyga").value;
	var GastoTransFacturado=document.getElementById("GastoTransFacturado").value;
	var GastoTransFosyga=document.getElementById("GastoTransFosyga").value;
		
	// Radio Remision, captura si se realizo o no remision
	var Remision = "";
	var radioRemision = document.getElementsByName("radioremision");
	for ( var xy = 0; xy < radioRemision.length; xy++) {
		if (radioRemision[xy].checked) {
			Remision = radioRemision[xy].id;
		}
	}
	
	// Radio Amparo transporte, captura si hubo o no transporte de la victima
	var Transporte = "";
	var radioAmparo = document.getElementsByName("radioamparo");
	for ( var tt = 0; tt< radioAmparo.length; tt++) {
		if (radioAmparo[tt].checked) {
			Transporte = radioAmparo[tt].id;
		}
	}
	
	// Radio referencia, captura si hubo o no 
	var Referencia = "";
	var radioReferencia = document.getElementsByName("referencia");
	for ( var t = 0; t< radioReferencia.length; t++) {
		if (radioReferencia[t].checked) {
			Referencia = radioReferencia[t].id;
		}
	}

	if(Remision=="Si"){
		if ((fec_remision == "")|| (hora_remision == "") || (PrestadorRemite == "")
				|| (CodInsRemite == "") || (ProfesionalRemite == "")|| (CargoRemite == "") || (fec_aceptacion == "")
				|| (hora_aceptacion == "") || (PrestadorRecibe == "")||(CodInsRecibe== "")||(ProfesionalRecibe== "")
				|| (CargoRecibe == "")||(txtmedico == "")||(fec_Ingreso == "")||(hora_ingreso == "")||(DiagPrincipalIngreso == "")
				|| (GastoMedicoFacturado == "")||(GastoTransFacturado == "")
		) {
			alert("Llene Todos Los Campos Requeridos.");
		}else{
			if((Transporte=="Si")){
				if((sw2==0)){
					ajax = getXMLObject();
					ajax.open("POST", "ControlFurips", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
						alert("Ingreso Exitoso.");
						window.location.href = "Anexos_furips.jsp";
						}
					}
					ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=12&Remision=" + Remision + "&Transporte="+ Transporte +"&num_documento="+num_documento
							+ "&furips=" + radicado + "&referencia=" + Referencia
							+ "&tdmedico=" + tdmedico + "&NDMedico=" + NDMedico
							+ "&RegistroMedico=" + RegistroMedico + "&PAMedico=" + PAMedico
							+ "&PNMedico=" + PNMedico + "&SAMedico=" + SAMedico
							+ "&SNMedico=" + SNMedico
							+ "&fec_remision=" + fec_remision + "&hora_remision=" + hora_remision
							+ "&PrestadorRemite=" + PrestadorRemite + "&CodInsRemite=" + CodInsRemite
							+ "&ProfesionalRemite=" + ProfesionalRemite + "&CargoRemite=" + CargoRemite 
							+ "&fec_aceptacion=" + fec_aceptacion + "&hora_aceptacion=" + hora_aceptacion
							+ "&PrestadorRecibe=" + PrestadorRecibe + "&CodInsRecibe=" + CodInsRecibe
							+ "&ProfesionalRecibe=" + ProfesionalRecibe + "&CargoRecibe=" + CargoRecibe
							+ "&placatrans=" + placatrans + "&transdesde=" + transdesde	
							+ "&transhasta=" + transhasta + "&transporte=" + transporte
							+ "&lugarvictima=" + lugarvictima 
							+ "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso
							+ "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso
							+ "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso
							+ "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso	
							+ "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2
					    	+ "&GastoMedicoFacturado=" + GastoMedicoFacturado + "&GastoTransFacturado=" + GastoTransFacturado	
							+ "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFosyga=" + GastoTransFosyga);
					}else if ((sw2==1)) {
						ajax = getXMLObject();
						ajax.open("POST", "ControlFurips", true);
						ajax.onreadystatechange = function() {
							if (ajax.readyState == 4) {
								alert("Ingreso Exitoso.");
								window.location.href = "Anexos_Anexos_furips.jsp";
							}
						}
						ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
						ajax.send("valor=11&Remision=" + Remision + "&Transporte="+ Transporte+"&num_documento="+num_documento
							+ "&furips=" + radicado + "&referencia=" + Referencia
							+ "&tipmedico=" + tipmedico + "&txtmedico=" + txtmedico
							+ "&fec_remision=" + fec_remision + "&hora_remision=" + hora_remision
							+ "&PrestadorRemite=" + PrestadorRemite + "&CodInsRemite=" + CodInsRemite
							+ "&ProfesionalRemite=" + ProfesionalRemite + "&CargoRemite=" + CargoRemite 
							+ "&fec_aceptacion=" + fec_aceptacion + "&hora_aceptacion=" + hora_aceptacion
							+ "&PrestadorRecibe=" + PrestadorRecibe + "&CodInsRecibe=" + CodInsRecibe
							+ "&ProfesionalRecibe=" + ProfesionalRecibe + "&CargoRecibe=" + CargoRecibe
							+ "&placatrans=" + placatrans + "&transdesde=" + transdesde	
							+ "&transhasta=" + transhasta + "&transporte=" + transporte
							+ "&lugarvictima=" + lugarvictima
							+ "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso
							+ "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso
							+ "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso
							+ "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso	
							+ "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2
					    	+ "&GastoMedicoFacturado=" + GastoMedicoFacturado + "&GastoTransFacturado=" + GastoTransFacturado	
							+ "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFosyga=" + GastoTransFosyga);
					}
			}else if ((Transporte=="No")) {
				if((sw2==0)){
					ajax = getXMLObject();
					ajax.open("POST", "ControlFurips", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
						alert("Ingreso Exitoso.");
						window.location.href = "Anexos_furips.jsp";
					}
					}
					ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=13&Remision=" + Remision + "&Transporte="+ Transporte+"&num_documento="+num_documento
							+ "&furips=" + radicado + "&referencia=" + Referencia
							+ "&tdmedico=" + tdmedico + "&NDMedico=" + NDMedico
							+ "&RegistroMedico=" + RegistroMedico + "&PAMedico=" + PAMedico
							+ "&PNMedico=" + PNMedico + "&SAMedico=" + SAMedico
							+ "&SNMedico=" + SNMedico
							+ "&fec_remision=" + fec_remision + "&hora_remision=" + hora_remision
							+ "&PrestadorRemite=" + PrestadorRemite + "&CodInsRemite=" + CodInsRemite
							+ "&ProfesionalRemite=" + ProfesionalRemite + "&CargoRemite=" + CargoRemite 
							+ "&fec_aceptacion=" + fec_aceptacion + "&hora_aceptacion=" + hora_aceptacion
							+ "&PrestadorRecibe=" + PrestadorRecibe + "&CodInsRecibe=" + CodInsRecibe
							+ "&ProfesionalRecibe=" + ProfesionalRecibe + "&CargoRecibe=" + CargoRecibe
							+ "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso
							+ "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso
							+ "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso
							+ "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso	
							+ "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2
					    	+ "&GastoMedicoFacturado=" + GastoMedicoFacturado + "&GastoTransFacturado=" + GastoTransFacturado	
							+ "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFosyga=" + GastoTransFosyga);
					}else if ((sw2==1)) {
						ajax = getXMLObject();
						ajax.open("POST", "ControlFurips", true);
						ajax.onreadystatechange = function() {
							if (ajax.readyState == 4) {
								alert("Ingreso Exitoso.");
								window.location.href = "Anexos_furips.jsp";
							}
						}
						ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
						ajax.send("valor=14&Remision=" + Remision + "&Transporte="+ Transporte+"&num_documento="+num_documento
							+ "&furips=" + radicado + "&referencia=" + Referencia
							+ "&tipmedico=" + tipmedico + "&txtmedico=" + txtmedico
							+ "&fec_remision=" + fec_remision + "&hora_remision=" + hora_remision
							+ "&PrestadorRemite=" + PrestadorRemite + "&CodInsRemite=" + CodInsRemite
							+ "&ProfesionalRemite=" + ProfesionalRemite + "&CargoRemite=" + CargoRemite 
							+ "&fec_aceptacion=" + fec_aceptacion + "&hora_aceptacion=" + hora_aceptacion
							+ "&PrestadorRecibe=" + PrestadorRecibe + "&CodInsRecibe=" + CodInsRecibe
							+ "&ProfesionalRecibe=" + ProfesionalRecibe + "&CargoRecibe=" + CargoRecibe
							+ "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso
							+ "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso
							+ "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso
							+ "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso	
							+ "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2
					    	+ "&GastoMedicoFacturado=" + GastoMedicoFacturado + "&GastoTransFacturado=" + GastoTransFacturado	
							+ "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFosyga=" + GastoTransFosyga);
					}
			}
		}
	}else if (Remision=="No"){
			if((Transporte=="Si")){
				if((sw2==0)){
					ajax = getXMLObject();
					ajax.open("POST", "ControlFurips", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
						alert("Ingreso Exitoso.");
						window.location.href = "Anexos_furips.jsp";
					}
					}
					ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=15&Remision=" + Remision + "&Transporte="+ Transporte+"&num_documento="+num_documento
							+ "&furips=" + radicado + "&referencia=" + Referencia
							+ "&tdmedico=" + tdmedico + "&NDMedico=" + NDMedico
							+ "&RegistroMedico=" + RegistroMedico + "&PAMedico=" + PAMedico
							+ "&PNMedico=" + PNMedico + "&SAMedico=" + SAMedico
							+ "&SNMedico=" + SNMedico
							+ "&placatrans=" + placatrans + "&transdesde=" + transdesde	
							+ "&transhasta=" + transhasta + "&transporte=" + transporte
							+ "&lugarvictima=" + lugarvictima 
							+ "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso
							+ "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso
							+ "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso
							+ "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso	
							+ "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2
					    	+ "&GastoMedicoFacturado=" + GastoMedicoFacturado + "&GastoTransFacturado=" + GastoTransFacturado	
							+ "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFosyga=" + GastoTransFosyga);
					}else if ((sw2==1)) {
						ajax = getXMLObject();
						ajax.open("POST", "ControlFurips", true);
						ajax.onreadystatechange = function() {
							if (ajax.readyState == 4) {
								alert("Ingreso Exitoso.");
								window.location.href = "Anexos_furips.jsp";
							}
						}
						ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
						ajax.send("valor=16&Remision=" + Remision + "&Transporte="+ Transporte+"&num_documento="+num_documento
							+ "&furips=" + radicado + "&referencia=" + Referencia
							+ "&tipmedico=" + tipmedico + "&txtmedico=" + txtmedico
							+ "&placatrans=" + placatrans + "&transdesde=" + transdesde	
							+ "&transhasta=" + transhasta + "&transporte=" + transporte
							+ "&lugarvictima=" + lugarvictima
							+ "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso
							+ "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso
							+ "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso
							+ "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso	
							+ "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2
					    	+ "&GastoMedicoFacturado=" + GastoMedicoFacturado + "&GastoTransFacturado=" + GastoTransFacturado	
							+ "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFosyga=" + GastoTransFosyga);
					}
			}else if ((Transporte=="No")) {
				if((sw2==0)){
					ajax = getXMLObject();
					ajax.open("POST", "ControlFurips", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
						alert("Ingreso Exitoso.");
						window.location.href = "Anexos_furips.jsp";
					}
					}
					ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=17&Remision=" + Remision + "&Transporte="+ Transporte+"&num_documento="+num_documento
							+ "&furips=" + radicado + "&referencia=" + Referencia
							+ "&tdmedico=" + tdmedico + "&NDMedico=" + NDMedico
							+ "&RegistroMedico=" + RegistroMedico + "&PAMedico=" + PAMedico
							+ "&PNMedico=" + PNMedico + "&SAMedico=" + SAMedico
							+ "&SNMedico=" + SNMedico
							+ "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso
							+ "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso
							+ "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso
							+ "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso	
							+ "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2
					    	+ "&GastoMedicoFacturado=" + GastoMedicoFacturado + "&GastoTransFacturado=" + GastoTransFacturado	
							+ "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFosyga=" + GastoTransFosyga);
					}else if ((sw2==1)) {
						ajax = getXMLObject();
						ajax.open("POST", "ControlFurips", true);
						ajax.onreadystatechange = function() {
							if (ajax.readyState == 4) {
								alert("Ingreso Exitoso.");
								window.location.href = "Anexos_furips.jsp";
							}
						}
						ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
						ajax.send("valor=18&Remision=" + Remision + "&Transporte="+ Transporte+"&num_documento="+num_documento
							+ "&furips=" + radicado + "&referencia=" + Referencia
							+ "&tipmedico=" + tipmedico + "&txtmedico=" + txtmedico
							+ "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso
							+ "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso
							+ "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso
							+ "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso	
							+ "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2
					    	+ "&GastoMedicoFacturado=" + GastoMedicoFacturado + "&GastoTransFacturado=" + GastoTransFacturado	
							+ "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFosyga=" + GastoTransFosyga);
					}
			}
		}
	}

//muestra los datos a llenar de la remision.
function clicksiRemision(){
	aparecerElemento("remision");
}

//verifica cual es el estado de aseguramiento y cuales son los campos a llenar
function ajaxVal(){
	var aseguramiento = document.getElementById("aseguramiento").value;
	
	if (aseguramiento=="Asegurado"){
		document.getElementById("marca").disabled=false;
		document.getElementById("placa").disabled=false;
		document.getElementById("servicio").disabled=false;
		document.getElementById("CodAseguradora").disabled=false;
		document.getElementById("Numpoliza").disabled=false;
		document.getElementById("desde").disabled=false;
		document.getElementById("hasta").disabled=false;
		aparecerElemento("ced_propietario");
		aparecerElemento("ced_conductor");
		aparecerelementoFormulario("ced_propietario");
		
	}else if (aseguramiento=="No Asegurado") {
		 document.getElementById("marca").disabled=false;
		 document.getElementById("placa").disabled=false;
		 document.getElementById("servicio").disabled=false;
		 document.getElementById("CodAseguradora").disabled=true;
		 document.getElementById("Numpoliza").disabled=true;
		 document.getElementById("desde").disabled=true;
		 document.getElementById("hasta").disabled=true;
		 aparecerElemento("ced_propietario");
		 aparecerElemento("ced_conductor");
		 aparecerelementoFormulario("ced_propietario");
			
	 }else if (aseguramiento=="Vehiculo Fantasma") {
		  document.getElementById("marca").disabled=true;
		  document.getElementById("placa").disabled=true;
		  document.getElementById("servicio").disabled=true;
		  document.getElementById("CodAseguradora").disabled=true;
		  document.getElementById("Numpoliza").disabled=true;
		  document.getElementById("desde").disabled=true;
		  document.getElementById("hasta").disabled=true;
		  desactivarelementoFormulario("ced_propietario");
		  desactivarelementoFormulario("ced_conductor");
		  aparecerElemento("guardar");
		  
	   }else if (aseguramiento=="Poliza Falsa") {
		    document.getElementById("marca").disabled=false;
			document.getElementById("placa").disabled=false;
			document.getElementById("servicio").disabled=false;
			document.getElementById("CodAseguradora").disabled=false;
			document.getElementById("Numpoliza").disabled=false;
			document.getElementById("desde").disabled=false;
			document.getElementById("hasta").disabled=false;
			aparecerElemento("ced_propietario");
			aparecerElemento("ced_conductor");
			
	     }else if (aseguramiento=="Vehiculo Fuga") {
	    	  document.getElementById("marca").disabled=true;
			  document.getElementById("placa").disabled=false;
			  document.getElementById("servicio").disabled=false;
			  document.getElementById("CodAseguradora").disabled=true;
			  document.getElementById("Numpoliza").disabled=true;
			  document.getElementById("desde").disabled=true;
			  document.getElementById("hasta").disabled=true;
			  desactivarelementoFormulario("ced_propietario");
			  desactivarelementoFormulario("ced_conductor");
			  aparecerElemento("guardar");
		}
}

//oculta el td con el identificador
function oculta(id){
	var elDiv = document.getElementById(id); 
		elDiv.style.display='none';   
}

//muestra el td con el identificador
function muestra(id){
    var elDiv = document.getElementById(id); 
        elDiv.style.display='block';
}

window.onload = function(){
	   oculta('remision'); 
};

//oculta el td con el identificador
function oculta2(id){
	var elDiv = document.getElementById(id); 
		elDiv.style.display='none';      
}

//muestra el td con el identificador
function muestra2(id){
    var elDiv = document.getElementById(id); 
        elDiv.style.display='block';   
}

window.onload = function(){
	   oculta('amparo'); 
};

function AutoCompleta2(vble){
	var diag=document.getElementById("diagini").value;
	var diagegre=document.getElementById("diagegre").value;
	var Otrodiaging=document.getElementById("Otrodiaging").value;
	var Otrodiagegre=document.getElementById("Otrodiagegre").value;
	var Otrodiaging2=document.getElementById("Otrodiaging2").value;
	var Otrodiagegre2=document.getElementById("Otrodiagegre2").value;
		
		ajax=getXMLObject();
		ajax.open("POST","ControlFurips",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				if(vble==1){
					document.getElementById("DiagPrincipalIng").innerHTML=ajax.responseText;
				}else if (vble==2) {
					document.getElementById("DiagPrincipalEgr").innerHTML=ajax.responseText;
				}else if (vble==3) {
					document.getElementById("OtroDiagIng").innerHTML=ajax.responseText;
				}else if (vble==4) {
					document.getElementById("OtroDiagEgr").innerHTML=ajax.responseText;
				}else if (vble==5) {
					document.getElementById("OtroDiagIng2").innerHTML=ajax.responseText;
				}else if (vble==6) {
					document.getElementById("OtroDiagEgr2").innerHTML=ajax.responseText;
				}
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		if(vble==1){
			ajax.send("valor=28&diag="+diag+"&vble="+vble); //Posting txtname to Servle	
		}else if (vble==2) {
			ajax.send("valor=28&diag="+diagegre+"&vble="+vble); //Posting txtname to Servle	
		}else if (vble==3) {
			ajax.send("valor=28&diag="+Otrodiaging+"&vble="+vble); //Posting txtname to Servle	
		}else if (vble==4) {
			ajax.send("valor=28&diag="+Otrodiagegre+"&vble="+vble); //Posting txtname to Servle	
		}else if (vble==5) {
			ajax.send("valor=28&diag="+Otrodiaging2+"&vble="+vble); //Posting txtname to Servle	
		}else if (vble==6) {
			ajax.send("valor=28&diag="+Otrodiagegre2+"&vble="+vble); //Posting txtname to Servle	
		}
		
}

function Seleccion(cod,diag){
	document.getElementById("diagini").value=diag;
	document.getElementById("DiagPrincipalIngreso").value=cod;
	document.getElementById("DiagPrincipalIng").innerHTML="";	
}

function Seleccion2(cod,diag){
	document.getElementById("diagegre").value=diag;
	document.getElementById("DiagPrincipalEgreso").value=cod;
	document.getElementById("DiagPrincipalEgr").innerHTML="";	
}
function Seleccion3(cod,diag){
	document.getElementById("Otrodiaging").value=diag;
	document.getElementById("OtroDiagIngreso").value=cod;
	document.getElementById("OtroDiagIng").innerHTML="";	
}

function Seleccion4(cod,diag){
	document.getElementById("Otrodiagegre").value=diag;
	document.getElementById("OtroDiagEgreso").value=cod;
	document.getElementById("OtroDiagEgr").innerHTML="";	
}
function Seleccion5(cod,diag){
	document.getElementById("Otrodiaging2").value=diag;
	document.getElementById("OtroDiagIngreso2").value=cod;
	document.getElementById("OtroDiagIng2").innerHTML="";	
}

function Seleccion6(cod,diag){
	document.getElementById("Otrodiagegre2").value=diag;
	document.getElementById("OtroDiagEgreso2").value=cod;
	document.getElementById("OtroDiagEgr2").innerHTML="";	
}