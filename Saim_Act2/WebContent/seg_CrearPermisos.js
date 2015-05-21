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
			var val=radioButtons[x].id; //   
			ajax.open("POST","ControlCrearPermisos",true); //getname will be the servlet name
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

function CrearMenu(){
	var NombreMenu=document.getElementById("txtNombreMenu").value;
	var UrlMenu=document.getElementById("txtUrlMenu").value;
	if(NombreMenu==""){alert("Ingrese El Nombre del Menu.");}
	if(UrlMenu==""){alert("Ingrese la Url del Menu.");}
	if((NombreMenu!="")&&(UrlMenu!="")){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearPermisos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			if(validar!=""){alert(validar);}
			if(validar==""){alert("Menu Creado Con Exito.");window.location.reload();}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1.1&NombreMenu="+NombreMenu+"&UrlMenu="+UrlMenu);
	}
}

function VerOpcionesMenu(){
	var CodigoMenuFk=document.getElementById("cmbMenu").value;
	var OPmenu=document.getElementById('OpMenu');	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearPermisos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			OPmenu.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.1&CodigoMenuFk="+CodigoMenuFk);
}

function CrearOpMenu(){
	var CodigoMenuFk=document.getElementById("cmbMenu").value;
	var NombreOpcionesMenu=document.getElementById("txtNombreOpcion").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearPermisos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			if(validar!=""){alert(validar);}
			if(validar==""){alert("Opcion de Menu Creado Con Exito.");
			VerOpcionesMenu();
			//window.location.reload();
			}	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.2&CodigoMenuFk="+CodigoMenuFk+"&NombreOpcionesMenu="+NombreOpcionesMenu);
}

function CambioOpcionesMenu(){
	var CodigoMenuFk=document.getElementById("cmbMenu").value;
	var OpcionesMenu=document.getElementById('OpcionesMenu');	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearPermisos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			OpcionesMenu.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3.1&CodigoMenuFk="+CodigoMenuFk);
}

function VerOpcionesDisponibles(){
	var CodigoOpMenuFk=document.getElementById("cmbOpMenu").value;
	var CodigoMenuFk=document.getElementById("cmbMenu").value;
	var OpDisponibles=document.getElementById('OpDisponibles');	
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearPermisos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			OpDisponibles.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3.2&CodigoOpMenuFk="+CodigoOpMenuFk+"&CodigoMenuFk="+CodigoMenuFk);
}

function CrearOpDisponible(){
	var CodigoOpMenuFk=document.getElementById("cmbOpMenu").value;
	var NombreOpcionDisponible=document.getElementById("txtOpcionDisponible").value;
	var UrlOpcionesMenu=document.getElementById("txtUrlOpDisponible").value;	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearPermisos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			if(validar!=""){alert(validar);}
			if(validar==""){alert("Opcion Disponible Creada Con Exito.");
			VerOpcionesDisponibles();
			//window.location.reload();
			}	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3.3&CodigoOpMenuFk="+CodigoOpMenuFk+"&NombreOpcionDisponible="+NombreOpcionDisponible+"&UrlOpcionesMenu="+UrlOpcionesMenu);
	
}
