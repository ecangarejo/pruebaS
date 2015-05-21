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
String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ''); };
//////////////////////////////////////////////////////////////////////////////////////////////////////////
function A(tecla, e){
	tecla = e.keyCode; e.which;
	if(tecla == 13){
		Ingresar();
	}
}

function B(tecla, e){
	tecla = e.keyCode; e.which;
	if(tecla == 13){
		Actualizar();
	}
}

function Radio() {
	var ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x=0; x<radioButtons.length; x++) {
		if (radioButtons[x].checked) {
			var val = radioButtons[x].value;
			ajax.open("POST", "ControlCrearEspecialidad", true);
			ajax.onreadystatechange = function(){
				if (ajax.readyState == 4) {
					if(ajax.status == 200) {
						contenido.innerHTML = ajax.responseText;
					}else{
						alert("Errores during AJAX call. Please try again");
					}
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val);
		}
	}
}

function Ingresar(){
	var desc = document.getElementById("Descripcion").value.trim();
	
	if(desc == ""){
		alert("Digite la descripci\xF3n");
		document.getElementById("Descripcion").focus();
		document.getElementById("Descripcion").select();
	}else{
		var ajax = getXMLObject();
		ajax.open("POST", "ControlCrearEspecialidad", true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				if(ajax.responseText == "Ingreso exitoso"){
					/*document.getElementById("Opcion").innerHTML = "";
					document.getElementById("1").checked = false;*/
					document.getElementById("Descripcion").value = "";
					document.getElementById("Descripcion").focus();
				}
				alert(ajax.responseText);
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Ingresar&desc="+encodeURIComponent(desc));
	}
}

function Buscar(){
	var contenido = document.getElementById('objeto');
	var cod = document.getElementById("lista").value.trim();
	if(cod != "Seleccione"){
		var ajax = getXMLObject();
		ajax.open("POST", "ControlCrearEspecialidad", true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				contenido.innerHTML = ajax.responseText;	
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ver&cod="+cod);
	}else{
		contenido.innerHTML = "";
	}
}

function Actualizar(){
	var cod = document.getElementById("lista").value.trim();
	var desc = document.getElementById("Descripcion").value.trim();
	
	if(desc == ""){
		alert("Digite la descripci\xF3n");
		document.getElementById("Descripcion").focus();
		document.getElementById("Descripcion").select();
	}else{
		var ajax = getXMLObject();
		ajax.open("POST", "ControlCrearEspecialidad", true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				if(ajax.responseText == "Especialidad actualizada exitosamente"){
					document.getElementById("Opcion").innerHTML = "";
					var xmlhttp1 = getXMLObject();
					xmlhttp1.open("POST", "ControlCrearEspecialidad", true);
					xmlhttp1.onreadystatechange = function(){
						if (xmlhttp1.readyState == 4) {
							if(xmlhttp1.status == 200) {
								document.getElementById("Opcion").innerHTML = xmlhttp1.responseText;
							}
						}
					};
					xmlhttp1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					xmlhttp1.send("valor=ActualizarRb");
				}
				alert(ajax.responseText);
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Actualizar&desc="+encodeURIComponent(desc)+"&cod="+cod);
	}
}