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
////////////////////////////////////////////////////////////////////////////
function getCheckedRadio() {
	if(xmlhttp) {
		 var radioButtons = document.getElementsByName("radiobutton");
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;    
		         xmlhttp.open("POST","ControlCrearFormato?valor="+val,true); //getname will be the servlet name
		         xmlhttp.onreadystatechange  = Mostrar;
		         xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		         xmlhttp.send(""); //Posting txtname to Servlet
			  }	     
	    }
	}
}
function Mostrar() {
	
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			var cambio= xmlhttp.responseText
			document.getElementById('cambio').innerHTML = cambio
	     }
	     else {
	        alert("Error during AJAX call. Please try again,getCheckedRadio");
	     }
	   }
}
///////////////////////////////////////////////////////////////////////////
function CrearTexto(){
	
	var pregunta=document.getElementById("txtArea").value;
	ajax=getXMLObject();
	if(pregunta==""){
		alert("Falta Digitar La Pregunta.");
	}
	if(pregunta!=""){
	ajax.open("POST","ControlCrearFormato",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			
			alert(ajax.responseText);
			window.location.reload();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&pregunta="+pregunta+"&tipo=1"); //Posting txtname to Servlet*/
	}

}

function CrearTextoCorto(){
	
	var pregunta=document.getElementById("txtArea").value;
	ajax=getXMLObject();
	if(pregunta==""){
		alert("Falta Digitar La Pregunta.");
	}
	if(pregunta!=""){
	ajax.open("POST","ControlCrearFormato",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			
			alert(ajax.responseText);
			window.location.reload();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&pregunta="+pregunta+"&tipo=3"); //Posting txtname to Servlet*/
	}

}
///////////////////////////////////////////////////////////////////////////

function ComprobarFormulario() {
	var x;
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('formulario').innerHTML = xmlhttp.responseText;
	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }
	}
function formulario(){
	 if(xmlhttp) {
		    xmlhttp.open("POST","ControlCrearFormato?valor=0",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarFormulario;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
}
///////////////////////////////////////////////////////////////////////////////
  function insertarFila(Modo) {
  var elmTBODY = document.getElementById('tbody');
  var elmTR;
  var elmTD2;
  var elmText2;
  if (Modo==0) { // Modo HTML
     elmTR = elmTBODY.insertRow(1);
     for (var i=0; i<1; i++) {
        elmTD2= elmTR.insertCell(i);
        elmText2 = document.createElement('input');
        elmTD2.appendChild(elmText2);
        }
     }
}
///////////////////////////////////////////////////////////////////////////////
function buscardatos(){
	var pregunta=document.getElementById("txtArea").value;
	if(xmlhttp) {
		xmlhttp.open("POST","ControlCrearFormato?valor=3&pregunta="+pregunta+"&tipo=2",true); //getname will be the servlet name
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	}
	
	var nodoCheck = document.getElementsByTagName("input")
	for(var i=0;i<nodoCheck.length-1;i++){
		if(nodoCheck[i].type=="text"){
			var respuesta=nodoCheck[i].value;			
			GuardarSeleccion(respuesta)			
			}
		if(i==0){
		alert("Pregunta Creada Con Exito");
		window.location.href="hic_CrearItems.jsp";
			}
	}
}

function GuardarSeleccion(respuesta){
	if(respuesta==""){
		alert("Digite la Respuesta")
		}
	else{
	 getXMLObject();
	 var xmlhttp = new getXMLObject();
	if(xmlhttp) { 
		var pregunta=document.getElementById("txtArea").value;
	    xmlhttp.open("POST","ControlCrearFormato?valor=4&respuesta="+respuesta+"&pregunta="+pregunta,true); //getname will be the servlet name
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet	
	}
	}
}