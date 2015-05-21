
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
		    xmlhttp.open("POST","ControlAprobarExamenIndividual?valor=3",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = MostrarIndividual;
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }	     
	    }
function MostrarIndividual() {	
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			document.getElementById('grupo').innerHTML = xmlhttp.responseText
			
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
function validar() {
    var c=document.getElementById('yo').value;
	var oscar=c;
 if(c!=1){
	for(var i=0; i<=c-1; i++){	
      if (form1.ca[i].checked) {
       	 d=form1.ca[i].value;  
       	if(xmlhttp){
    		xmlhttp.open("POST","ControlAprobarExamenIndividual?valor=2&codigoResul="+d,true);
    		xmlhttp.send("");
    	}//fin del si
    		getXMLObject();
    		var xmlhttp = new getXMLObject(); 
     }     	  
      oscar--; 
     }
 }
 //if(c==1){
	 form1.ca[0].checked;
	 d=form1.ca[0].value;
	  	if(xmlhttp){
    		xmlhttp.open("POST","ControlAprobarExamenIndividual?valor=2&codigoResul="+d,true);
    		xmlhttp.send("");
    	}//fin del si
    		getXMLObject();
 //}
    		window.location.href="lab_aprobarExamenTexto.jsp",true;  		
   		 
 }