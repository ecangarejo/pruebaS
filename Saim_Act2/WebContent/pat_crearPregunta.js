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
		    xmlhttp.open("POST","Pat_ControlCrearPregunta?valor=0",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarFormulario;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
}
//////////////////////////////////////////////////////////////////////////////////
function ComprobarCerrada() {
	var x;
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var aviso=xmlhttp.responseText
			if(aviso!=""){
				alert(aviso)
			}
			else{
				alert("Pregunta Creada Con Exito!!!");
				window.location.reload();
			}
	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }
	}
function CrearCerrada(TipoRequerimiento){
	var CodTipoRespuesta=document.getElementById("txtCodTipoRespuesta").value;
	var NombrePregunta=document.getElementById("txtNomPregunta").value;
	var PatronNormal=document.getElementById("txtPatronNormal").value;
	var UnidadPregunta=document.getElementById("txtUnidadPregunta").value;
	if(CodTipoRespuesta==""){
		alert("No Ha Seleccionado Ningun Tipo De Respuesta Para Esta Pregunta.");
	}
	if(NombrePregunta==""){
		alert("No Ha Digitado El Nombre De La Pregunta.");
	}
	if((CodTipoRespuesta!="")&&(NombrePregunta!="")){
	 if(xmlhttp) { 	
		    xmlhttp.open("POST","Pat_ControlCrearPregunta?valor=1&CodTipoRespuesta="+CodTipoRespuesta+"&NombrePregunta="+NombrePregunta+"&PatronNormal="+PatronNormal+"&UnidadPregunta="+UnidadPregunta+"&TipoRequerimiento="+TipoRequerimiento,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarCerrada;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
	}
}

function IngresarPregunta(){	
	var Requerido="";
	var TipoRequerimiento="";
	if(form1.chkRequerido.checked){
		Requerido=form1.chkRequerido.value;
	}	
	var Epicrisis="";
	if(form1.chkEpicrisis.checked){
		Epicrisis=form1.chkEpicrisis.value;
	}
	if((Epicrisis=="")&&(Requerido=="")){
		TipoRequerimiento="0";
		CrearCerrada(TipoRequerimiento);
		}
	if((Epicrisis=="")&&(Requerido!="")){
		TipoRequerimiento="1";
		CrearCerrada(TipoRequerimiento);
		}
	if((Epicrisis!="")&&(Requerido=="")){
		TipoRequerimiento="2";
		CrearCerrada(TipoRequerimiento);
		}
	if((Epicrisis!="")&&(Requerido!="")){
		TipoRequerimiento="3";
		CrearCerrada(TipoRequerimiento);
		}
}





















