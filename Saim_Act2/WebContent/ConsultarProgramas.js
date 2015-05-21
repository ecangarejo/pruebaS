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
//////////////////////////////////////////////////////////////////////////////////////////////////////////
function BuscarPrograma(){
	var Programa=document.getElementById("txtCodigoPrograma").value;
	var CodManual=document.getElementById("cmbManualTar").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlConsultas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("resultado").innerHTML = ajax.responseText
			//alert(ajax.responseText);
			//window.location.reload();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CP&Programa="+Programa+"&CodManual="+CodManual); //Posting txtname to Servlet*/
	
}

function BuscarDx(){
	var NombreDx=document.getElementById("txtNombreDiagnostico").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlConsultas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("resultado").innerHTML = ajax.responseText
			//alert(ajax.responseText);
			//window.location.reload();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BDX&NombreDx="+NombreDx); //Posting txtname to Servlet*/

	
	
}

function BuscarValorEntidades(CodPrograma){
	var CodManual=document.getElementById("cmbManualTar").value;

	ajax=getXMLObject();
	ajax.open("POST","ControlConsultas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("EntiVal").innerHTML = ajax.responseText
			//alert(ajax.responseText);
			//window.location.reload();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MEP&Programa="+CodPrograma+"&CodManual="+CodManual); //Posting txtname to Servlet*/

	
}
