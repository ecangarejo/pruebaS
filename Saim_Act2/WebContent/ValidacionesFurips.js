function getXMLObject() {
	var xmlhttp;
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
var xmlhttp = new getXMLObject();


function ajaxxMun(x) {
	var dpto_evento = document.getElementById("cbdep").value;
	ajax = getXMLObject();
	ajax.open("POST", "ControlValidaciones", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("cbmun2").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("yo=5&dpto_evento=" + dpto_evento);
}


function ajaxxMuni(h) {
	var dpto_evento = document.getElementById("dpto").value;
	ajax = getXMLObject();
	ajax.open("POST", "ControlValidaciones", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("municipio2").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("yo=6&dpto_evento=" + dpto_evento);
	
}

function ajaxxMunic(h) {
	var dpto_evento = document.getElementById("dp").value;
	ajax = getXMLObject();
	ajax.open("POST", "ControlValidaciones", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("mun2").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("yo=7&dpto_evento=" + dpto_evento);
}

// Esta funcion envia el codigo del evento seleccionado ya sea accidente de transito, naturales o terroristas
function ajaxEvento(h) {
	var evento = document.getElementById("evento").value;
	ajax = getXMLObject();
	ajax.open("POST", "ControlValidaciones", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("evento2").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("yo=4&evento=" + evento);
}


// funcion para validar numeros
function validarNro(e) {
	var key = "";
	if (window.event) { // IE
		key = e.keyCode;
	} else if (e.which) { // Netscape/Firefox/Opera
		key = e.which;
	}
	if (key < 48 || key > 57) {
		if (key == 46 || key == 8) // Detectar . (punto) y backspace
									// (retroceso)
		{
			return true;
		} else {
			return false;
		}
	}
	return true;
}

// funcion para validar solo letras
function soloLetras(e) {
	key = e.keyCode || e.which;
	tecla = String.fromCharCode(key).toLowerCase();
	letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
	especiales = [ 8, 37, 39, 46 ];

	tecla_especial = false;
	for ( var i in especiales) {
		if (key == especiales[i]) {
			tecla_especial = true;
			break;
		}
	}
	if (letras.indexOf(tecla) == -1 && !tecla_especial) {
		return false;
	}
}

// funcion de calendario en español
$( function($) {
	$.datepicker.regional['es'] = {
		closeText :'Cerrar',
		prevText :'<Ant',
		nextText :'Sig>',
		currentText :'Hoy',
		monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre' ],
		monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
		dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado' ],
		dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mie', 'Juv', 'Vie', 'Sab' ],
		dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa' ],
		weekHeader :'Sem',
		dateFormat :'dd-mm-yy',
		showAnim :'clip',
		changeMonth :true,
		changeYear :true,
		showWeek :true,
		firstDay :1,
		isRTL :false,
		showMonthAfterYear :false,
		yearSuffix :''
	};
	$.datepicker.setDefaults($.datepicker.regional['es']);
});

// funcion para colocar un calendario desde: hasta:
$( function() {
	$("#desde").datepicker( {
		defaultDate :"+1w",
		changeMonth :true,
		numberOfMonths :1,
		onClose : function(selectedDate) {
			$("#hasta").datepicker("option", "minDate", selectedDate);
		}
	});
	$("#hasta").datepicker( {
		defaultDate :"+1w",
		changeMonth :true,
		numberOfMonths :1,
		onClose : function(selectedDate) {
			$("#desde").datepicker("option", "maxDate", selectedDate);
		}
	});
});

//funcion para colocar un calendario desde: hasta:
$( function() {
	$("#desde1").datepicker( {
		maxDate: "+0D",
		defaultDate :"+1w",
		changeMonth :true,
		numberOfMonths :1,
		onClose : function(selectedDate) {
			$("#hasta1").datepicker("option", "minDate", selectedDate);
		}
	});
	$("#hasta1").datepicker( {
		maxDate: "+0D",
		defaultDate :"+1w",
		changeMonth :true,
		numberOfMonths :1,
		onClose : function(selectedDate) {
			$("#desde1").datepicker("option", "maxDate", selectedDate);
		}
	});
});

$( function() {
	$("#fec_evento").datepicker( { 
		maxDate: "+0D",
		defaultDate :"+1w",
		changeMonth :true,
		numberOfMonths :1,
		onClose : function(selectedDate) {
			$("#fec_radicado").datepicker("option", "minDate", selectedDate);
		}
	});
	$("#fec_radicado").datepicker( {
		maxDate: "+0D",
		defaultDate :"+1w",
		changeMonth :true,
		numberOfMonths :1,
		onClose : function(selectedDate) {
			$("#fec_evento").datepicker("option", "maxDate", selectedDate);
		}
	});
});

//funcion de calendario
$(function() {
  $( "#fec_remision" ).datepicker({maxDate: "+0D"});
});
$(function() {
	  $( "#fec_aceptacion" ).datepicker({maxDate: "+0D"});
});
$(function() {
	  $( "#fec_Ingreso" ).datepicker({maxDate: "+0D"});
});
$(function() {
	  $( "#fec_Egreso" ).datepicker({maxDate: "+0D"});
});
$(function() {
	  $( "#fechanacimiento" ).datepicker();
});

//validacion de la hora 
function CheckTime(str){ 
	hora=str.value; 
		if (hora=='') { 
			return; 
		} 
		if (hora.length>5) { 
			alert("Introdujo una cadena mayor a 5 caracteres"); 
			return; 
		} 
		if (hora.length!=5) { 
			alert("Introducir HH:MM"); 
			return; 
		} 
		a=hora.charAt(0); //<=2 
		b=hora.charAt(1); //<4 
		c=hora.charAt(2); //: 
		d=hora.charAt(3); //<=5 
		if ((a==2 && b>3) || (a>2)) { 
			alert("El valor que introdujo en la Hora no corresponde, introduzca un digito entre 00 y 23"); 
			return; 
		} 
		if (d>5) { 
			alert("El valor que introdujo en los minutos no corresponde, introduzca un digito entre 00 y 59"); 
			return; 
		} 
		if (c!=':' ) { 
			alert("Introduzca el caracter ':' para separar la hora de los minutos"); 
			return; 
		} 
} 
