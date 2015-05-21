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
function Radio() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('formulario');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","Pat_ControlCrearArea",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			ajax.send("valor="+val); //Posting txtname to Servlet
		}	     
	}
}

function VerPreguntas(){
	var CodigoArea=document.getElementById("cmbNombreArea").value;
	var formulario=document.getElementById("preguntas");
	if(CodigoArea=="Seleccione"){
		alert("Seleccione Un Area.");
	}
	if((CodigoArea!="Seleccione")){
		ajax=getXMLObject();
		ajax.open("POST","Pat_ControlCrearArea",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var contenido=ajax.responseText;
				formulario.innerHTML=contenido;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		ajax.send("valor=cambio&CodigoArea="+CodigoArea);
	}
}

function ActualizarFormato(){
	var CodigoArea=document.getElementById("cmbNombreArea").value;
	var CodPregunta=document.getElementById("cmbPreguntas").value;
	var formulario=document.getElementById("preguntas");
	if(CodigoArea=="Seleccione"){
		alert("Seleccione Un Area.");
	}
	if(CodPregunta=="Seleccione"){
		alert("Seleccione Una Pregunta.");
	}
	if((CodigoArea!="Seleccione")&&(CodPregunta!="Seleccione")){
		ajax=getXMLObject();
		ajax.open("POST","Pat_ControlCrearArea",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var contenido=ajax.responseText;
				formulario.innerHTML=contenido;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		ajax.send("valor=actu&CodigoArea="+CodigoArea+"&CodPregunta="+CodPregunta);
	}
}
////////////////////////////////////////////////////////////////////////////
function ComprobarAsignarPregunta() {
	//var x;
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var aviso=xmlhttp.responseText;		
			document.getElementById("preguntas").innerHTML=aviso;
			document.getElementById("txtCodPregunta").value="";
			document.getElementById("txtPregunta").value="";
			document.getElementById("txtPregunta").focus;
	     }
	     else {
	        alert("Error during AJAX call ComprobarAsignarPregunta. Please try again");
	     }
	   }
	}
function AsignarPregunta(){
	var NomArea=document.getElementById("txtNomArea").value;
	var CodPregunta=document.getElementById("txtCodPregunta").value;
	if(NomArea==""){alert("Falta Ingresar El Nombre Del Area!!!");}
	if(CodPregunta==""){alert("Elija Una Pregunta Para El Area!!!");}
	if((NomArea!="")&&(CodPregunta!="")){
	 if(xmlhttp) { 	
		    xmlhttp.open("POST","Pat_ControlCrearArea?valor=1&NomArea="+NomArea+"&CodPregunta="+CodPregunta,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarAsignarPregunta;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
	}
}

function regresar(){
	var NomArea=document.getElementById("txtNomArea").value;
	 if(xmlhttp) { 		 
		    xmlhttp.open("POST","Pat_ControlCrearArea?valor=4&NomArea="+NomArea,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarAsignarPregunta;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
}

function omitir(codRelacion){

	xmlhttp.open("POST","Pat_ControlCrearArea?valor=3&codRelacion="+codRelacion,true); //getname will be the servlet name
    xmlhttp.onreadystatechange  = regresar;
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlhttp.send(""); //Posting txtname to Servlet
   
}
///////////////////////////////////////////////////////////////////////////////
function FinalizarArea(){
	alert("Area Creada Con Exito!!!");
	window.location.href="hic_crearArea.jsp"; 
}