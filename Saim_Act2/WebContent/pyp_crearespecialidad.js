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
			ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
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

function GuardarRelPerEsp(){
	var CodigoPerfil=document.getElementById("cmbNomPerfil").value;
	var CodigoEspecialidad=document.getElementById("cmbNomEspecialidad").value;
	if(CodigoPerfil=="Seleccione"){
		alert("Seleccione Perfil.");
		document.getElementById("cmbNomPerfil").focus();
	}else{
		if(CodigoEspecialidad=="Seleccione"){
			alert("Seleccione Especialidad.");
			document.getElementById("cmbNomEspecialidad").focus();
		}else{
			ajax=getXMLObject();
			ajax.open("POST","PYP_ControlEspecialidades",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					alert(ajax.responseText);	
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=GPE&CodigoEspecialidad="+CodigoEspecialidad+"&CodigoPerfil="+CodigoPerfil);
		}
	}
}

function BuscarEspecialidad(){
	var CodigoEspecialidad=document.getElementById("cmbNomEspecialidad").value;
	var Actualizar=document.getElementById('ActuEspe');
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlEspecialidades",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			Actualizar.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&CodigoEspecialidad="+CodigoEspecialidad);
}
function ActualizarEspecialidad(){
	var CodEspe=document.getElementById("txtCodEspe").value;
	var NomEspeNuevo=document.getElementById("txtNomEspeNuevo").value;
	//var ValorEspeNuevo=document.getElementById("txtValorEspeNuevo").value;

	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlEspecialidades",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);	
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&CodEspe="+CodEspe+"&NomEspeNuevo="+NomEspeNuevo);
}

function IngresarEspecialidad(){
	var NomEspecialidad=document.getElementById("txtNomEspecialidad").value;
	//var ValorEspecialidad=document.getElementById("txtValorEspecialidad").value;
	if(NomEspecialidad==""){
		alert("El Nombre de la Especialidad No puede ir Vacio");
		}
	/*if(ValorEspecialidad==""){
		alert("El Precio de la Especialidad No puede ir Vacio");
		}*/


	if((NomEspecialidad!="")){
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlEspecialidades",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			if(validar=="Esta Especialidad ya Esta Creada."){
				alert(validar);
				}
			else{
				alert(validar);
				window.location.reload();
				}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&NomEspecialidad="+NomEspecialidad);
	}
}