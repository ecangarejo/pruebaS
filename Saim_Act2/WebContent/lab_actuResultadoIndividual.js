
function getXMLObject()  //XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
	}
	catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
		}
		catch (e2) {
			xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
	}
   return xmlHttp;  // Mandatory Statement returning the ajax object created
}
var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function ajaxFunction() {	
	if(xmlhttp) { 		 	 
		xmlhttp.open("POST","ControlActuResultadoIndividual?valor=1",true); //getname will be the servlet name
		xmlhttp.onreadystatechange  = ComprobarPeticion;
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send(""); //Posting txtname to Servlet
	}  
}

function ComprobarPeticion() {	
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('grupo').innerHTML = xmlhttp.responseText;
		}
		else {
			alert("Error during AJAX call. Please try again,ComprobarPeticion");
		}
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function ajaxLlenarExamenes() {	
	if(xmlhttp) { 	
		var codGrupo=document.getElementById('cmbGrupo').value;	 	 
		xmlhttp.open("POST","ControlActuResultadoIndividual?valor=2&codGrupo="+codGrupo,true); //getname will be the servlet name
		xmlhttp.onreadystatechange  = ComprobarExamenes;
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send(""); //Posting txtname to Servlet
	}  
}

function ComprobarExamenes() {	
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('option').innerHTML = xmlhttp.responseText;
		}
		else {
			alert("Error during AJAX call. Please try again,ComprobarPeticion");
		}
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function  BuscarExam(){
	if(xmlhttp){
		var ti=document.getElementById('cbafiliacion').selectedIndex;
		var tipo=document.getElementById('cbafiliacion').options[ti].text;
		var cedula=document.getElementById('txtnumdoc').value;
		var codSub=document.getElementById('cmbSubgrupo').value;
		xmlhttp.open("POST","ControlActuResultadoIndividual?valor=3&codSub="+codSub+"&cedula="+cedula+"&tipo="+tipo+"",true); //getname will be the servlet name
		xmlhttp.onreadystatechange  = EnviarExamen;
		xmlhttp.send(""); //Posting txtname to Servlet
	}
}
function EnviarExamen() {	
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('examBuscados').innerHTML = xmlhttp.responseText;
		}
		else {
			alert("Error during AJAX call. Please try again,ComprobarPeticion");
		}
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function mostarexamenes(ano,mes,dia,hora,minuto,segundo,codpa,codexa) {
	getXMLObject();
	if(xmlhttp){
		var fecha=ano+"-"+mes+"-"+dia
		var horas=hora+":"+minuto+":"+segundo
		xmlhttp.open("POST","ControlActuResultadoIndividual?valor=4&codsub="+codexa+"&numero="+codpa+"&fecha="+fecha+"&horas="+horas,true);	 
		xmlhttp.onreadystatechange  = verExamenes;
		xmlhttp.send(""); 
	}
}
function verExamenes() {
	if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
			document.getElementById('examenes').innerHTML = xmlhttp.responseText;
		}	
		else {
			alert("Error during AJAX call. Please try again,busexam");
		}		
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function Actualizar(){		 
	if(xmlhttp) {
		var a=document.form1.txtResultado.value;
		var codResultado=document.form1.codResultado.value;
		var codtipoexa=document.form1.tipoExam.value;		
		xmlhttp.open("POST","ControlActuResultadoIndividual?valor=5&resultado="+a+"&tipo="+codtipoexa+"&codResultado="+codResultado,true); 
		xmlhttp.send("");
	} 	
	alert("Actualizacion Exitosa.!");
	window.location.reload();
}
