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
/****************************DEVOLVER ALTA PACIENTE*********************************/
function DevolverAltaAdm(){
	var CodHistoricoCama=document.getElementById("CodHistoricoCama").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodCama=document.getElementById("CodCama").value;
	var CamaNueva=document.getElementById("cmbCama").value;
	var servicio=document.getElementById("servicio").value;
	if(servicio=="1"){
		if(CamaNueva=="null"){
			//alert("Entro Cuando la Cama esta Disponible.Urg");
			//alert("CodHistoricoCama "+CodHistoricoCama+" CodAdm "+CodAdm+" CodCama "+CodCama+" CamaNueva "+CamaNueva+" servicio "+servicio);
			ajax=getXMLObject();
			ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert("Devolucion de Alta Exitosa.");
					//document.getElementById('Admisiones').innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=6.4&CodHistoricoCama="+CodHistoricoCama+"&CodAdm="+CodAdm+"&CodCama="+CodCama+"&CamaNueva="+CamaNueva+"&servicio="+servicio); //Posting txtname to Servlet
		}else{
			//alert("Entro Cuando la Cama esta Ocupada.Urg");
			//alert("CodHistoricoCama "+CodHistoricoCama+" CodAdm "+CodAdm+" CodCama "+CodCama+" CamaNueva "+CamaNueva+" servicio "+servicio);
			ajax=getXMLObject();
			ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert("Devolucion de Alta Exitosa.");
					//document.getElementById('Admisiones').innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=6.5&CodHistoricoCama="+CodHistoricoCama+"&CodAdm="+CodAdm+"&CodCama="+CodCama+"&CamaNueva="+CamaNueva+"&servicio="+servicio); //Posting txtname to Servlet
		}
	}else{
		if(CamaNueva=="null"){
			alert("Entro Cuando la Cama esta Disponible.Hosp");
			alert("CodHistoricoCama "+CodHistoricoCama+" CodAdm "+CodAdm+" CodCama "+CodCama+" CamaNueva "+CamaNueva+" servicio "+servicio);
			ajax=getXMLObject();
			ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert("Devolucion de Alta Exitosa.");
					//document.getElementById('Admisiones').innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=6.4&CodHistoricoCama="+CodHistoricoCama+"&CodAdm="+CodAdm+"&CodCama="+CodCama+"&CamaNueva="+CamaNueva+"&servicio="+servicio); //Posting txtname to Servlet
		}else{
			//alert("Entro Cuando la Cama esta Ocupada.Hosp");
			//alert("CodHistoricoCama "+CodHistoricoCama+" CodAdm "+CodAdm+" CodCama "+CodCama+" CamaNueva "+CamaNueva+" servicio "+servicio);
			ajax=getXMLObject();
			ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert("Devolucion de Alta Exitosa.");
					//document.getElementById('Admisiones').innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=6.5&CodHistoricoCama="+CodHistoricoCama+"&CodAdm="+CodAdm+"&CodCama="+CodCama+"&CamaNueva="+CamaNueva+"&servicio="+servicio); //Posting txtname to Servlet
		}
	}
}

function BuscarDevolverAdmision(){
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
		 ajax.send("valor=6&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento); //Posting txtname to Servlet
	 }
}

function DevolverAlta(CodHistoricoCama,CodAdm,CodCama){
	//alert("CodHistoricoCama "+CodHistoricoCama+" CodAdm "+CodAdm+" CodCama "+CodCama);	
		 ajax=getXMLObject();
		 ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
		 ajax.onreadystatechange=function() {
			 if (ajax.readyState==4) {
				 document.getElementById('Admisiones').innerHTML = ajax.responseText;
			 }
		 }
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=6.1&CodHistoricoCama="+CodHistoricoCama+"&CodAdm="+CodAdm+"&CodCama="+CodCama); //Posting txtname to Servlet
}

function LlenarSubarea(){
	 ajax=getXMLObject();
	 var CodArea=document.getElementById("cmbArea").value;
	 ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
	 ajax.onreadystatechange=function() {
		 if (ajax.readyState==4) {
			 document.getElementById('SubareaCama').innerHTML = ajax.responseText;
		 }
	 }
	 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	 ajax.send("valor=6.2&CodArea="+CodArea); //Posting txtname to Servlet
}

function LlenarCamasSubarea(){
	 ajax=getXMLObject();
	 var CodSubArea=document.getElementById("cmbSubArea").value;
	 ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
	 ajax.onreadystatechange=function() {
		 if (ajax.readyState==4) {
			 document.getElementById('Cama').innerHTML = ajax.responseText;
		 }
	 }
	 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	 ajax.send("valor=6.3&CodSubArea="+CodSubArea); //Posting txtname to Servlet
}
/***************************FIN DEVOLVER ALTA PACIENTE******************************/



/*****************************************************/
function CargarCenso(){	
	ajax=getXMLObject();
	ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			document.getElementById('Opcion').innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5"); //Posting txtname to Servlet
}

function ReporteTrasladoUrgHosp(CodAdm){
	pagina="adm_ReporteTrasladoUrgHos.jsp?CodAdm="+CodAdm			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);	
}


function mostarAlta (CodAdmision,Codigocama) {		
	pagina="adm_ReporteAltaPaciente.jsp?CodAdm="+CodAdmision+"&CodCama="+Codigocama;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}


function BuscarAltaAdmision(){
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
		 ajax.send("valor=4&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento); //Posting txtname to Servlet
	 }
}
function CambiarMedicoTratante(){
	//alert();
	var CodAdm=document.getElementById("txtCodAdm").value;
	var CodUsu=document.getElementById("txtUsuario").value;
	var CodMedTra=document.getElementById("cmbMedicoTratante").value;
	var vuet = document.getElementById("cmbMedicoTratante").selectedIndex;
	var NomMedico = document.getElementById("cmbMedicoTratante").options[vuet].text;
	
	if(CodMedTra=="Seleccione"){
		alert("Seleccione Medico Tratante");
	}else{
		 ajax=getXMLObject();
		 ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
		 ajax.onreadystatechange=function() {
			 if (ajax.readyState==4) {
				 alert(ajax.responseText);
				 window.location.reload();
				 //document.getElementById('Admisiones').innerHTML = ajax.responseText;
			 }
		 }
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=CMD&CodAdm="+CodAdm+"&CodUsu="+CodUsu+"&CodMedTra="+CodMedTra+"&NomMedico="+NomMedico); //Posting txtname to Servlet

	}
}
function BuscarMedicoTratante(){
	 var TipoDocumento=document.getElementById("cbafiliacion").value; 
	 var NumeroDocumento=document.getElementById("txtnumdoc").value; 	 
	 if(NumeroDocumento==""){
		alert("Escriba Numero Documento."); 
	 }else{
		 ajax=getXMLObject();
		 ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
		 ajax.onreadystatechange=function() {
			 if (ajax.readyState==4) {
				// alert(ajax.responseText);
				 document.getElementById('Admisiones').innerHTML = ajax.responseText;
			 }
		 }
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=BMD&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento); //Posting txtname to Servlet
	 }
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
				ajax.open("POST","ControlCenso",true); //getname will be the servlet name
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
 
 function impresion(){
	 pagina="Censo_Historico.jsp"				
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
	}
 function ImprimeCensoNombre(){
	 pagina="Censo_Nombre.jsp"				
		 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		 window.open(pagina,"NUEVA",opciones);
 }
 
 function CensoServicio(){
	 var NomSubArea=document.getElementById("cmbServicio").value;
	 if(NomSubArea=="Seleccione"){
		 alert("Seleccione El Servicio.");
	 }
	 if(NomSubArea!="Seleccione"){
		 pagina="Censo_Servicio.jsp?NomSubArea="+NomSubArea;				
			 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
			 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
			 window.open(pagina,"NUEVA",opciones);
	 }
 }
 
 function CensoEntidad(){
	 var CodEntidad=document.getElementById("cmbEntidad").value;
	 if(CodEntidad=="Seleccione"){
		 alert("Seleccione La Entidad.");
	 }
	 if(CodEntidad!="Seleccione"){
		 pagina="Censo_Entidad.jsp?CodEntidad="+CodEntidad;				
			 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
			 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
			 window.open(pagina,"NUEVA",opciones);
	 }
 }