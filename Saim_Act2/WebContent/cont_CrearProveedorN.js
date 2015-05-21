function getXMLObject(){ // XML OBJECT
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // For Old Microsoft Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // For Microsoft IE 6.0+
		} catch (e2) {
			xmlHttp = false // No Browser accepts the XMLHTTP Object then false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}

var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object
// /////////////////////////////////////////////////////////////////////////////////////

var patron = new Array(2, 2, 4);
var patron2 = new Array(1, 3, 3, 3, 3)
function masca(d, sep, pat, nums) {
	if (d.valant != d.value) {
		val = d.value
		largo = val.length
		ini = val.substring(0, 2);
		if (ini > 31) {
			alert("Dia No Valido!!!");
			val = d.value = "";
			d.focus();
		}
		val = val.split(sep);

		val2 = ''
		for (r = 0; r < val.length; r++) {
			val2 += val[r]
		}
		ini = val2.substring(2, 4);

		if (ini > 12) {
			alert("Mes No Valido!!!");
			val2 = d.value = "";
			d.focus();
		}

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
}


var patronp = new Array(2);
var patrona = new Array(4);

function valAnoPeriodo(d, pat, nums, op) {
	val = d.value;
	largo = val.length;

	if (nums) {
		for (z = 0; z < val.length; z++) {
			if (isNaN(val.charAt(z))) {
				letra = new RegExp(val.charAt(z), "g");
				d.value = val.replace(letra, "");
				val = d.value;
				largo = val.length;
			}
		}
	}

	if (d.valant != d.value) {
		if (op == 0) {
			if (largo > 2) {
				d.value = val.substring(0, 2);
			}

			ini = val.substring(0, 2);
			if ((ini > 13) || (ini == "00")) {
				d.value = "01";
			}
		} else {
			if (largo == 4) {
				document.getElementById('MC').value = "";
				document.getElementById('MC').focus();
			}
		}
	}

	var elEvento = window.event; // arguments[0] ||
	var tecla = elEvento.keyCode;
	if (tecla == 13) {
		if ((document.getElementById('AC').value != "")
				& (document.getElementById('MC').value != "")
				& (document.getElementById('RC1').value != "Seleccione")) {
			var ano = document.getElementById('AC').value;
			var periodo = document.getElementById('MC').value;
			var RC1 = document.getElementById('RC1').value;
		} else {
			alert("Debe seleccionar tanto el A\u00f1o como el Periodo");
			if (document.getElementById('MC').value == "") {
				document.getElementById('MC').focus();
			}
			if (document.getElementById('AC').value == "") {
				document.getElementById('AC').focus();
			}
		}
	}
}

function Prueba() {
	var cmbPer = document.getElementById("cmbPer").value;
	ajax = getXMLObject();
	var contenido = document.getElementById('Contenido');
	ajax.open("POST", "ControlProveedorN", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1p&cmbPer=" + cmbPer);
}


function verificar() {
	var Numero_identificacion = document.getElementById("Numero_identificacion").value;
	var Tipo_identificacion = document.getElementById("Tipo_identificacion").value;
	
	ajax = getXMLObject();
	var contenido = document.getElementById('Contenido');
	ajax.open("POST", "ControlProveedorN", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var id= ajax.responseText;
			if(id=="1"){
				alert("El Numero de documento ya se  encuentra, por favor digite otro ");
				document.getElementById("Numero_identificacion").value="";
				document.getElementById("Numero_identificacion").focus();
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&Numero_identificacion=" + Numero_identificacion);
}


function ListarTerceros() {
	ajax = getXMLObject();
	var contenido = document.getElementById('Contenido');
	ajax.open("POST", "ControlProveedorN", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=LT");
}

function MostrarTercero(NumDoc,codtercero) {
	document.getElementById("txtNumDoc").value = NumDoc;
	document.getElementById('txtNumDoc').disabled=true;
	document.getElementById("codtercero").value = codtercero;
	BuscarProveedor();

}

function BuscarProveedor() {
	var numero_documento = document.getElementById("txtNumDoc").value;

	if (numero_documento == "") {
		alert("Digite Numero De Identificacion.");
	}else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlProveedorN", true); // getname will be the servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("Contenido").innerHTML = ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&Numero_identificacion=" + numero_documento); // Posting txtname to Servle
	}
}

function ListarTercerosCom() {
	ajax = getXMLObject();
	var contenido = document.getElementById('Contenido');
	ajax.open("POST", "ControlProveedorN", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=LTCom");
}

function MostrarTerceroCom(NumDoc,codtercero) {
	document.getElementById("txtNumDoc").value = NumDoc;
	document.getElementById('txtNumDoc').disabled=true;
	document.getElementById("codtercero").value = codtercero;
	BuscarProveedorCom();

}

function BuscarProveedorCom() {
	var numero_documento = document.getElementById("txtNumDoc").value;

	if (numero_documento == "") {
		alert("Digite Numero De Identificacion.");
	}else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlProveedorN", true); // getname will be the servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("Contenido").innerHTML = ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2&Numero_identificacion=" + numero_documento); // Posting txtname to Servle
	}
}




function GuardarProveedorPN() {
	var usuario = document.getElementById("usu").value;
	var Tipo_identificacion = document.getElementById("Tipo_identificacion").value;
	var Numero_identificacion = document.getElementById("Numero_identificacion").value;
	var Primer_nombre = document.getElementById("Primer_nombre").value;
	var segundo_nombre = document.getElementById("segundo_nombre").value;
	var Primer_apellido = document.getElementById("Primer_apellido").value;
	var segundo_apellido = document.getElementById("segundo_apellido").value;
	var direccion = document.getElementById("direccion").value;
	var telefono = document.getElementById("telefono").value;
	var fax = document.getElementById("fax").value;

	var cbdep = document.getElementById("cbdep").value;
	var cbmun = document.getElementById("cbmun").value;

	var contacto = document.getElementById("contacto").value;
	var tel_contacto = document.getElementById("tel_contacto").value;
	var email_contacto = document.getElementById("email_contacto").value;
	var regimen = document.getElementById("regimen").value;
	var DiasPlazo = document.getElementById("DiasPlazo").value;
	var FechaIngreso = document.getElementById("FechaIngreso").value;
	var PagWeb = document.getElementById("PagWeb").value;

	if (Numero_identificacion == "") {
		alert("Digite Numero de identificacion");
		document.getElementById("Numero_identificacion").focus();
	} else {
		if (Primer_nombre == "") {
			alert("Digite Nombre");
			document.getElementById("Primer_nombre").focus();
		} else {
			if (Primer_apellido == "") {
				alert("Digite Apellido");
				document.getElementById("Primer_apellido").focus();
			} else {
				if (direccion == "") {
					alert("Digite Direccion");
					document.getElementById("direccion").focus();
				} else {
					if (telefono == "") {
						alert("Digite Telefono");
						document.getElementById("telefono").focus();
					} else {
						if (cbdep == "Seleccione") {
							alert("Seleccione Departamento");
							document.getElementById("cbdep").focus();
						} else {
							if (cbmun == "Seleccione") {
								alert("Seleccione Municipio");
								document.getElementById("cbmun").focus();
							} else {
								ajax = getXMLObject();
								ajax.open("POST", "ControlProveedorN", true); // getname will be the servlet name
								ajax.onreadystatechange = function() {
									if (ajax.readyState == 4) {
										alert(ajax.responseText);
										window.location.href = "Com_CrearProveedor.jsp";
									}
								}
								ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
								ajax.send("valor=1.1&Tipo_identificacion="+ Tipo_identificacion
										+ "&Numero_identificacion="+ Numero_identificacion
										+ "&Primer_nombre=" + Primer_nombre
										+ "&segundo_nombre=" + segundo_nombre
										+ "&Primer_apellido=" + Primer_apellido
										+ "&segundo_apellido="+ segundo_apellido 
										+ "&direccion="+ direccion 
										+ "&telefono=" + telefono
										+ "&fax=" + fax 
										+ "&cbdep=" + cbdep
										+ "&cbmun=" + cbmun 
										+ "&contacto="+ contacto 
										+ "&tel_contacto="+ tel_contacto 
										+ "&email_contacto="+ email_contacto 
										+ "&regimen="+ regimen 
										+ "&DiasPlazo=" + DiasPlazo
										+ "&FechaIngreso=" + FechaIngreso
										+ "&PagWeb=" + PagWeb
										+ "&usuario=" + usuario); // Posting txtname to Servle
							}
						}
					}
				}
			}
		}
	}
}


function GuardarProveedorPJ() {
	var usuario = document.getElementById("usu").value;
	var Tipo_identificacion = document.getElementById("Tipo_identificacion").value;
	var Numero_identificacion = document.getElementById("Numero_identificacion").value;
	var razon_social = document.getElementById("razon_social").value;
	var direccion = document.getElementById("direccion").value;
	var telefono = document.getElementById("telefono").value;
	var fax = document.getElementById("fax").value;

	var cbdep = document.getElementById("cbdep").value;
	var cbmun = document.getElementById("cbmun").value;

	var contacto = document.getElementById("contacto").value;
	var tel_contacto = document.getElementById("tel_contacto").value;
	var email_contacto = document.getElementById("email_contacto").value;

	var regimen = document.getElementById("regimen").value;
	var DiasPlazo = document.getElementById("DiasPlazo").value;
	var FechaIngreso = document.getElementById("FechaIngreso").value;
	var PagWeb = document.getElementById("PagWeb").value;

	if (Numero_identificacion == "") {
		alert("Digite Numero de identificacion");
		document.getElementById("Numero_identificacion").focus();
	} else {
		if (razon_social == "") {
			alert("Digite la Razon Social");
			document.getElementById("razon_social").focus();
		} else {
			if (direccion == "") {
				alert("Digite Direccion");
				document.getElementById("direccion").focus();
			} else {
				if (telefono == "") {
					alert("Digite Telefono");
					document.getElementById("telefono").focus();
				} else {
					if (cbdep == "Seleccione") {
						alert("Seleccione Departamento");
						document.getElementById("cbdep").focus();
					} else {
						if (cbmun == "Seleccione") {
							alert("Seleccione Municipio");
							document.getElementById("cbmun").focus();
						} else {
							ajax = getXMLObject();
							ajax.open("POST", "ControlProveedorN", true); // getname will be the servlet name
							ajax.onreadystatechange = function() {
								if (ajax.readyState == 4) {
									alert(ajax.responseText);
									window.location.href = "Com_CrearProveedor.jsp";
								}
							}
							ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
							ajax.send("valor=1.2&Tipo_identificacion="+ Tipo_identificacion
									+ "&Numero_identificacion="+ Numero_identificacion 
									+ "&razon_social="+ razon_social 
									+ "&direccion=" + direccion
									+ "&telefono=" + telefono 
									+ "&fax=" + fax
									+ "&cbdep=" + cbdep 
									+ "&cbmun=" + cbmun
									+ "&contacto=" + contacto
									+ "&tel_contacto=" + tel_contacto
									+ "&email_contacto=" + email_contacto
									+ "&regimen=" + regimen 
									+ "&DiasPlazo="+ DiasPlazo 
									+ "&FechaIngreso="+ FechaIngreso 
									+ "&PagWeb=" + PagWeb
									+ "&usuario=" + usuario); // Posting txtname to Servle
						}
					}
				}
			}
		}
	}
}


function ActualizarProveedorCont() {
	var codtercero = document.getElementById("codtercero").value;
	var retIva = document.getElementById("retIva").value;
	var retIca = document.getElementById("retIca").value;
	var AutoRetenedor = document.getElementById("AutoRetenedor").value;
	var OpExt = document.getElementById("OpExt").value;
	var IndCliente = "";
	var IndProveedor = "";
	var IndEmpleado = "";
	if (document.getElementById("chk").checked) {
		IndCliente = "0";
	} else {
		IndCliente = "1";
	}
	if (document.getElementById("chk2").checked) {
		IndProveedor = "0";
	} else {
		IndProveedor = "1";
	}
	if (document.getElementById("chk3").checked) {
		IndEmpleado = "0";
	} else {
		IndEmpleado = "1";
	}
	var txtBanco = document.getElementById("txtCodBanco").value;
	var TipoCuenta = document.getElementById("TipoCuenta").value;
	var NumCuenta = document.getElementById("NumCuenta").value;

	if (retIva == "") {
		retIva = "0";
	}
	if (retIca == "") {
		retIca = "0";
	}
	if (txtBanco == "") {
		txtBanco = "0";
	}
	if (TipoCuenta == "Seleccione") {
		TipoCuenta = "0";
	}

	if (AutoRetenedor == "Seleccione") {
			alert("Seleccione Autoretenedor");
			document.getElementById("AutoRetenedor").focus();
		} else {
			ajax = getXMLObject();
			ajax.open("POST", "ControlProveedorN", true); // getname will be the servlet name
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					alert(ajax.responseText);
					window.location.href = "cont_CrearProveedorN.jsp";
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=1.3&codtercero="+ codtercero 
					+ "&OpExt=" + OpExt
					+ "&IndCliente=" + IndCliente 
					+ "&IndProveedor="+ IndProveedor 
					+ "&IndEmpleado=" + IndEmpleado 
					+ "&retIva="+ retIva 
					+ "&retIca=" + retIca 
					+ "&AutoRetenedor="+ AutoRetenedor 
					+ "&txtBanco=" + txtBanco 
					+ "&TipoCuenta="+ TipoCuenta 
					+ "&NumCuenta=" + NumCuenta); // Posting txtname to Servle
		}
	}


function ActualizarProveedorCom() {
	var codtercero = document.getElementById("codtercero").value;
	var usuario = document.getElementById("usu").value;
	var Tipo_identificacion = document.getElementById("Tipo_identificacion").value;
	var Numero_identificacion = document.getElementById("Numero_identificacion").value;
	var razon_social = document.getElementById("razon_social").value;
	var direccion = document.getElementById("direccion").value;
	var telefono = document.getElementById("telefono").value;
	var fax = document.getElementById("fax").value;

	var cbdep = document.getElementById("cbdep").value;
	var cbmun = document.getElementById("cbmun").value;

	var contacto = document.getElementById("contacto").value;
	var tel_contacto = document.getElementById("tel_contacto").value;
	var email_contacto = document.getElementById("email_contacto").value;

	var regimen = document.getElementById("regimen").value;
	var DiasPlazo = document.getElementById("DiasPlazo").value;
	var FechaIngreso = document.getElementById("FechaIngreso").value;
	var PagWeb = document.getElementById("PagWeb").value;
	var estado = document.getElementById("estado").value;

	if (Numero_identificacion == "") {
		alert("Digite Numero de identificacion");
		document.getElementById("Numero_identificacion").focus();
	} else {
		if (razon_social == "") {
			alert("Digite la Razon Social");
			document.getElementById("razon_social").focus();
		} else {
			if (direccion == "") {
				alert("Digite Direccion");
				document.getElementById("direccion").focus();
			} else {
				if (telefono == "") {
					alert("Digite Telefono");
					document.getElementById("telefono").focus();
				} else {
					if (cbdep == "Seleccione") {
						alert("Seleccione Departamento");
						document.getElementById("cbdep").focus();
					} else {
						if (cbmun == "Seleccione") {
							alert("Seleccione Municipio");
							document.getElementById("cbmun").focus();
						} else {
							ajax = getXMLObject();
							ajax.open("POST", "ControlProveedorN", true); // getname will be the servlet name
							ajax.onreadystatechange = function() {
								if (ajax.readyState == 4) {
									alert(ajax.responseText);
									window.location.href = "Com_ActProveedor.jsp";
								}
							}
							ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
							ajax.send("valor=1.4&codtercero="+ codtercero 
									+"&Tipo_identificacion="+ Tipo_identificacion
									+ "&Numero_identificacion="+ Numero_identificacion 
									+ "&razon_social="+ razon_social 
									+ "&direccion=" + direccion
									+ "&telefono=" + telefono 
									+ "&fax=" + fax
									+ "&cbdep=" + cbdep 
									+ "&cbmun=" + cbmun
									+ "&contacto=" + contacto
									+ "&tel_contacto=" + tel_contacto
									+ "&email_contacto=" + email_contacto
									+ "&regimen=" + regimen 
									+ "&DiasPlazo="+ DiasPlazo 
									+ "&FechaIngreso="+ FechaIngreso 
									+ "&PagWeb=" + PagWeb
									+ "&usuario=" + usuario
									+ "&estado="+estado); // Posting txtname to Servle
						}
					}
				}
			}
		}
	}
}
















function autocompletaBanco(){
	var NomBanco=document.getElementById("txtBanco").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedorN",true); // getname will be the servlet
												// name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			document.getElementById("banco").innerHTML=ajax.responseText;
			var Contador=document.getElementById("txtCont").value;
			if(Contador=="1"){
				var CodBanco=document.getElementById("txtCodBanco").value;
				var NombrBanco=document.getElementById("txtNombBanco").value;
				SeleccionarBanco(CodBanco,NombrBanco);
				// NomBanco=document.getElementById("txtNombBanco").value;
			}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=abc&NomBanco="+NomBanco); // Posting txtname to Servle

}

function SeleccionarBanco(CodBanco,NombrBanco){
	document.getElementById("txtBanco").value=NombrBanco;
	document.getElementById("txtCodBanco").value=CodBanco;
	document.getElementById("banco").style.display = "none";
}






function validarNro(e) {
	var key = "";
	if (window.event) { // IE
		key = e.keyCode;
	} else if (e.which) { // Netscape/Firefox/Opera
		key = e.which;
	}
	if (key < 48 || key > 57) {
		if (key == 46 || key == 8) // Detectar . (punto) y backspace  (retroceso)
		{
			return true;
		} else {
			return false;
		}
	}
	return true;
}

//Función que permite solo Números
function ValidaSoloNumeros() {
	if ((event.keyCode < 48) || (event.keyCode > 57))
		event.returnValue = false;
}