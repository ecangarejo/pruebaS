contAreasFinalizadas = 0;
cantAreas = 0;
ocultarArea = false;
tipoAiepi="";
anio = "";
meses = "";
dias = "";
codAiepi = "";
codPaciente = "";
fechaActual = "";

var fondo = false; //para ventana crear/ver tratamiento 
var mensaje = false;

function ventObservaciones(){
    
	var codUsuario = document.getElementById('txtCodusuario').value;
	
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	mensaje.setAttribute('id','msg');
	fondo.setAttribute('id','fondo');
	document.getElementsByTagName('body')[0].appendChild(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	window.scroll (0,0) ; 
	document.body.style.overflow="hidden";
	
	var xmlhttp = getXMLObject();
	xmlhttp.onreadystatechange = function (){
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status==200){
				mensaje.innerHTML = xmlhttp.responseText;
				document.getElementById('oculto').focus();
				document.getElementById('observaciones').focus();
			}
		}
	};
	xmlhttp.open("POST","ControlConfiguracion",true);
	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlhttp.send("op=6&codPac="+codPaciente+"&codAiepi="+codAiepi+"&fecha="+fechaActual);
}

function getXMLObject(){	
	var xmlhttp=null;
	if (typeof (XMLHttpRequest) != 'undefined') {
		try {
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
		}
	} else {
		try {
			xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
		} catch (e) {
			xmlhttp = new ActiveXObject('Msxml2.XMLHTTP');
		}
	}
	return xmlhttp;
}

function verificarPaciente(){
	
	var contenedor = document.getElementById('Opcion');
	var btnFinalizar = document.createElement('input');
	btnFinalizar.type = "button"; 
	btnFinalizar.value = "Finalizar";
	btnFinalizar.setAttribute('id','btnFinalizar');
	btnFinalizar.attachEvent('onclick',finalizar);
	
	var codUsuario = document.getElementById('txtCodusuario').value;
	
	var tdoc = document.getElementById('cmbTipoDoc').value;
	var ndoc = document.getElementById('txtNumDocumento').value;
	if(tdoc=="Seleccione"||ndoc==""){
		alert("Datos incorrectos");
	}else{
		var xmlhttp = getXMLObject();
		xmlhttp.onreadystatechange = function (){
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status==200){
					var respuesta = xmlhttp.responseText;
					if(respuesta.length>0){
						var resp = respuesta.split("-");
						
						if(resp[0]=="na"){
							alert("Paciente no apto para el Aiepi");
							location.reload();
						}else{
							if(resp[0]=="ne"){
								alert("Paciente no existe");
								location.reload();
							}else{
								if(resp[0]=="AI"){
									alert("Paciente ya presenta estrategia AIEPI para la fecha");
									location.reload();
								}else{
									if(resp[0]!="ne" && resp[0]!="na"){
										tipoAiepi = resp[2];
										anio = resp[3];
										meses = resp[4];
										dias = resp[5];
										codAiepi = resp[6];
										codPaciente = resp[7];
										fechaActual = resp[8]+"-"+resp[9]+"-"+resp[10];
										
										document.getElementById('btnBuscarPac').disabled=true;
										if(tipoAiepi=="tipo_aiepi_2"){
											nt = document.createTextNode("AIEPI PARA NI\u00D1OS ENTRE 0 Y 2 MESES DE EDAD");
										}else{
											if(tipoAiepi=="tipo_aiepi_3"){
												nt = document.createTextNode("AIEPI PARA NI\u00D1OS ENTRE 2 MESES Y 5 A\u00D1OS DE EDAD");
											}else{
												if(tipoAiepi=="tipo_aiepi_1"){
													nt = document.createTextNode("AIEPI PARA NI\u00D1OS RECIEN NACIDOS");
												}
											}
											
										}
										
										var cont = document.getElementById('cabecera2');
										if(cont.hasChildNodes()){
											cont.removeChild(contenedor.lastChild);
											cont.appendChild(nt);
										}else{
											cont.appendChild(nt);
										}
										
										if (resp[1].length>0){
											//alert(resp[1]);
											var detAreas = resp[1].split("%");
											
											for (aa=0;aa<detAreas.length;aa++){							
												areas(detAreas[aa]);	
												cantAreas++;
											}
										}
										contenedor.appendChild(btnFinalizar);											
									}
								}
							}
						}
					}
					
				}
			}
		};
		xmlhttp.open("POST","ControlConfiguracion",true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send("op=1&tdoc="+tdoc+"&ndoc="+ndoc+"&codUsuario="+codUsuario);
	}
}



//crear las areas del aiepi
function areas(codArea){
		var contenedor = document.getElementById('Opcion');
		var divArea = document.createElement('div');
		divArea.setAttribute('id','DIV'+codArea);
		divArea.className = "areas";
		contenedor.insertBefore(divArea,document.getElementById('btnFinalizar'));
		ajax = getXMLObject();
		ajax.onreadystatechange = function (){
			if (ajax.readyState == 4) {
				if (ajax.status==200){
					var resp = ajax.responseText;
					var detResp = resp.split("%");
					if(detResp[2]=="N/A"){
						divArea.innerHTML = detResp[1];
						preguntas(codArea,null);
					}else{
						divArea.innerHTML = detResp[1];
					}
				}
			}
		};
		ajax.open("POST","ControlConfiguracion",true);
		ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ajax.send("op=5&codArea="+codArea);
}

//cargar preguntas AiepiGestantes
function preguntasGestantes(codArea,radio){
	var contenedor = document.getElementById("");
	var divArea = document.createElement('div');
	divArea.setAttribute('id', codArea);
	divArea.className = "preguntas";
	contenedor.appendChild(divArea);
	var divDiag = document.createElement('div');
	divDiag.setAttribute('id', 'diag');
	divDiag.className = "diag";
	contenedor.appendChild(divDiag);
	
	
	var xmlhttp = getXMLObject();
	xmlhttp.onreadystatechange = function (){
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status==200){
				divArea.innerHTML=xmlhttp.responseText;
			}
		}
		
		if(document.getElementById('Calculado')!=null){
			//alert("prueba");
		}
		
		
	};
	xmlhttp.open("POST","ControlConfiguracion",true);
	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlhttp.send("op=2&codArea="+codArea+"&anio="+anio+"&meses="+meses+"&dias="+dias);
}

//desplegar las preguntas del area
function preguntas(codArea,radio){	
	if(ocultarArea){
		contAreasFinalizadas--;
		ocultarArea=false;
	}
	if(radio!=null){
		radio.disabled=true;
	}
	var contenedor = document.getElementById('DIV'+codArea);
	var divArea = document.createElement('div');
	divArea.setAttribute('id', codArea);
	divArea.className = "preguntas";
	contenedor.appendChild(divArea);
	var divDiag = document.createElement('div');
	divDiag.setAttribute('id', 'diag');
	divDiag.className = "diag";
	contenedor.appendChild(divDiag);
	var xmlhttp = getXMLObject();
	xmlhttp.onreadystatechange = function (){
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status==200){
				divArea.innerHTML=xmlhttp.responseText;
			}
		}
		if(document.getElementById('Calculado')!=null){
			//alert("prueba");
		}
	};
	xmlhttp.open("POST","ControlConfiguracion",true);
	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlhttp.send("op=2&codArea="+codArea+"&anio="+anio+"&meses="+meses+"&dias="+dias);
}

function obtenerDatosRespuesta(area,boton){	
	
	var cont1 = document.getElementById('DIV'+area).childNodes;
	var cant = cont1[1].childNodes.length;
	var vect = cont1[1].childNodes;
	var divDiag = cont1[2];
	
	var VRb = new Array;
	var k=0;
	var s=0;
	var VSb = new Array;
	var l=0;
	var VTb = new Array;

	for (var i = 0;i<cant;i++){	
		
		var id = vect[i].getAttribute('id');
		id = id.substring(0,4);
		
		if (id=="preg"){
			
			var vect2 = vect[i].childNodes;		
			
			for(var j = 0; j<vect2.length;j++){
		
				if(vect2[j].nodeName=="DIV"){
					
					if (vect2[j].childNodes.length==1){
						
						if (vect2[j].childNodes[0].type=="radio"){
							VRb[k]=vect2[j].childNodes[0];
							k++;
						}
						
						if (vect2[j].childNodes[0].nodeName=="SELECT"){
							VSb[s]=vect2[j].childNodes[0];
							s++;
						}
											
						if (vect2[j].childNodes[0].type=="text"){
							VTb[l]=vect2[j].childNodes[0];
							l++;
						}
					}else{
						
						var vectTemp = vect2[j].childNodes;
						
						for (var u=0;u<vect2[j].childNodes.length;u++){
							id = vectTemp[u].getAttribute('id');
							id = id.substring(0,4);
							
							
							//////
							
							if (id=="preg"){
								
								var vect2Temp = vectTemp[u].childNodes;		
								
								for(var w = 0; w<vect2Temp.length;w++){
											
									if(vect2Temp[w].nodeName=="DIV"){
										if (vect2Temp[w].childNodes[0].type=="radio"){
											VRb[k]=vect2Temp[w].childNodes[0];
											k++;
										}
										
										if (vect2Temp[w].childNodes[0].nodeName=="SELECT"){
											VSb[s]=vect2Temp[w].childNodes[0];
											s++;
										}
															
										if (vect2Temp[w].childNodes[0].type=="text"){
											VTb[l]=vect2Temp[w].childNodes[0];
											l++;
										}
										
										
										
									}
									
								}
								
							}
							
							//////
							
						}
					}
					
				}
				
			}
			
		}
	}
	
	
	
	var vRBval = valRBEncuesta(VRb, "radio");
	var vSval = valRBEncuesta(VSb, "select");
	var vTval = valRBEncuesta(VTb, "text");
	
	
	
	if(vRBval[0]&&vSval[0]&&vTval[0]){
		var respuestas = serializar(vRBval[1], vSval[1], vTval[1]);
		
		var xmlhttp = getXMLObject();
		xmlhttp.onreadystatechange = function (){
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status==200){
					//
					var resp = xmlhttp.responseText;
					
					if(resp.length>0){
						var detResp = resp.split("=");
					
						if(detResp[0].length>0){						
							vectDetResp = detResp[0].split("&");
							for (z=0;z<vectDetResp.length;z++){
								var vectDetDiag = vectDetResp[z].split("|");
								labelDiag = document.createElement('label');
								if(vectDetDiag[1]=="1"){
									labelDiag.className = "labelDiag1";
								}else{
									if(vectDetDiag[1]=="0"){
										labelDiag.className = "labelDiag0";
									}else{
										if(vectDetDiag[1]=="2"){
											labelDiag.className = "labelDiag2";
										}
									}
								}
								nt = document.createTextNode(vectDetDiag[0]);
								labelDiag.appendChild(nt);
								divDiag.appendChild(labelDiag);
							}
								
						}
						if(detResp[1].length>0){
							vecAreas = detResp[1].split("-");	
							for (var m=0;m<vecAreas.length;m++){
								areas(vecAreas[m]);
								cantAreas++;
							}
						}
					}
					
						
					
					
					
				}
			}
		};
		xmlhttp.open("POST","ControlConfiguracion",true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send("op=4&resp="+respuestas+"&codArea="+area+"&tipoAiepi="+tipoAiepi+"&codAiepi="+codAiepi);
		for (i1=0;i1<VRb.length;i1++){
			VRb[i1].disabled=true;
		}
		for (i1=0;i1<VSb.length;i1++){
			VSb[i1].disabled=true;
		}
		for (i1=0;i1<VTb.length;i1++){
			VTb[i1].disabled=true;
		}
		boton.disabled=true;
		if(document.getElementById('rdSi'+area)!=null&&document.getElementById('rdNo'+area)!=null) {
			document.getElementById('rdSi'+area).disabled=true;
			document.getElementById('rdNo'+area).disabled=true;
		}
		contAreasFinalizadas++;
		
	}else{
		alert("Faltan campos por llenar");
	}
		
}

//funcion para serializar los vectores con las respuestas validadas
function serializar(vecT1, vecT2, vecT3){
	var cadSerialize = "";  //cadena con los datos serializados
	for (i = 0;i<vecT1.length;i++){
		cadSerialize += vecT1[i]+",";
	}
	for (i = 0;i<vecT2.length;i++){
		cadSerialize += vecT2[i]+",";
	}
	for (i = 0;i<vecT3.length;i++){
		cadSerialize += vecT3[i]+",";
	}
	return cadSerialize;
}

//validar campos de respuestas
function valRBEncuesta(vecRB,tipo){
	var rE = new Array;// vector que almacenara el numero de Rb selecionados y
	// los RB que estan seleccionado
	var sRB = false; // alamcena si todos los Rb han sido seleccionados
	var j=0;
	var k=0;
	if(tipo=="radio"){
		j=0;
		k=0;
		sRB = false;
		for (i=0;i<vecRB.length;i++){
			if(vecRB[i].checked == true){
				rE[k]=vecRB[i].value;
				k++;
				j++;
			}
		}
		if (j==(vecRB.length/2)){
			sRB = true;
		}
		vecRBValidados = new Array(sRB,rE);
	}
	if(tipo=="select"){
		j=0;
		k=0;
		sRB = false;
		for (i=0;i<vecRB.length;i++){
			var valorSelect = vecRB[i].value.split("-"); 
			if(valorSelect[1]!=""){
				rE[k]=vecRB[i].value;
				k++;
				j++;
			}	
		}
		if (j==vecRB.length){
			sRB = true;
		}
		vecRBValidados = new Array(sRB,rE);
	}
	if(tipo=="text"){
		j=0;
		k=0;
		sRB = false;
		
		for (i=0;i<vecRB.length;i++){
			if(vecRB[i].value.length!=""){
				rE[k]=vecRB[i].getAttribute('name')+"-"+vecRB[i].value;
				
				k++;
				j++;
			}
		}
		
		if (j==vecRB.length){
			sRB = true;
		}
		vecRBValidados = new Array(sRB,rE);
	}
	
	return vecRBValidados;
	
}

//cargar preguntas de dependencia
function cargarPregDepend(a){
	//alert(a.value);
	var vradio=a.value.split("-");
	var contenedor = document.getElementById(vradio[0]);
	var newDIV = document.createElement('div');
	newDIV.className = "preguntasDependencia";
	if(contenedor.lastChild.getAttribute('id')=="nuevo"){
		contenedor.removeChild(contenedor.lastChild);
	}
	var xmlhttp = getXMLObject();
	xmlhttp.onreadystatechange = function (){
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status==200){
				if(xmlhttp.responseText.length>0){
					newDIV.setAttribute('id','nuevo');
					contenedor.appendChild(newDIV);
					newDIV.innerHTML=xmlhttp.responseText;
				}

			}
		}
	};
	xmlhttp.open("POST","ControlConfiguracion",true);
	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlhttp.send("op=3&codPreg="+vradio[0]+"&codResp="+vradio[1]);
}

//ocultar las preguntas de dependecia
function ocultarPregDepend(a){
	var vradio=a.value.split("-");
	var contenedor = document.getElementById(vradio[0]);
	var newDIV = document.createElement('div');
	
	
	if(contenedor.lastChild.getAttribute('id')=="nuevo"){
		contenedor.removeChild(contenedor.lastChild);
	}
	
}

//ocultar las preguntas de dependecia
function ocultarPreguntas(codArea){
	ocultarArea = true;
	contAreasFinalizadas++;
	document.getElementById('rdSi'+codArea).disabled=false;
	var contenedor = document.getElementById('DIV'+codArea);
	var cont=document.getElementById('DIV'+codArea).childNodes;
	
	if (cont.length>1){
		
		if (cont[2].getAttribute('id')=="diag"){
			contenedor.removeChild(cont[2]);
		}
		if (cont[1].getAttribute('id')==codArea){
			contenedor.removeChild(cont[1]);
		}
	}
	
}

//finalizar la encuesta
function finalizar(){
	
	if (contAreasFinalizadas==cantAreas){
		if (confirm("Desea finalizar la encuesta")){
			ventObservaciones();
		}
		
	}else{
		alert("Faltan preguntas por contestar");
	}
}

function impReporte(codPac,fechaActual){
	
	var edad = encodeURIComponent(document.getElementById('edadUsu').value);
	var observaciones = document.getElementById('observaciones').value;
	//actualizar las observaciones y la edad del paciente
	guardarObservaciones(codAiepi, edad, observaciones);
	//ver reporte
	Abrir(codPac, fechaActual);
	//actualizar pagina
	location.reload();

}

function finalizarReporte(){
	var edad = encodeURIComponent(document.getElementById('edadUsu').value);
	var observaciones = document.getElementById('observaciones').value;
	//actualizar pagina
	location.reload();
	//actualizar las observaciones y la edad del paciente
	guardarObservaciones(codAiepi, edad, observaciones);
}

function Abrir(codPac,fecha) {	
    pagina="aiepi_report.jsp?codPac="+codPac+"&fecha="+fecha+"";			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
}

function guardarObservaciones(codAiepi,edad,observaciones){
	var xmlhttp = getXMLObject();
	xmlhttp.onreadystatechange = function (){
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status==200){
				
			}
		}
	};
	xmlhttp.open("POST","ControlConfiguracion",true);
	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	xmlhttp.send("op=7&codAiepi="+codAiepi+"&edad="+edad+"&observaciones="+observaciones);
}

function cerrar(){
	document.getElementsByTagName('body')[0].removeChild(fondo);
	document.getElementsByTagName('body')[0].removeChild(mensaje);
	fondo=false;
	mensaje=false;
}

///jsp reportes
function verificarPaciente2(){
	
	var contenedor = document.getElementById('Opcion');
	var codUsuario = document.getElementById('txtCodusuario').value;
	var tdoc = document.getElementById('cmbTipoDoc').value;
	var ndoc = document.getElementById('txtNumDocumento').value;
	if(tdoc=="Seleccione"||ndoc==""){
		alert("Datos incorrectos");
	}else{
		var xmlhttp = getXMLObject();
		xmlhttp.onreadystatechange = function (){
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status==200){
					resp = xmlhttp.responseText;
					resp = resp.split("_");
					if(resp[0]=="na"){
						alert("Paciente no presenta AIEPI");
						location.reload();
					}else{
						contenedor.innerHTML = resp[1];
					}
				}
			}
		};
		xmlhttp.open("POST","ControlConfiguracion",true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send("op=8&tdoc="+tdoc+"&ndoc="+ndoc);
	}
}

function soloNumero(campo){
	if(isNaN(campo.value)){
		campo.value="";
		alert("Campo para solo numeros");
		campo.focus();
	}
}

//funci de la fecha
var patron = new Array(2, 2, 4);
function masca(d, pat, nums, dias, mes, annio) {
	var sep = "/";
	if (d.valant != d.value) {
		val = d.value
		largo = val.length
		ini = val.substring(0, 2);
		if ((ini > 31) || (ini == "00")) {
			// alert("Dia No Valido!!!");
			val = d.value = "01";
			// d.focus();
		}
		val = val.split(sep);

		val2 = ''
		for (r = 0; r < val.length; r++) {
			val2 += val[r]
		}
		ini = val2.substring(2, 4);
		if ((ini == "04") || (ini == "06") || (ini == "09") || (ini == "11")) {
			x = val2.substring(0, 2);
			if (x == "31") {
				val2 = "30";
				val2 = val2 + ini;
			}
		}
		if ((ini > 12) || (ini == "00")) {
			// alert("Mes No Valido!!!");
			mescj = val2.substring(0, 2);
			val2 = mescj + "01";
			// d.focus();
		}

		ano = val2.substring(4, 8);
		dia = val2.substring(0, 2);

		if (ini == "02") {
			if ((dia == "30") || (dia == "31") || (dia == "29")) {
				if (ano.length == 4) {
					if ((ano % 100 != 0)
							&& ((ano % 4 == 0) || (ano % 400 == 0))) {
						val2 = "29";
						val2 = val2 + ini;
					} else {
						val2 = "28";
						val2 = val2 + ini;
					}
					val2 = val2 + ano;
				}
			}
		}

		/*
		 * /////////////////////////////////////////////////// //Validar fecha
		 * mayor que la fecha actual
		 * 
		 * if(ano.length==4){ if (ano==annio) { if(ini==mes){
		 * if((dia==dias)||(dia<dias)){ alert("La fecha de Vencimiento no puede
		 * ser menor o igual a la fecha Actual"); val2=''; } }else{ if(ini<mes){
		 * alert("La fecha de Vencimiento no puede ser menor o igual a la fecha
		 * Actual"); val2=''; } } } if (ano<annio) { alert("La fecha de
		 * Vencimiento no puede ser menor o igual a la fecha Actual"); val2=''; } }
		 * 
		 * ///////////////////////////////////////////////////
		 */
		if (nums) {
			for (z = 0; z < val2.length; z++) {
				if (isNaN(val2.charAt(z))) {
					letra = new RegExp(val2.charAt(z), "g")
					val2 = val2.replace(letra, "")

				}
			}
		}

		val = ''
		val3 = new Array()
		for (s = 0; s < pat.length; s++) {
			val3[s] = val2.substring(0, pat[s])
			val2 = val2.substring(pat[s])
		}

		for (q = 0; q < val3.length; q++) {
			if (q == 0) {
				val = val3[q]
			} else {
				if (val3[q] != "") {
					val += sep + val3[q]
				}
			}
		}
		d.value = val
		d.valant = val
	}
	var elEvento =  window.event;  // arguments[0] ||
    var tecla = elEvento.keyCode;
}