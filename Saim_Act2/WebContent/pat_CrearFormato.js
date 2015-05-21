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
			ajax.open("POST","Pat_ControlFormatoHic",true); //getname will be the servlet name
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

function ActualizarFormato(){
	var CodigoFormato=document.getElementById("cmbFormato").value;
	var CodigoArea=document.getElementById("cmbAreas").value;
	var formulario=document.getElementById("areas");
	if(CodigoFormato=="Seleccione"){
		alert("Seleccione Un Formato.");
	}
	if(CodigoArea=="Seleccione"){
		alert("Seleccione Un Area.");
	}
	if((CodigoFormato!="Seleccione")&&(CodigoArea!="Seleccione")){
		ajax=getXMLObject();
		ajax.open("POST","Pat_ControlFormatoHic",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var contenido=ajax.responseText;
				formulario.innerHTML=contenido;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		ajax.send("valor=actu&CodigoFormato="+CodigoFormato+"&CodArea="+CodigoArea);
	}
}


function VerAreas(){
	//alert()
	var CodigoFormato=document.getElementById("cmbFormato").value;
	var formulario=document.getElementById("areas");
	if(CodigoFormato=="Seleccione"){
		alert("Seleccione Un Area.");
	}
	if((CodigoFormato!="Seleccione")){
		ajax=getXMLObject();
		ajax.open("POST","Pat_ControlFormatoHic",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var contenido=ajax.responseText;
				formulario.innerHTML=contenido;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		ajax.send("valor=cambio&CodigoFormato="+CodigoFormato);
	}
}

function ComprobarFormulario() {
	//var x;
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('formulario').innerHTML = xmlhttp.responseText;
	     }
	     else {
	        alert("Error during AJAX call ComprobarFormulario. Please try again");
	     }
	   }
	}
function formulario(){
	 if(xmlhttp) { 	
		    xmlhttp.open("POST","Pat_ControlFormatoHic?valor=0",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarFormulario;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
}
////////////////////////////////////////////////////////////////////////////
function ComprobarAsignarArea() {
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var aviso=xmlhttp.responseText;			
			document.getElementById("areas").innerHTML=aviso;
			document.getElementById("txtArea").value="";
			document.getElementById("txtArea").focus="";
			document.getElementById("txtCodArea").value="";
	     }
	     else {
	        alert("Error during AJAX call ComprobarAsignarArea. Please try again");
	     }
	   }
	}
function AsignarArea(){
	var NomFormato=document.getElementById("txtNomFormato").value;
	var CodArea=document.getElementById("txtCodArea").value;
	if(NomFormato==""){alert("Falta Ingresar El Nombre Del Formato!!!");}
	if(CodArea==""){alert("Elija Un Area Para El Formato!!!");}
	if((NomFormato!="")&&(CodArea!="")){
	 if(xmlhttp) { 	
		    xmlhttp.open("POST","Pat_ControlFormatoHic?valor=1&NomFormato="+NomFormato+"&CodArea="+CodArea,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarAsignarArea;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
	}
}

function regresar(){
	var NomFormato=document.getElementById("txtNomFormato").value;
	 if(xmlhttp) { 		 
		    xmlhttp.open("POST","Pat_ControlFormatoHic?valor=4&NomFormato="+NomFormato,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarAsignarArea;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
}

function omitir(codRelacion){

	xmlhttp.open("POST","Pat_ControlFormatoHic?valor=3&codRelacion="+codRelacion,true); //getname will be the servlet name
    xmlhttp.onreadystatechange  = regresar;
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlhttp.send(""); //Posting txtname to Servlet
   
}
////////////////////////////////////////////////////////////////////////////
function FinalizarFormato(){
	alert("Formato Creado Con Exito!!!");
	window.location.href="hic_CrearFormato.jsp"; 
}