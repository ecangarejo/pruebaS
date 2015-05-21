function getXMLObject() // XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP") // For Old Microsoft
		// Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") // For Microsoft
			// IE 6.0+
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
// ////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////

function Rips() {
	var contenidos = document.getElementById('form1');
	ajax = getXMLObject();
	ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenidos.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1A"); //Posting txtname to Servle	
}


function BuscarFA() {
	var eps = document.getElementById("cmbE").value;
	var fi = document.getElementById("FI").value;
	var ff = document.getElementById("FF").value;
	var contenidos = document.getElementById('DFD');
	
	if ((fi == "") || (ff == "")) {
		alert("Debe seleccionar un rango de busqueda");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				contenidos.innerHTML = ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3A&fi=" + fi + "&eps=" + eps + "&ff=" + ff); //Posting txtname to Servle	
	}
}


function MoverS() {
	var fd = document.getElementById("FD");
	var fs = document.getElementById("FS");
	var c = 0;
	var x = 0;

	if (fs.length == 0) {
		for ( var i = 0; i < fd.length; i++) {
			if (fd[i].selected) {
				x++;
			}

			if ((fd[i].selected) && (c == 0)) {
				var options = document.createElement('option');
				options.value = fd[i].value;
				options.text = fd[i].text;
				document.getElementById("FS").add(options);
				fd[i] = null;
				i--;
				c++;
			}
		}
	} else {
		alert("Ya hay una factura seleccionada para generar RIPS");
	}
	
	if (x > 1) {
		alert("Solo se permite generar rips de una factura a la vez");
	}
}


function MoverD() {
	var fd = document.getElementById("FD");
	var fs = document.getElementById("FS");

	for ( var i = 0; i < fs.length; i++) {
		if (fs[i].selected) {
			var options = document.createElement('option');
			options.value = fs[i].value;
			options.text = fs[i].text;
			document.getElementById("FD").add(options);
			fs[i] = null;
			i--;
		}
	}
}


function ExportarA() {
	var nom = document.getElementById("txtNombre").value;
	var fi = document.getElementById("FI").value;
	var ff = document.getElementById("FF").value;
	var fs = document.getElementById("FS");
	var CodEnt = document.getElementById("cmbE").value;
	var rut = "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Saim";
	//var contenido="//usr//share//tomcat6//webapps//Saim";
	//var contenido="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Saim";
	if (nom == "") {
		alert("Debe seleccionar el Nombre del archivo destino");
	} else {
		if (fs.length == 0) {
			alert("Seleccione las Facturas de las cuales desea Generar los RIPS");
		} else {
			var datos = fs.options[0].text;
			var fsl = fs.length;
			document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Uno.gif"> 1%';
			/*** Archivo de Transacciones***/
			ajax = getXMLObject();
			var ctaf = 0;
			ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					ctaf = ajax.responseText;
					
					USA(rut,nom,fi, ff, fsl, datos, CodEnt, ctaf);
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=5A&rut=" + rut + "&nom=" + nom +"&fi=" + fi+ "&ff=" + ff + "&fsl=" + fsl + "&datos=" + datos+ "&CodEnt=" + CodEnt + "&ARA=1"); //Posting txtname to Servle
		}
	}
}


function USA(rut,nom, fi, ff, fsl, datos, CodEnt, ctaf) {
	/*** Archivo de Descripcion Agrupada***/

	/*** Archivo de Transacciones***/
	document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Diez.gif"> 10%';

	var ctus = 0;
	ajax = getXMLObject();
	ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			ctus = ajax.responseText;
			
			AMA(rut,nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5A&rut=" + rut +"&nom=" + nom + "&fi=" + fi + "&ff=" + ff+ "&fsl=" + fsl + "&datos=" + datos + "&CodEnt=" + CodEnt+ "&ARA=2"); //Posting txtname to Servle			
}//fin de la creacion del folder


function AMA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus) {
	document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Veinte.gif"> 20%';

	/*** Archivo de Medicamentos***/
	ajax = getXMLObject();
	var ctam = 0;
	ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			ctam = ajax.responseText;
			
			ACA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5A&rut=" + rut + "&nom=" + nom + "&fi=" + fi + "&ff=" + ff+ "&fsl=" + fsl + "&datos=" + datos + "&CodEnt=" + CodEnt+ "&ARA=3"); //Posting txtname to Servle
}//fin de la creacion del folder


function ACA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam) {
	/*** Archivo de Consultas***/
	document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Treinta.gif"> 30%';

	/*** Archivo de Otros Servicios***/
	ajax = getXMLObject();
	var ctac = 0;
	ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			ctac = ajax.responseText;
			
			ATA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5A&rut=" + rut + "&nom=" + nom + "&fi=" + fi + "&ff=" + ff	+ "&fsl=" + fsl + "&datos=" + datos + "&CodEnt=" + CodEnt+ "&ARA=4"); //Posting txtname to Servle
}//fin de la creacion del folder


function ATA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac) {
	document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Cuarenta.gif"> 40%';

	/*** Archivo de Otros Servicios***/
	ajax = getXMLObject();
	var ctat = 0;
	ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			ctat = ajax.responseText;
			
			AUA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac, ctat);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5A&rut=" + rut + "&nom=" + nom + "&fi=" + fi + "&ff=" + ff+ "&fsl=" + fsl + "&datos=" + datos + "&CodEnt=" + CodEnt+ "&ARA=5"); //Posting txtname to Servle
}//fin de la creacion del folder


function AUA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac, ctat) {
	document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Cincuenta.gif"> 50%';

	/*** Archivo de Urgencias***/

	/*** Archivo de Otros Servicios***/
	ajax = getXMLObject();
	var ctau = 0;
	ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			ctau = ajax.responseText;
			
			APA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac, ctat, ctau);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5A&rut=" + rut + "&nom=" + nom + "&fi=" + fi + "&ff=" + ff+ "&fsl=" + fsl + "&datos=" + datos + "&CodEnt=" + CodEnt+ "&ARA=6"); //Posting txtname to Servle
}//fin de la creacion del folder


function APA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac, ctat, ctau) {
	document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Sesenta.gif"> 60%';

	/*** Archivo de Procedimientos***/
	ajax = getXMLObject();
	var ctap = 0;
	ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			ctap = ajax.responseText;
			
			RNA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac, ctat, ctau, ctap);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5A&rut=" + rut + "&nom=" + nom + "&fi=" + fi + "&ff=" + ff	+ "&fsl=" + fsl + "&datos=" + datos + "&CodEnt=" + CodEnt+ "&ARA=7"); //Posting txtname to Servle
}//fin de la creacion del folder


function RNA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac, ctat, ctau, ctap) {
	document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Setenta.gif"> 70%';

	/*** Archivo de Recien Nacidos***/
	ajax = getXMLObject();
	var ctrn = 0;
	ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			ctrn = ajax.responseText;
			
			AHA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac, ctat, ctau, ctap, ctrn);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5A&rut=" + rut + "&nom=" + nom + "&fi=" + fi + "&ff=" + ff+ "&fsl=" + fsl + "&datos=" + datos + "&CodEnt=" + CodEnt+ "&ARA=8"); //Posting txtname to Servle
}//fin de la creacion del folder


function AHA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac, ctat, ctau, ctap, ctrn) {
	document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Ochenta.gif"> 80%';

	/*** Archivo de Hospitalizacion***/
	ajax = getXMLObject();
	var ctah = 0;
	ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			ctah = ajax.responseText;
			
			CTA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac, ctat, ctau, ctap, ctrn, ctah);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5A&rut=" + rut + "&nom=" + nom + "&fi=" + fi + "&ff=" + ff+ "&fsl=" + fsl + "&datos=" + datos + "&CodEnt=" + CodEnt+ "&ARA=9"); //Posting txtname to Servle
}//fin de la creacion del folder



function CTA(rut, nom, fi, ff, fsl, datos, CodEnt, ctaf, ctus, ctam, ctac, ctat, ctau, ctap, ctrn, ctah) {
	document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Noventa.gif"> 99%';

	/*** Archivo de Control***/
	ajax = getXMLObject();
	ajax.open("POST", "ControlRipsNew", true); //getname will be the servlet name
	ajax.onreadystatechange = function() {
		
		if (ajax.readyState == 4) {
			var valida = ajax.responseText;
			// Realiza el llamado de la funcion zip
			zip(rut,nom,ctaf, ctus, ctam, ctac, ctat, ctau, ctap, ctrn, ctah);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5A&rut=" + rut + "&nom=" + nom + "&fi=" + fi + "&ff=" + ff + "&fsl=" + fsl+ "&datos=" + datos + "&CodEnt=" + CodEnt + "&ctac=" + ctac+ "&ctaf=" + ctaf + "&ctah=" + ctah + "&ctam=" + ctam + "&ctap="+ ctap + "&ctat=" + ctat + "&ctau=" + ctau + "&ctrn=" + ctrn+ "&ctus=" + ctus + "&ARA=10"); // Posting txtname to Servle
}//fin de la creacion del folder


//funcion para comprimir los archivos planos
function zip(rut,nom, ctaf, ctus, ctam, ctac, ctat, ctau, ctap, ctrn, ctah) {
	document.getElementById('form1').innerHTML = '<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Noventa.gif"> 99%';

	/** * Archivo de Control** */
	ajax = getXMLObject();
	ajax.open("POST", "ControlRipsNew", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {

		if (ajax.readyState == 4) {
			var valida = ajax.responseText;
			if (valida != "") {
				document.getElementById('form1').innerHTML = "<table><tr><td ><a href='zip.jsp?nom="+nom+"'>Descargar RIPS</a></td></tr></table>";
				pagina = "zip.jsp?nom="+nom;
				var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=si, resizable=yes, width=400, height=300, top=85,  left=140";
				window.open(pagina, "NUEVA", opciones);
			}
			alert("Rips Exportados Satisfactoriamente!!!");
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6&rut=" + rut +"&nom=" + nom + "&ctac=" + ctac + "&ctaf=" + ctaf+ "&ctah=" + ctah + "&ctam=" + ctam + "&ctap=" + ctap + "&ctat="+ ctat + "&ctau=" + ctau + "&ctrn=" + ctrn + "&ctus=" + ctus); // Posting txtname to Servle
}

function max() {
	var nom = document.getElementById("txtNombre").value;
	largo = nom.length;
	if (largo > 6) {
		var y = nom.substr(0, 6);
		document.getElementById("txtNombre").value = y;
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