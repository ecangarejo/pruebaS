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


function TrasladarAmbHosp(CodAdmision,Codigocama){
	
	var fecha=document.getElementById('txtFecha').value
	var hora=document.getElementById('txtHora').value;
	var usuario=document.getElementById('txtUsuario').value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	var CodPac=document.getElementById("txtCodPac").value;
	var NomUsuario=document.getElementById("txtNomUsuario").value;
	var cmbCama=document.getElementById("cmbCama").value;
	//window.location.href="ControlAdmiFinal?usuario="+usuario+"&hora="+hora+"&numingreso="+CodAdmision+"&numcama="+Codigocama+"&fecha="+fecha,true;
		//alert("valor=1AH&usuario="+usuario+"&hora="+hora+"&numingreso="+CodAdmision+"&numcama="+Codigocama+"&fecha="+fecha+"&CodDiagnostico="+CodDiagnostico+"&CodPac="+CodPac+"&NomUsuario="+NomUsuario+"&CamaNueva="+cmbCama); //Posting txtname to Servlet
		if(CodDiagnostico==""){
		alert("Seleccione Un Diagnostico.");
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlAdmiFinal",true); //getname will be the servlet name
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
				//document.getElementById('Finalizar').innerHTML = ajax.responseText;
				//alert("Alta Realizada Satisfactoriamente.");
				alert(ajax.responseText);
				//mostarAlta (CodAdmision,Codigocama);
				//Reporte(ajax.responseText);
				//window.location.href='adm_Traslado_Ambulatorio_Hospitalizacion.jsp';
			}
		}	
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1AH&usuario="+usuario+"&hora="+hora+"&numingreso="+CodAdmision+"&numcama="+Codigocama+"&fecha="+fecha+"&CodDiagnostico="+CodDiagnostico+"&CodPac="+CodPac+"&NomUsuario="+NomUsuario+"&CamaNueva="+cmbCama); //Posting txtname to Servlet
	}
}

function Reporte(validar) {	
	pagina="adm_ReporteAdmision.jsp?cod="+validar;		
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}

function VerCama(){
	var CodSubArea=document.getElementById("cmbArea").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			//alert(ajax.responseText);
			document.getElementById('Cama').innerHTML = ajax.responseText;
			//
			//window.location.href='adm_Final_Admision_Ambulatoria.jsp';
			//mostarAlta (CodAdmision,Codigocama);
		}
	}	
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6.3&CodSubArea="+CodSubArea); //Posting txtname to Servlet

}



function DarAltaAmbulatoria(CodAdmision,Codigocama){
	
	var fecha=document.getElementById('txtFecha').value
	var hora=document.getElementById('txtHora').value;
	var usuario=document.getElementById('txtUsuario').value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	/*******/
	var CodDiagnosticoEgreso=document.getElementById("txtCodDiagnosticoEgreso").value;
	var finalidadcons=document.getElementById("cmbfinalidadcons").value;
	var causaexterna=document.getElementById("cmbcausaexterna").value;
	var tipdiag=document.getElementById("cmbtipdiag").value;
	var EstSalida=document.getElementById("EstSalida").value;
	var TipoTras=document.getElementById("TipoTras").value;
	/*******/
	var CodPac=document.getElementById("txtCodPac").value;
	//window.location.href="ControlAdmiFinal?usuario="+usuario+"&hora="+hora+"&numingreso="+CodAdmision+"&numcama="+Codigocama+"&fecha="+fecha,true;
	if((CodDiagnostico=="")||(CodDiagnosticoEgreso=="")){
		alert("Falta diligenciar algun diagnostico.");
	}else{
		if(TipoTras=="Seleccione"){
			alert("Seleccione Tipo de salida");
			document.getElementById("TipoTras").focus();
		}else{
			if(EstSalida=="Seleccione"){
				alert("Seleccione Estado de la salida.");
				document.getElementById("EstSalida").focus();
			}else{
				ajax=getXMLObject();
				ajax.open("POST","ControlAdmiFinal",true); //getname will be the servlet name
				ajax.onreadystatechange=function() {
					if (ajax.readyState==4) {
						//document.getElementById('Finalizar').innerHTML = ajax.responseText;
						alert("Alta Realizada Satisfactoriamente.");
						window.location.href='adm_Final_Admision_Ambulatoria.jsp';
						//mostarAlta (CodAdmision,Codigocama);
					}
				}	
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=1&usuario="+usuario+"&hora="+hora+"&numingreso="+CodAdmision+
						"&numcama="+Codigocama+"&fecha="+fecha+"&CodDiagnostico="+CodDiagnostico+
						"&CodPac="+CodPac+"&CodDiagnosticoEgreso="+CodDiagnosticoEgreso+"&finalidadcons="+finalidadcons+
						"&causaexterna="+causaexterna+"&tipdiag="+tipdiag+"&EstSalida="+EstSalida+"&TipoTras="+TipoTras); //Posting txtname to Servlet
			}
		}
	}
}
	/*function mostarAlta (CodAdmision,Codigocama) {		
		pagina="adm_ReporteAltaPaciente.jsp?CodAdm="+CodAdmision+"&CodCama="+Codigocama;			
	   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	   	window.open(pagina,"NUEVA",opciones);		
	}*/
 
 function BuscarAdmision(){
	 var TipoDocumento=document.getElementById("cmbTipoDocumento").value; 
 	 var NumeroDocumento=document.getElementById("txtIdentificacion").value; 
 	 if(NumeroDocumento==""){
 		alert("Escriba Numero Documento."); 
 	 }else{
 		 ajax=getXMLObject();
 		 ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
 		 ajax.onreadystatechange=function() {
 			 if (ajax.readyState==4) {
 				 document.getElementById('Finalizar').innerHTML = ajax.responseText;
 			 }
 		 }	
 		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 		 ajax.send("valor=3&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento); //Posting txtname to Servlet
 	 }
 }
 
 function DarAltaAm(CodAdmision,Codigocama){
	 	var fecha=document.getElementById('txtFecha').value
	 	var hora=document.getElementById('txtHora').value;
	 	var usuario=document.getElementById('txtUsuario').value;
		/* ajax=getXMLObject();
 		 ajax.open("POST","ControlAdmiFinal",true); //getname will be the servlet name
 		 ajax.onreadystatechange=function() {
 			 if (ajax.readyState==4) {
 				 //document.getElementById('Finalizar').innerHTML = ajax.responseText;
 				mostarAlta (CodAdmision,Codigocama);
 			 }
 		 }	
 		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 		 ajax.send("valor=DAA&numingreso="+CodAdmision+"&numcama="+Codigocama);*/ //Posting txtname to Servlet

	 	
		window.location.href="ControlAdmiFinal?usuario="+usuario+"&hora="+hora+"&numingreso="+CodAdmision+"&numcama="+Codigocama+"&fecha="+fecha,true;
		alert("Alta Realizada Satisfactoriamente.");
		window.location.href='adm_Final_Admision_Ambulatoria.jsp';
		
		/**
		formato de salida
		*/

}
 
 function AnularAdmision(){
	 var opcion=confirm("Desea Anular Esta Admision?");
	 if(opcion){
		 var CodigoAdmision=document.getElementById("txtCodigoAdmision").value; 
		 var CodigoCama=document.getElementById("txtCodigoCama").value; 
		 var Hora=document.getElementById("txtHora").value; 
		 var Fecha=document.getElementById("txtFecha").value; 
		 var CodigoUsuario=document.getElementById("txtUsuario").value; 
		 var txtMotivoAnulacion=document.getElementById("txtMotivoAnulacion").value;
		 if(txtMotivoAnulacion==""){
			 alert("Escriba el Motivo de la Anulacion.");
			 document.getElementById("txtMotivoAnulacion").focus();
		 }else{
			 ajax=getXMLObject();
			 ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
			 ajax.onreadystatechange=function() {
				 if (ajax.readyState==4) {
					 var validar=ajax.responseText;
					 if(validar==""){
						 alert("Admision Anulada Con Exito.");
						 window.location.reload();
					 }
					 if(validar!=""){
						 alert(validar)
					 }
				 }
			 }	
			 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			 ajax.send("valor=3.1&CodigoAdmision="+CodigoAdmision+"&CodigoCama="+CodigoCama+"&Hora="+Hora+"&Fecha="+Fecha+"&CodigoUsuario="+CodigoUsuario+"&txtMotivoAnulacion="+encodeURIComponent(txtMotivoAnulacion)); //Posting txtname to Servlet
			// alert("valor=3.1&CodigoAdmision="+CodigoAdmision+"&CodigoCama="+CodigoCama+"&Hora="+Hora+"&Fecha="+Fecha+"&CodigoUsuario="+CodigoUsuario+"&txtMotivoAnulacion="+encodeURIComponent(txtMotivoAnulacion)); //Posting txtname to Servlet
		 }
	 }
	 else{
		 
	 }
 }

 function fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	  document.getElementById('txtFecha').value = temp1
	  id = setTimeout("fecha()",1000)
	  }

 function hora(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txtHora').value = temp;
	  id = setTimeout("hora()",1000)
	  }

 

 
 function DarAlta(CodAdmision,Codigocama){

 	var fecha=document.getElementById('txtFecha').value
 	var hora=document.getElementById('txtHora').value;
 	var usuario=document.getElementById('txtUsuario').value;
	window.location.href="ControlAdmiFinal?usuario="+usuario+"&hora="+hora+"&numingreso="+CodAdmision+"&numcama="+Codigocama+"&fecha="+fecha,true;
	alert("Alta Realizada Satisfactoriamente.");
	mostarAlta (CodAdmision,Codigocama);
	/**
	formato de salida
	*/

	 }
	function mostarAlta (CodAdmision,Codigocama) {		
		pagina="adm_ReporteAltaPaciente.jsp?CodAdm="+CodAdmision+"&CodCama="+Codigocama;			
	   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	   	window.open(pagina,"NUEVA",opciones);		
	}