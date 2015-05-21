/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/** *********************************************** */
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

/** *********************************************** */

function informes_Odontologia_por_paciente() {
	var TipoDocumento = document.getElementById("cbafiliacion").value;
	var NumeroDocumento = document.getElementById("txtnumdoc").value;
	if (NumeroDocumento == "") {
		alert("Escriba Numero Documento.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "registroPaciente", true); // getname will be the
													// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			document.getElementById('historias_encontradas').innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&TipoDocumento=" + TipoDocumento
				+ "&NumeroDocumento=" + NumeroDocumento); // Posting txtname
															// to Servlet
	}
}

function mostrarInformeOdontologiaModificacion(CodTratamiento,CodHistoria,idCodigopaciente){
	
	pagina="odont_modificacion_historia.jsp?CodTratamiento="+CodTratamiento+"&CodHistoria="+CodHistoria +"&CodPaciente="+idCodigopaciente ;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Modificacion_De_Cateterismo",opciones);
}




function eliminarTratamiento(idTratamiento) {
	
	var TipoDocumento = document.getElementById("cbafiliacion").value;
	var NumeroDocumento = document.getElementById("txtnumdoc").value;

	
    ajax = getXMLObject();
		ajax.open("POST", "registroPaciente", true); // getname will be the
													// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			document.getElementById('historias_encontradas').innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4&idTratamiento=" + idTratamiento+"&TipoDocumento=" + TipoDocumento
			+ "&NumeroDocumento=" + NumeroDocumento); // Posting txtname
															// to Servlet
	
}



function eliminarHistoria(idTratamiento,idHistoria) {
	var TipoDocumento = document.getElementById("cbafiliacion").value;
	var NumeroDocumento = document.getElementById("txtnumdoc").value;
	
	ajax = getXMLObject();
	ajax.open("POST", "registroPaciente", true); // getname will be the
												// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
		document.getElementById('historias_encontradas').innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&idTratamiento=" + idTratamiento+"&idHistoria="+idHistoria+"&TipoDocumento=" + TipoDocumento
			+ "&NumeroDocumento=" + NumeroDocumento); // Posting txtname
														// to Servlet
}

