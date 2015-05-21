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
function Radio() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlCrearEspecialidad",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val); //Posting txtname to Servlet
		}	     
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////
function CrearTipoEspecialidad(){
	var NombreTipoEspecialidad=document.getElementById("txtTipoEspecialidad").value;	
	ajax=getXMLObject();
	if(NombreTipoEspecialidad==""){
		alert("El Nombre del Tipo de Especialidad No Puede Ir Vacio.\n Intente Otravez.");
	}
	if(NombreTipoEspecialidad!=""){
	ajax.open("POST","ControlCrearEspecialidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			alert(validar);
			CargarTipoEspecialidad()
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1.1&NombreTipoEspecialidad="+NombreTipoEspecialidad);
	}
}

function CargarTipoEspecialidad(){
	var contenido=document.getElementById('Opcion');
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearEspecialidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1");
}

function CrearEspecialidad(){
	var NombreEspecialidad=document.getElementById("txtNombreEspecialidad").value;
	var CodigoTipoEspecialidadFK=document.getElementById("cmbTipoEspecialidades").value;
	var CodigoRips=document.getElementById("txtCodigoRips").value;
	if(NombreEspecialidad==""){
		alert("Falta Escribir El Nombre De La Especialidad.");
		}else{
			if(CodigoTipoEspecialidadFK=="Seleccione"){
				alert("Seleccione El Tipo De Especialidad.");
				}else{
					if(CodigoRips==""){
						alert("Falta Escribir EL Codigo Rips.");
						}
				}
		} 
	
	
	if((NombreEspecialidad!="")&&(CodigoTipoEspecialidadFK!="Seleccione")&&(CodigoRips!="")){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearEspecialidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);	
			CargarEspecialidad();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.1&NombreEspecialidad="+NombreEspecialidad+"&CodigoTipoEspecialidadFK="+CodigoTipoEspecialidadFK+"&CodigoRips="+CodigoRips);
	}
}

function CargarEspecialidad(){
	var contenido=document.getElementById('Opcion');
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearEspecialidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2");
}

function VerEspecialidadesCreadas(){
	var EspCreadas=document.getElementById('EspCreadas');
	var CodigoTipoEspecialidadFK=document.getElementById("cmbTipoEspecialidades").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearEspecialidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			EspCreadas.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.2&CodigoTipoEspecialidadFK="+CodigoTipoEspecialidadFK);
}