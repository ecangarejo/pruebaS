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
			xmlHttp = false; // No Browser accepts the XMLHTTP Object then false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}
var xmlhttp = new getXMLObject(); 

function AuditarF() {
	// alert("consultarFAct");
	ajax = getXMLObject();
	var contenido1 = document.getElementById('Opcion');
	ajax.open("POST", "ControlConsultaPaciente", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido1.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=30A"); // Posting txtname to Servlet
}

function consulta(){
	var vFI = document.getElementById("FI").value;
	var vFF= document.getElementById("FF").value;
	
	ajax = getXMLObject();
	var contenido1 = document.getElementById('resultadosf');
	ajax.open("POST", "ControlConsultaPaciente", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido1.innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=30&vFI="+vFI+"&vFF="+vFF); // Posting txtname to Servlet
}



//funcion de la fecha
var patron = new Array(2, 2, 4);
function masca(d, pat, nums, dias, mes, annio) {
	var sep = "-";
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
    if(tecla == 13) { 
    		document.getElementById("ref0").focus();
    		
    }
	
}

// /////////////////
var fechafilter = /^\d{2,2}\-\d{2,2}\-\d{4,4}$/;
function vfi() {

	var e = document.getElementById("FI").value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha inicial es Invalida");
		document.getElementById("FI").value = "";
		// document.getElementById("FI").focus();
	}
	return returnval
}

function vff() {

	var e = document.getElementById("FF").value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha final es Invalida");
		document.getElementById("FF").value = "";
		// document.getElementById("FF").focus();
	}
	return returnval
}
// ////////////////////////
var fechafilter = /^\d{2,2}\-\d{2,2}\-\d{4,4}$/;
function checkfecha(c) {
	var cc = "fechai" + c;
	var e = document.getElementById(cc).value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha inicial es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	return returnval
}

var fechafilters = /^\d{2,2}\-\d{2,2}\-\d{4,4}$/;
function checkfechaf(c) {
	var cc = "fechaf" + c;
	var e = document.getElementById(cc).value;
	var returnval = fechafilters.test(e)
	if (returnval == false) {
		alert("La fecha final es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	return returnval
}
