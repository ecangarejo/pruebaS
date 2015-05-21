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
function Buscarusuario(){
	var formularioImagen=document.getElementById('SubirImagen');
	var Identificacion=document.getElementById('txtNumeroDocumento').value;
	ajax=getXMLObject();
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			formularioImagen.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Imagen&Identificacion="+Identificacion);
}

function Radio() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
	var CodigoUsuario=document.getElementById("txtCodigoUsuario").value;
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id; //   
			ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&CodigoUsuario="+CodigoUsuario); //Posting txtname to Servlet
		}
	}
}


function BuscarUsuario(){
	var formatos=document.getElementById('formatos');
	var Identificacion=document.getElementById('txtIdentificacion').value;
	ajax=getXMLObject();
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			formatos.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Buscar&Identificacion="+Identificacion);
}

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

function RelacionFormatos(){
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
	var CodigoUsuario=document.getElementById("txtCodigoUsuario").value;
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Hosp&CodigoUsuario="+CodigoUsuario); //Posting txtname to Servlet
}

function GuardarRelacion(CodigoUsuario,CodigoFormato){
	ajax=getXMLObject();
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionFormatos();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Guardar&CodigoUsuario="+CodigoUsuario+"&CodigoFormato="+CodigoFormato); //Posting txtname to Servlet    
}

function GuardarRelacionFormatos(){	
	var c=document.getElementById('txtConDisponibles').value;
	var CodigoUsuario=document.getElementById('txtCodigoUsuario').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkDisponible[i].checked) {
			CodigoFormato=document.form1.chkDisponible[i].value;
			GuardarRelacion(CodigoUsuario,CodigoFormato);
			oscar--;
			}
		}
	}
	else{
		CodigoFormato=document.form1.chkDisponible.value;
		GuardarRelacion(CodigoUsuario,CodigoFormato);
	}
}

function OmitirRelacion(CodigoAsignacion){
	ajax=getXMLObject();
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionFormatos();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Omitir&CodigoAsignacion="+CodigoAsignacion); //Posting txtname to Servlet    
}
function OmitirPermisoFormato(){
	var c=document.getElementById('txtContAutorizadas').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkAutorizadas[i].checked) {
				CodigoAsignacion=document.form1.chkAutorizadas[i].value;
				OmitirRelacion(CodigoAsignacion);
				oscar--;
			}
		}
	}
	else{
		CodigoAsignacion=document.form1.chkAutorizadas.value;
		OmitirRelacion(CodigoAsignacion);
	}
}

function RelacionFormatosCE(){
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
	var CodigoUsuario=document.getElementById("txtCodigoUsuario").value;
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Cex&CodigoUsuario="+CodigoUsuario); //Posting txtname to Servlet
}


function OmitirRelacionCE(CodigoAsignacion){
	ajax=getXMLObject();
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionFormatosCE();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmitirCE&CodigoAsignacion="+CodigoAsignacion); //Posting txtname to Servlet    

}

function OmitirPermisoFormatoCE(){
	var c=document.getElementById('txtContAutorizadasCE').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkAutorizadasCE[i].checked) {
				CodigoAsignacion=document.form1.chkAutorizadasCE[i].value;
			OmitirRelacionCE(CodigoAsignacion);
			oscar--;
			}
		}
	}
	else{
		CodigoAsignacion=document.form1.chkAutorizadasCE.value;
		OmitirRelacionCE(CodigoAsignacion);
	}
}


function GuardarRelacionCE(CodigoUsuario,CodigoFormato){
	ajax=getXMLObject();
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionFormatosCE();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GuardarCE&CodigoUsuario="+CodigoUsuario+"&CodigoFormato="+CodigoFormato); //Posting txtname to Servlet    
}

function GuardarRelacionFormatosCE(){	
	var c=document.getElementById('txtConDisponiblesCE').value;
	var CodigoUsuario=document.getElementById('txtCodigoUsuario').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkDisponibleCE[i].checked) {
			CodigoFormato=document.form1.chkDisponibleCE[i].value;
			GuardarRelacionCE(CodigoUsuario,CodigoFormato);
			oscar--;
			}
		}
	}
	else{
		CodigoFormato=document.form1.chkDisponibleCE.value;
		GuardarRelacionCE(CodigoUsuario,CodigoFormato);
	}
}


function RelacionPermisoHC(){
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
	var CodigoUsuario=document.getElementById("txtCodigoUsuario").value;
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Phic&CodigoUsuario="+CodigoUsuario); //Posting txtname to Servlet
}


function GuardarRelacionHC(CodigoUsuario,CodigoPermiso){
	ajax=getXMLObject();
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionPermisoHC();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GuardarHC&CodigoUsuario="+CodigoUsuario+"&CodigoPermiso="+CodigoPermiso); //Posting txtname to Servlet    
}

function GuardarRelacionPermisoHC(){	
	var c=document.getElementById('txtConDisponiblesHC').value;
	var CodigoUsuario=document.getElementById('txtCodigoUsuario').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkDisponibleHC[i].checked) {
			CodigoPermiso=document.form1.chkDisponibleHC[i].value;
			GuardarRelacionHC(CodigoUsuario,CodigoPermiso);
			oscar--;
			}
		}
	}
	else{
		CodigoPermiso=document.form1.chkDisponibleHC.value;
		GuardarRelacionHC(CodigoUsuario,CodigoPermiso);
	}
}

function OmitirRelacionHC(CodigoAsignacion){
	ajax=getXMLObject();
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionPermisoHC();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmitirHC&CodigoAsignacion="+CodigoAsignacion); //Posting txtname to Servlet    

}

function OmitirPermisoHC(){
	var c=document.getElementById('txtContAutorizadasHC').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkAutorizadasHC[i].checked) {
				CodigoAsignacion=document.form1.chkAutorizadasHC[i].value;
			OmitirRelacionHC(CodigoAsignacion);
			oscar--;
			}
		}
	}
	else{
		CodigoAsignacion=document.form1.chkAutorizadasHC.value;
		OmitirRelacionHC(CodigoAsignacion);
	}
}

function guardarcomo()
{
var WebBrowser = '<OBJECT ID="WebBrowser1" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>';
document.body.insertAdjacentHTML('beforeEnd', WebBrowser); 
WebBrowser1.ExecWB(4, 2);
WebBrowser1.outerHTML = "";
}

