
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
function cargar() {	
	
	getXMLObject();
	if(xmlhttp) {    
			
		document.getElementById('examenes').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';

			xmlhttp.open("POST","ControlAprobarExamenIndividual?valor=1",true); //getname will be the servlet name
			
		    xmlhttp.onreadystatechange  = MostrarIndividual;
		    xmlhttp.send(""); //Posting txtname to Servlet
	}	      
} 
function MostrarIndividual() {	
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			document.getElementById('examenes').innerHTML = xmlhttp.responseText
			
	     }
	     else {
	        alert("Error during AJAX call. Please try again,dato");
	     }
	   }
}
////////////////////////////////////////////////////////////////////////////////////////
function checkAll() {
	 var i;
   var nodoCheck = document.getElementsByTagName("input");
   var varCheck = document.getElementById("all").checked;
   for (i=0; i<nodoCheck.length; i++){
       if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name != "all" && nodoCheck[i].disabled == false) {
           nodoCheck[i].checked = varCheck;
       }
   } 
}
/////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////
function validar(){
    var c=document.getElementById('yo').value;
	var oscar=c;
 if(c!=1){
	for(var i=0; i<=c-1; i++){	
      if (document.form1.ca[i].checked) {
       	 d=document.form1.ca[i].value; 
       	 enviar(d);
     }     	  
      oscar--; 
     }
 }
 else{
	 if(document.form1.ca.checked){
	 d=document.form1.ca.value;
	 enviar(d);
	}
}
 }//fin de la funcion 
function enviar(d){
	  getXMLObject();
		  var xmlhttp = new getXMLObject();
		  var codigou=document.getElementById('txtcodigousu').value;	
	  if(xmlhttp) { 
	    xmlhttp.open("POST","ControlAprobarExamenIndividual?valor=2&codigoResul="+d+"&codigou="+codigou,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = PERMISOS;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet	    
	 }	
	  window.location.reload(); 
}
function PERMISOS() {		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {			     	
				var a = xmlhttp.responseText		     	
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
}
function omitir(cod,CodAsignacion){
	if(xmlhttp) { 
	    xmlhttp.open("POST","ControlAprobarExamenIndividual?valor=4&codigoResul="+cod+"&CodAsignacion="+CodAsignacion,true); //getname will be the servlet name
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet	    
	 }
	//alert(cod)
	window.location.reload(); 
}
