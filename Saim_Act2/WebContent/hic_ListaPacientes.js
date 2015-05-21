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
///////////////////////////////////////////////////////////////////////////////////
function ComprobarAdmisiones() {
	
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var lista=xmlhttp.responseText;
			document.getElementById('sugerencias').innerHTML = lista;
	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }
	}
function Admisiones(){
	
	 if(xmlhttp) { 	
		    xmlhttp.open("POST","ControlListarPacientes?valor=0",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarAdmisiones;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
}
//////////////////////////////////////////////////////////////////////////////////
function AsignarFormatos(CodAdm,CodPac){
	
	//alert("CodAdm "+CodAdm+" CodPac "+CodPac);
	window.location.href="hic_VerFormatos.jsp?CodPac="+CodPac+"&CodAdm="+CodAdm;
	//BuscarPaciente(CodPac,CodAdm);
}