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

function informes_pyp_planfam_por_paciente() {
	var TipoDocumento = document.getElementById("cbafiliacion").value;
	var NumeroDocumento = document.getElementById("txtnumdoc").value;
	if (NumeroDocumento == "") {
		alert("Escriba Numero Documento.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "pypModificarData", true); // getname will be the
													// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			document.getElementById('informes_pyp_planfam_paciente').innerHTML = ajax.responseText;
			desactivarelementoFormulario("cbafiliacion");
            desactivarelementoFormulario("txtnumdoc");
            desactivarelementoFormulario("btnBuscarPac");
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Consultar&TipoDocumento=" + TipoDocumento
				+ "&NumeroDocumento=" + NumeroDocumento); // Posting txtname
															// to Servlet
	}
}


function mostrarInformesPYPPlanFam(idcodInforme){
	pagina="pyp_reportePyp.jsp?CodInfPYPPlanFam="+idcodInforme;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Informe_Pyp",opciones);
}


function desactivarelementoFormulario(idCombo) {
	document.getElementById(idCombo).disabled = true;
}