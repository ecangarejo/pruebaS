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
			ajax.open("POST","PYP_ControlMedico",true); //getname will be the servlet name
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

function IngresarMedico(){
	var TipoDocumento=document.getElementById("cmbTipoDocumento").value;
	var NumeroDocumento=document.getElementById("txtNumeroDocumento").value;
	var Nombres=document.getElementById("txtNombres").value;
	var Apellidos=document.getElementById("txtApellidos").value;
	var TarjetaProfesional=document.getElementById("txtTarjetaProfesional").value;
	var Profesion=document.getElementById("txtProfesion").value;
	var Especialidad=document.getElementById("cmbEspecialidad").value;
	var Direccion=document.getElementById("txtDireccion").value;
	var Telefono=document.getElementById("txtTelefono").value;	
	var TiempoMedico=document.getElementById("txtTiempoMedico").value;
	if(TipoDocumento=="Seleccione"){
		alert("Seleccione Un Tipo De Documento.");
	}
	if(NumeroDocumento==""){
		alert("Digite el Numero de Documento.");
	}
	if(Nombres==""){
		alert("Digite el Nombre del Medico.");
	}
	if(Apellidos==""){
		alert("Digite los Apellidos del Medico.");
	}
	if(TarjetaProfesional==""){
		alert("Digite la Tarjeta Profesional del Medico.");
	}
	if(Profesion==""){
		alert("Digite la Profesion.");
	}
	if(Especialidad=="Seleccione"){
		alert("Seleccione Una Especialidad.");
	}
	if(Direccion==""){
		alert("Digite la Direccion.");
	}
	if(Telefono==""){
		alert("Digite el Telefono.");
	}	
	if((TipoDocumento!="Seleccione")&& (NumeroDocumento!="")&&(Nombres!="")&&(Apellidos!="")&&(TarjetaProfesional!="")&&(Profesion!="")&&(Especialidad!="Seleccione")&&(Direccion!="")&&(Telefono!="")){
		ajax=getXMLObject();
		ajax.open("POST","PYP_ControlMedico",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var validar=ajax.responseText;
				if(validar=="Esta Medico ya Esta Existe."){
					alert(validar);
					}
				else{
					alert(validar);
					window.location.reload();
					}
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento+"&Nombres="+Nombres+"&Apellidos="+Apellidos+"&TarjetaProfesional="+TarjetaProfesional+"&Profesion="+Profesion+"&Especialidad="+Especialidad+"&Direccion="+Direccion+"&Telefono="+Telefono+"&TiempoMedico="+TiempoMedico);
	}
}

function BuscarMedico(){
	var TipoDocumento=document.getElementById("cmbTipoDoc").value;
	var NumeroDocumento=document.getElementById("txtNumDocu").value;
	var ActuMedi=document.getElementById("ActuMedi");
	if(TipoDocumento=="Seleccione"){
		alert("Seleccione Un Tipo De Documento.");
	}
	if(NumeroDocumento==""){
		alert("Digite el Numero de Documento.");
	}
	if((TipoDocumento!="Seleccione")&& (NumeroDocumento!="")){
		ajax=getXMLObject();
		ajax.open("POST","PYP_ControlMedico",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var actualizar=ajax.responseText;
				ActuMedi.innerHTML=actualizar;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento);
	
		
	}
	
}

function ActualizarMedico(){
	var Codigo=document.getElementById("txtCodigoMedico").value;
	var TipoDocumento=document.getElementById("cmbTipoDocumento").value;
	var NumeroDocumento=document.getElementById("txtNumeroDocumento").value;
	var Nombres=document.getElementById("txtNombres").value;
	var Apellidos=document.getElementById("txtApellidos").value;
	var TarjetaProfesional=document.getElementById("txtTarjetaProfesional").value;
	var Profesion=document.getElementById("txtProfesion").value;
	var Especialidad=document.getElementById("cmbEspecialidad").value;
	var Direccion=document.getElementById("txtDireccion").value;
	var Telefono=document.getElementById("txtTelefono").value;
	var TiempoMedico=document.getElementById("txtTiempoMedico").value;
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlMedico",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;			
			alert(validar);
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento+"&Nombres="+Nombres+"&Apellidos="+Apellidos+"&TarjetaProfesional="+TarjetaProfesional+"&Profesion="+Profesion+"&Especialidad="+Especialidad+"&Direccion="+Direccion+"&Telefono="+Telefono+"&Codigo="+Codigo+"&TiempoMedico="+TiempoMedico);

}