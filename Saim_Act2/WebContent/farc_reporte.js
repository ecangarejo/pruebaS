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

function Exportar() {
	var fechaini="";
	var fechafin="";
	var pi="";
	var pf="";
	var periodo = document.getElementById("periodo").value;
	var anio = document.getElementById("anio").value;
	
	
	if((periodo=="Seleccione")){
		alert("Seleccione un Periodo a reportar.");
	}else if ((anio=="Seleccione")) {
		alert("Seleccione un año a reportar.");
	}else{
		if(periodo=="P1"){
			fechaini=anio+'-01-01';
			fechafin=anio+'-03-31';
			pi="01";
			pf="03";
		}else if (periodo=="P2") {
			fechaini=anio+'-04-01';
			fechafin=anio+'-06-30';
			pi="04";
			pf="06";
		}else if (periodo=="P3") {
			fechaini=anio+'-07-01';
			fechafin=anio+'-09-30';
			pi="07";
			pf="09";
		}else if (periodo=="P3") {
			fechaini=anio+'-10-01';
			fechafin=anio+'-12-31';
			pi="10";
			pf="12";
		}
		
		ajax = getXMLObject();
		ajax.open("POST", "ControlReporteFarc", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById('SISMED').innerHTML = ajax.responseText;
				
				document.getElementById("cj").innerHTML=document.getElementById("ii").value;
			}
	}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&fechaini=" + fechaini + "&fechafin="+ fechafin + "&pi="+ pi + "&pf="+ pf + "&anio="+ anio );
		
	}
}