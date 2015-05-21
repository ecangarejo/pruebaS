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
function BuscarPaciente(){
	var TipoDocumento=document.getElementById("cmbTipoDoc").value; 
	var NumeroDocumento=document.getElementById("txtnumdoc").value; 
	ajax=getXMLObject();
	ajax.open("POST","ControlTrasladoLlenar",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			document.getElementById('Resultado').innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("va=8&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento); //Posting txtname to Servlet
}

function CargarSubarea(){
	var CodArea=document.getElementById("cmbArea").value; 
	 ajax=getXMLObject();
	 ajax.open("POST","ControlTrasladoLlenar",true); //getname will be the servlet name
	 ajax.onreadystatechange=function() {
	 if (ajax.readyState==4) {
		  document.getElementById('Subarea').innerHTML = ajax.responseText;
		}
	}
	 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	 ajax.send("va=8.1&CodArea="+CodArea); //Posting txtname to Servlet

}

function ObtenerCama(){
	var CodSubarea=document.getElementById("cmbSubarea").value; 
	 ajax=getXMLObject();
	 ajax.open("POST","ControlTrasladoLlenar",true); //getname will be the servlet name
	 ajax.onreadystatechange=function() {
	 if (ajax.readyState==4) {
		  document.getElementById('CamaSub').innerHTML = ajax.responseText;
		}
	}
	 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	 ajax.send("va=8.2&CodSubarea="+CodSubarea); //Posting txtname to Servlet
	
}

function IngresarMedicoTratante(CodAdm,MedicoTratante,usuario){
	//var NomMedico=document.getElementById("cmbMedicoTratante").value;
	var vuet = document.getElementById("cmbMedicoTratante").selectedIndex;
	var NomMedico = document.getElementById("cmbMedicoTratante").options[vuet].text;
	
	//alert("va=Imt&CodAdm="+CodAdm+"&UsuarioIngresa="+usuario+"&NomMedico="+NomMedico+"&CodMedTra="+MedicoTratante);
	ajax=getXMLObject();
	ajax.open("POST","ControlTrasladoLlenar",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			var c =ajax.responseText;
			//alert("c "+c);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("va=Imt&CodAdm="+CodAdm+"&UsuarioIngresa="+usuario+"&NomMedico="+NomMedico+"&CodMedTra="+MedicoTratante); //Posting txtname to Servlet

}

function TrasladarUrgHosp(){
	var CodCamaVieja=document.getElementById("txtCodCama").value;
	var CodCamaNueva=document.getElementById("cmbCama").value; 
	var CodAdm=document.getElementById("txtCodAdm").value;
	var Fecha=document.getElementById("txtFecha").value;
	var Hora=document.getElementById("txtHora").value;
	var Observacion=document.getElementById("txtObservacion").value;
	var NumAutorizacion=document.getElementById("txtNumAutoriza").value;
	var NombreAutoriza=document.getElementById("txtAutorizadoPor").value;
	var MedicoTratante=document.getElementById("cmbMedicoTratante").value;
	var usuario=document.getElementById("txtUsuario").value;
	
	if(CodCamaNueva=="Seleccione"){
		alert("Seleccione la cama destino.");
	}else{
		if(NumAutorizacion==""){
			alert("Digite numero autorizacion");
		}else{
			if(MedicoTratante=="Seleccione"){
				alert("Seleccione Medico Tratante.");
			}else{
				ajax=getXMLObject();
				ajax.open("POST","ControlTrasladoLlenar",true); //getname will be the servlet name
				ajax.onreadystatechange=function() {
					if (ajax.readyState==4) {
						var c =ajax.responseText;
						if(c==1){
							alert("Traslado Exitoso.");
							ReporteTrasladoUrgHosp(CodAdm,CodCamaNueva);
							if(MedicoTratante!="Seleccione"){
								IngresarMedicoTratante(CodAdm,MedicoTratante,usuario);
							}
							window.location.reload();
						}
						if(c==2){
							alert("Al Paciente No Le Han Dado La Alta De Urgencia.\nEL Medico En Turno Debe De Dar La Salida");
						}
						//alert();
						//document.getElementById('CamaSub').innerHTML = ajax.responseText;
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("va=9.1&CodCamaVieja="+CodCamaVieja+"&CodCamaNueva="+CodCamaNueva+
						"&CodAdm="+CodAdm+"&Fecha="+Fecha+"&Hora="+Hora+"&Observacion="+Observacion+
						"&NumAutorizacion="+NumAutorizacion+"&NombreAutoriza="+NombreAutoriza+"&usuario="+usuario); //Posting txtname to Servlet
			}
		}
	}
}


function ReporteTrasladoUrgHosp(CodAdm,CodCamaNueva){
	pagina="adm_ReporteTrasladoUrgHos.jsp?CodAdm="+CodAdm			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);	
   	ReporteAnexo3(CodAdm,CodCamaNueva);
}


function ReporteAnexo3(CodAdm,CodCamaNueva){
	pagina="fact_Anexo3.jsp?adm="+CodAdm+"&cama="+CodCamaNueva;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA2",opciones);	
   	
}