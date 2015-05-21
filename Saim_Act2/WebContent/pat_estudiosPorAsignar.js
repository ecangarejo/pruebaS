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


function BuscarPaciente(idcodOrden){
	var TipoDocumento=document.getElementById("txttipodoc").value;
	var Identificacion=document.getElementById("txtNomUsu").value;
	alert (TipoDocumento+" "+Identificacion);
			
			window.location.href="pat_AsignarEstudio.jsp?Identificacion="+Identificacion+"&TipoDocumento="+TipoDocumento+"&CodOrden="+idcodOrden,true;

			
}

function mostrarOrdenPat(idcodOrden){
	
	pagina="ordenPatReporte.jsp?idcodOrden="+idcodOrden;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Orden_patologia",opciones);
}

