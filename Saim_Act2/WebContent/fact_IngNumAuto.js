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
	var contenido = document.getElementById('contenido');
	var usuario=document.getElementById('txtCodusuario').value;
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x=0; x<radioButtons.length; x++) {
		if (radioButtons[x].checked) {
			var val = radioButtons[x].value;
			ajax.open("POST", "ControlIngNumAuto", true);
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
			//alert("valor="+val+"&user="+usuario);
			ajax.send("valor="+val+"&user="+usuario);
		}
	}
}


function BuscarDat(user){
	var ajax = getXMLObject();
	//alert("entrando");
	var Adm=document.getElementById('Admi').value;
	var cont=document.getElementById('datos');
	//alert("Buscar "+Adm);
	ajax.open("POST", "ControlIngNumAuto", true);
	ajax.onreadystatechange = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BAdm&Adm="+Adm+"&user="+user);
	
}


function BuscarDatFact(user){
	var ajax = getXMLObject();
	//alert("entrando");
	var Fact=document.getElementById('consec').value;
	var cont=document.getElementById('datos');
	//alert("Buscar "+Adm);
	ajax.open("POST", "ControlIngNumAuto", true);
	ajax.onreadystatechange = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BFact&Fact="+Fact+"&user="+user);
	
}

function Edit(Adm,Ent,user){
	var ajax = getXMLObject();
	var cont= document.getElementById('auto');
	ajax.open("POST", "ControlIngNumAuto", true);
	ajax.onreadystatechange = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Edit&Adm="+Adm+"&Ent="+Ent+"&user="+user);
}


function EditD(user,enca){
	//alert("EditD"+enca);
	var ajax = getXMLObject();
	var Fact=document.getElementById('conseF').value;
	var cont= document.getElementById('auto');
	ajax.open("POST", "ControlIngNumAuto", true);
	ajax.onreadystatechange = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=EditF&user="+user+"&enca="+enca+"&Fact="+Fact);
}

function Guardar(Adm, Ent,user){
	//alert("Valores de Adm="+Adm+" Valor de Entidad ="+Ent);
	var ajax = getXMLObject();
	var contenido=document.getElementById('datos');
	var cont=document.getElementById('auto');
	var noauto=document.getElementById('NumAuto').value;
	//alert("valor"+noauto);
	if(noauto==""){
		alert("No ha digitado el numero de Autorizacion");
	}else{
		ajax.open("POST", "ControlIngNumAuto", true);
		ajax.onreadystatechange = function(){
			if (ajax.readyState == 4) {
				//alert("Ingreso Exitoso !");
				//cont.innerHTML="";
				//alert(ajax.responseText);
				var prueba=ajax.responseText;
				if(prueba!=""){
					var opcion=confirm(ajax.responseText);
					if(opcion){
						ModificarFactAuto(Adm,Ent,user,noauto);
					//alert("modificar fact");
					}else{
						alert("Numero de Autorización Actualizado !!");
						window.location.reload();
					}
				}else{
					alert("Numero de Autorización Actualizado !");
					window.location.reload();
				}
				//contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GuardarAuto&Adm="+Adm+"&Ent="+Ent+"&noauto="+noauto+"&user="+user);
	}
}

function GuardarF(user,enca){
	//alert("enca");
	var ajax = getXMLObject();
	var noauto=document.getElementById('NumAuto').value;
	//alert("valor"+noauto); 
	if(noauto==""){
	alert("No ha digitado el numero de Autorizacion");
	}else{
		ajax.open("POST", "ControlIngNumAuto", true);
		ajax.onreadystatechange = function(){
			if (ajax.readyState == 4) {
				alert(ajax.responseText);
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GuardarAutoF&user="+user+"&enca="+enca+"&noauto="+noauto);
	}
}

function ModificarFactAuto(Adm,Ent,user,noauto){
	var ajax = getXMLObject();
	ajax.open("POST", "ControlIngNumAuto", true);
	ajax.onreadystatechange = function(){
		if (ajax.readyState == 4) {
			alert("Numero de Autorización Actualizado !!!");
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GuardarAutoFact&Adm="+Adm+"&Ent="+Ent+"&noauto="+noauto+"&user="+user);
	
}
function Ingresar(){
	alert("Ingresar ");
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