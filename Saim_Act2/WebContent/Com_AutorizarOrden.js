function getXMLObject() { // XML OBJECT
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // For Old Microsoft Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // For Microsoft IE 6.0+
		} catch (e2) {
			xmlHttp = false; // No Browser accepts the XMLHTTP Object then false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}

var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object
/** *********************************************** */

// Ordenes sin aprobar
function Ord_Sin_Aprobar() {
	ajax = getXMLObject();
	ajax.open("POST", "ControlAutorizarOrden", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("orden").innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1");
}


//detalle de la orden sin aprobar
function Detalle_Sin_Aprobar(codorden, proveedor) {
	ajax = getXMLObject();
	ajax.open("POST", "ControlAutorizarOrden", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("orden").innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&codorden=" + codorden + "&proveedor=" + proveedor);
}


//anular orden muestra interfaz
function Anular_Orden(codorden) {
	if (confirm("¿Desea anular esta orden?")) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlAutorizarOrden", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("orden").innerHTML = ajax.responseText;
				document.getElementById("sin_aprobar").innerHTML = "";
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&codorden=" + codorden);
	}
}


// anular orden
function Anular(codorden) {
	var usuario = document.getElementById("usu").value;
	var motivo = document.getElementById("motivo").value;

	if (confirm("¿Desea anular esta orden?")) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlAutorizarOrden", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("orden").innerHTML = ajax.responseText;
				window.location.href = "Com_AutorizarOrden.jsp";
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4&codorden=" + codorden + "&usuario=" + usuario+ "&motivo=" + motivo);
	}
}


//Genera el reporte de orden en estudio
function Reporte2(codorden) {
	pagina = "Orden_ReporteEnEstudio.jsp?codorden=" + codorden;
	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}


//aprobar orden
function Aprobar_Orden(codorden) {
	var usuario = document.getElementById("usu").value;

	ajax = getXMLObject();
	ajax.open("POST", "ControlAutorizarOrden", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Orden Aprobada Exitosamente");
			window.location.href = "Com_AutorizarOrden.jsp";
			Reporte(codorden);
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&codorden=" + codorden + "&usuario=" + usuario);
}


//Genera el reporte de orden aprobada
function Reporte(codorden) {
	pagina = "Orden_Reporte.jsp?codorden=" + codorden;
	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}


// editar orden
function Editar_Orden(codorden, proveedor) {
	ajax = getXMLObject();
	ajax.open("POST", "ControlAutorizarOrden", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("orden").innerHTML = ajax.responseText;
			document.getElementById("detalle_orden").innerHTML = "";
			//document.getElementById("sin_aprobar").innerHTML = "";
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6&codorden=" + codorden + "&proveedor=" + proveedor);
}


//actualizar detalle orden
function Actualizar_Detalle(cod_detalle, codorden,proveedor) {
	ajax = getXMLObject();
	ajax.open("POST", "ControlAutorizarOrden", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("detalle_orden").innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7&cod_detalle=" + cod_detalle + "&codorden=" + codorden+ "&proveedor=" + proveedor);
}

//generar orden
function Update_Detalle(cod_detalle, codorden, proveedor) {
	var cantidad = document.getElementById("cantidad1").value;
	var descuento = document.getElementById("descuento1").value;
	var valor_unitario = document.getElementById("valor_unitario1").value;
	var iva = document.getElementById("iva1").value;

	if ((cantidad == "") || (descuento == "")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Actualizacion Exitosa.");
				document.getElementById("cantidad1").value = "";
				document.getElementById("descuento1").value = "";
				document.getElementById("iva1").value = "";
				document.getElementById("valor_unitario1").value = "";
				document.getElementById("detalle_orden").innerHTML = "";
				Editar_Orden(codorden, proveedor)
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=9&cod_detalle=" + cod_detalle + "&cantidad="
				+ cantidad + "&descuento=" + descuento + "&valor_unitario="
				+ valor_unitario + "&iva=" + iva+ "&codorden=" + codorden);
	}
}

//eliminar detalle de una orden
function Eliminar_Detalle(cod_detalle,codorden,proveedor) {
	if (confirm("¿Quieres eliminar este registro?")) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Eliminado exitosamente");
				Editar_Orden(codorden, proveedor)
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=10&cod_detalle=" + cod_detalle);
	}
}



//Ordenes sin aprobar
function OpcionOrden() {
	ajax = getXMLObject();
	ajax.open("POST", "ControlAutorizarOrden", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("orden").innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8");
}


//buscar orden aprobada
function OrdenAprobada() {
	var No_orden = document.getElementById("No_orden").value;
	if (No_orden == "") {
		alert("Por favor digite el numero de la orden.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlAutorizarOrden", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("orden").innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=9&No_orden=" + No_orden);
	}
}


//opciones de consulta
function Ord(val) {
	if (val == "1") {
		document.getElementById("inf").style.display = "inline";
		document.getElementById("datos").innerHTML = "";
	} else {
		document.getElementById("inf").style.display = "none";
		ajax = getXMLObject();
		ajax.open("POST", "ControlAutorizarOrden", true); // getname will be
															// the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("datos").innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=10&val=" + val);
	}
}


//buscar ordenes en estudio
function BuscarOrdenes() {
	var desde = document.getElementById("desde1").value;
	var hasta = document.getElementById("hasta1").value;
	var estado = document.getElementById("estado").value;

	if ((desde == "") || (hasta == "") || (estado == "Seleccione")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlAutorizarOrden", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("datos").innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=11&desde=" + desde + "&hasta=" + hasta + "&estado="+ estado);
	}
}


//Genera el reporte de orden aprobada
function Reportes(FI,FF,estado) {
	if(estado=="0"){
		pagina = "Orden_Reportes.jsp?FI=" + FI+"&FF="+FF+"&estado=OrdenesEstudio";
	}
	if(estado=="1"){
		pagina = "Orden_Reportes.jsp?FI=" + FI+"&FF="+FF+"&estado=OrdenesAprobadas";
	}
	if(estado=="2"){
		pagina = "Orden_Reportes.jsp?FI=" + FI+"&FF="+FF+"&estado=OrdenesAnuladas";
	}
	if(estado=="3"){
		pagina = "Orden_Reportes.jsp?FI=" + FI+"&FF="+FF+"&estado=Ordenes";
	}
	
	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}

function COrden() {
	var txtOrden = document.getElementById("txtOrden").value;
	var Pro = document.getElementById("Pro").value;
	var FI = document.getElementById("FI").value;
	var FF = document.getElementById("FF").value;
	if ((txtOrden == "") && (Pro == "Seleccione") && (FI == "") && (FF == "")) {
		alert("seleccione un criterio de busqueda");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlAutorizarOrden", true); // getname will be
															// the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("resultadosf").innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=12&txtOrden=" + txtOrden + "&Pro=" + Pro + "&FI=" + FI+ "&FF=" + FF);
	}
}

function CPro() {
	var Pro = document.getElementById("Pro").value;
	if ((Pro == "Seleccione")) {
		alert("seleccione un criterio de busqueda");
	} else {
		//alert(" criterio de busqueda" + Pro);
		ajax = getXMLObject();
		ajax.open("POST", "ControlAutorizarOrden", true); // getname will be
															// the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("resultadosf").innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=13&Pro=" + Pro);
	}
}


//Genera el reporte de orden aprobada
function CProveedor() {
	var Pro = document.getElementById("Pro").value;
	var FI = document.getElementById("FI").value;
	var FF = document.getElementById("FF").value;
	var Prod = document.getElementById("Prod").value;
	if ((Pro == "Seleccione") && (FI == "") && (FF == "")) {
		alert("seleccione un criterio de busqueda");
	} else {
		if((Pro != "Seleccione") && (FI != "") && (FF != "")){
			pagina = "Orden_ReportesConsolidado.jsp?FI=" + FI+"&FF="+FF+"&estado=OrdenProConsolidado&Pro="+Pro;
			var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
			opciones = opciones+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
			window.open(pagina, "NUEVA", opciones);
		}
		if((Prod != "Seleccione") && (FI != "") && (FF != "")){
			pagina = "Orden_ReportesConsolidado.jsp?FI=" + FI+"&FF="+FF+"&estado=OrdenProdConsolidado&Pro="+Prod;
			var opciones2 = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
			opciones2 = opciones2+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
			window.open(pagina, "NUEVA", opciones2);
		}
	
	}
}



// Función que permite solo Números
function ValidaSoloNumeros() {
	if ((event.keyCode < 48) || (event.keyCode > 57))
		event.returnValue = false;
}


//funci de la fecha
var patron = new Array(2, 2, 4);
function masca(d, pat, nums, dias, mes, annio) {
	var sep = "-";
	if (d.valant != d.value) {
		val = d.value
		largo = val.length
		ini = val.substring(0, 2);
		if ((ini > 31) || (ini == "00")) {
			val = d.value = "01";
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
			mescj = val2.substring(0, 2);
			val2 = mescj + "01";
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
	var elEvento = window.event; // arguments[0] ||
	var tecla = elEvento.keyCode;
	if (tecla == 13) {
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
	}
	return returnval
}

function vff() {

	var e = document.getElementById("FF").value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha final es Invalida");
		document.getElementById("FF").value = "";
	}
	return returnval
}
// ////////////////////////
var fechafilter1 = /^\d{2,2}\-\d{2,2}\-\d{4,4}$/;
function checkfecha(c) {
	var cc = "fechai" + c;
	var e = document.getElementById(cc).value;
	var returnval = fechafilter1.test(e)
	if (returnval == false) {
		alert("La fecha inicial es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	return returnval
}

var fechafilters = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
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