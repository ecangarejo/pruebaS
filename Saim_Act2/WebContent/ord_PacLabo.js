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
///////////////////////////////////////////////////////////////////////////////////

function Radio() {	
if(xmlhttp) { 
	 var radioButtons = document.getElementsByName("radiobutton");
      for (var x = 0; x < radioButtons.length; x ++) {
        if (radioButtons[x].checked) {
	         var val=radioButtons[x].id;    
	      
	    xmlhttp.open("POST","ControlListarPacientes?valor="+val,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = MostrarRadio;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
		  }	     
    }
 }
}
function MostrarRadio() {
if (xmlhttp.readyState == 4) {		
	if(xmlhttp.status == 200) {		
		opciones.innerHTML = xmlhttp.responseText		
     }
     else {
        alert("Error during AJAX call. Please try again,Radio");
     }
   }
}

///////////////////////////////////////////////////////////////////////////////////
function ComprobarAdmisiones() {
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var lista=xmlhttp.responseText;
			document.getElementById('sugerencias').innerHTML = lista;
	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }
	}
function Pacientes(){
	if(xmlhttp) { 	
		xmlhttp.open("POST","ControlListarPacientes?valor=0",true); //getname will be the servlet name
		xmlhttp.onreadystatechange  = ComprobarAdmisiones;
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send(""); //Posting txtname to Servlet
	}
}
//////////////////////////////////////////////////////////////////////////////////
function ComprobarOrden() {
	
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var lista=xmlhttp.responseText;
			document.getElementById('formulario').innerHTML = lista;
	     }
	     else {
	        alert("Error during AJAX call,ComprobarOrden. Please try again");
	     }
	   }
	}
function AsignarOrdenLab(CodPac){
	
	if(xmlhttp) { 	
	    xmlhttp.open("POST","ControlListarPacientes?valor=3&CodPac="+CodPac,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarOrden;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	   var x;
}
////////////////////////////////////////////////////////////////////////
function PacienteOrdenLab(CodPac){
	window.location.href="ord_AsignarLaboratorio.jsp?CodPac="+CodPac;
}
////////////////////////////////////////////////////////////////////////
function AsignarFormatos(CodAdm,CodPac){
	//window.location.href="hic_VerFormatos.jsp?CodPac="+CodPac+"&CodAdm="+CodAdm;
	window.location.href="hic_pestanas.jsp?CodPac="+CodPac+"&CodAdm="+CodAdm;
}
////////////////////////////////////////////////////////////////////////
function ComprobarExamenes() {
	
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var lista=xmlhttp.responseText;
			document.getElementById('examenes').innerHTML = lista;
	     }
	     else {
	        alert("Error during AJAX call,ComprobarExamenes. Please try again");
	     }
	   }
	} 

function VerExamenes(){
	var CodArea=document.getElementById("cmbgrupos").value;
	//alert(CodArea)
	if(xmlhttp) { 	
	    xmlhttp.open("POST","ControlListarPacientes?valor=4&CodArea="+CodArea,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarExamenes;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	   var x;
	
}
/////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////
//fin de la funcion 
////////////////////////////////////

  function Asignar(){	
	  
    var c=document.getElementById('yo').value;
	var oscar=c;
	
 if(c!=1){
	for(var i=0; i<=c-1; i++){	
      if (document.form1.ca[i].checked) {
       	 d=document.form1.ca[i].value;
       	 a=document.form1.txtTipo[i].value;
       	//alert("d "+d+" a "+a)
       	 enviar2(d,a);
       	// alert("Dentro el For "+d)
     }     	  
      oscar--; 
     }	
 } 
 else{
	 if(document.form1.ca.checked){
	 d=document.form1.ca.value;
	 a=document.form1.txtTipo.value;
	// alert("DEntro del else "+d+" a "+a)
	 enviar2(d,a);
	}
}
 }//fin de la funcion 
//////////////////////////////////
function enviar2(CodEstudio,TipoEstu){
	//alert("CodEstudio "+CodEstudio+" TipoEstu "+TipoEstu)
	var CodPac=document.getElementById("txtCodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	//alert("CodPac "+CodPac+" hora "+hora+" fecha "+fecha+" CodEstudio "+CodEstudio+" TipoEstu "+TipoEstu);
	if(CodEstudio==""){
		alert("Seleccione Un Estudio!!!")
		}
	if(TipoEstu==""){
		alert("No Existe El Tipo De Estudio!!!");
	}
	if(CodPac==""){
		alert("No Se Ha Seleccionado Paciente!!!");
	}
	if(hora==""){
		alert("No Se Ha Seleccionado Hora!!!");
	}
	if(fecha==""){
		alert("No Se Ha Seleccionado Fecha!!!");
	}
	if((CodEstudio!="")&&(TipoEstu!="")&&(CodPac!="")&&(hora!="")&&(fecha!="")){
	 getXMLObject();
	 var xmlhttp = new getXMLObject();
	if(xmlhttp) { 
		//var pregunta=document.getElementById("txtArea").value;
	    xmlhttp.open("POST","ControlListarPacientes?valor=5&CodPaciente="+CodPac+"&TipoEstudio="+TipoEstu+"&CodEstudio="+CodEstudio+"&hora="+hora+"&fecha="+fecha,true); //getname will be the servlet name
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet	
	}
	}
	
}
/////////////////////////////
function finalizar(){
	alert("Estudios Asignados Exitosamente!!!");
	window.location.reload();
	
}
/////////////////////////////////////////////////////////////////////
function enviar(){
	  getXMLObject();
		  var xmlhttp = new getXMLObject();
	  if(xmlhttp) { 
		var usuario=document.getElementById("txtNomUsu").value;
		var codPaciente=document.getElementById("txtCodPac").value;	
		var codExamen=document.getElementById("txtcodexamen").value;
		var hora=document.getElementById("txtHora").value;
		var fecha=document.getElementById("txtFecha").value;	
		var datosClinico=document.getElementById('txtdatosClinicos').value;
		var portatil="";
		if(document.getElementById("chkportatil").checked){
			 portatil=document.getElementById("chkportatil").value;
			 //alert(portatil);
			}
		if(codPaciente==""){
			alert("No se Ha Seleccionado Ningun Paciente");
			}
		if(codExamen==""){
			alert("No se Ha Seleccionado Ningun Examen");
			}
		if(hora==""){
			alert("No hay Hora Seleccionado");
			}
		if(fecha==""){
			alert("No hay Fecha Seleccionado");
			}
		if(datosClinico==""){
			alert("Los Datos Clinicos No Pueden Ir Vacios");
			}
		//alert("codPaciente "+codPaciente+" codExamen "+codExamen+" hora "+hora+" fecha "+fecha+" datosClinico "+datosClinico+" usuario "+usuario);
		var e;
	    for(e=0;e<datosClinico.length;e++){
	    	datosClinico=datosClinico.replace('Ñ','@');
			datosClinico=datosClinico.replace('ñ','@');
	    	datosClinico=datosClinico.replace('Á','A');
	    	datosClinico=datosClinico.replace('É','E');
	    	datosClinico=datosClinico.replace('Í','I');
	    	datosClinico=datosClinico.replace('Ó','O');
	    	datosClinico=datosClinico.replace('Ú','U');
	    	datosClinico=datosClinico.replace('á','a');
	    	datosClinico=datosClinico.replace('é','e');
	    	datosClinico=datosClinico.replace('í','i');
	    	datosClinico=datosClinico.replace('ó','o');
	    	datosClinico=datosClinico.replace('ú','u');
	    }
		if((codPaciente!="")&&(codExamen!="")&&(hora!="")&&(fecha!="")&&(datosClinico!="")){
	    xmlhttp.open("POST","ControlCitasExamen?valor=9&codigoExa_fk="+codExamen+"&codigoPac_fk="+codPaciente+"&estado=-1&usuario="+usuario+"&hora="+hora+"&fecha="+fecha+"&datosClinico="+datosClinico+"&portatil="+portatil,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = PERMISOS;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send(""); //Posting txtname to Servlet
		alert("Examenes Asignados Correctamente!!!");
		}
		//finalizar();	    
	}	
}
function PERMISOS() {		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {			     	
				var a = xmlhttp.responseText		     	
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
}