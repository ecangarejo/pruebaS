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




/*****************************************************/
function CargarExamenes(){	
	ajax=getXMLObject();
	ajax.open("POST","ControlImagenologiasPendientes",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			document.getElementById('Opcion').innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5"); //Posting txtname to Servlet
}

function ExaPabellon(){
	 var NomSubArea=document.getElementById("cmbServicio").value;
	 if(NomSubArea=="Seleccione"){
		 alert("Seleccione El Servicio.");
	 }
	 if(NomSubArea!="Seleccione"){
		 ajax=getXMLObject();
			ajax.open("POST","ControlImagenologiasPendientes",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('Opcion').innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=exaPabellon"+"&pabellon="+NomSubArea); //Posting txtname to Servlet
	 }
}

function exaPaciente(codPaciente){
	 
		 ajax=getXMLObject();
			ajax.open("POST","ControlImagenologiasPendientes",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('Opcion').innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=exaPac"+"&codPac="+codPaciente); //Posting txtname to Servlet
	 
}


function BuscarDatosPaciente(){
	 var TipoDocumento=document.getElementById("cmbTipoDoc").value; 
	 var NumeroDocumento=document.getElementById("txtNumDocumento").value; 
	 var PrimerApellido=document.getElementById("txtPrimerApellido").value;
	 var SegundoApellido=document.getElementById("txtSegundoApellido").value;
	 var Nombre=document.getElementById("txtNombre").value;

		 if((NumeroDocumento=="")&&(TipoDocumento=="Seleccione")&&(PrimerApellido=="")&&(SegundoApellido=="")&&(Nombre=="")){
			 alert("Seleccione algun criterio de busqueda.")
		 }else{
			 ajax=getXMLObject();
			 ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
			 ajax.onreadystatechange=function() {
				 if (ajax.readyState==4) {
					 document.getElementById('resultado').innerHTML = ajax.responseText;
				 }
			 }
			 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			 ajax.send("valor=BDP&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento+"&PrimerApellido="+PrimerApellido+"&SegundoApellido="+SegundoApellido+"&Nombre="+Nombre); //Posting txtname to Servlet
		 }
	 
}


 function BuscarHojaAdmision(){
	 var TipoDocumento=document.getElementById("cbafiliacion").value; 
	 var NumeroDocumento=document.getElementById("txtnumdoc").value; 
	 
	 if(NumeroDocumento==""){
		alert("Escriba Numero Documento."); 
	 }else{
		 ajax=getXMLObject();
		 ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
		 ajax.onreadystatechange=function() {
			 if (ajax.readyState==4) {
				 document.getElementById('Admisiones').innerHTML = ajax.responseText;
			 }
		 }
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=2&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento); //Posting txtname to Servlet
	 }
 }
 
 
 function mostarAdmision(CodAdmision) {		
		pagina="adm_ReporteAdmision.jsp?cod="+CodAdmision			
	   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	   	window.open(pagina,"NUEVA",opciones);		
	}
 
 
 
 function Radio() {
		ajax=getXMLObject();
		var contenido=document.getElementById('Opcion');
		var radioButtons = document.getElementsByName("radiobutton");
		for (var x = 0; x < radioButtons.length; x ++) {
			if (radioButtons[x].checked) {
				var val=radioButtons[x].id;    
				ajax.open("POST","ControlImagenologiasPendientes",true); //getname will be the servlet name
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
 
