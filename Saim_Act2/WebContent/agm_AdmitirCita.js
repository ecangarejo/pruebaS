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
function CargarFormulario(){
	ajax=getXMLObject();
	var Formulario=document.getElementById("Formulario");
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			Formulario.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=10"); //Posting txtname to Servlet
}


function VerificarDatosCitaCEX(CodHorMed){
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("DatosCitaCEX").innerHTML = ajax.responseText
		}
	};
	
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VCP&CodHorMed="+CodHorMed); //Posting txtname to Servlet

	
}
function BuscarCitas(){
	ajax=getXMLObject();
	var Resultado=document.getElementById("Resultado");
	var CodMedico=document.getElementById("cmbMedico").value;
	var Identificacion=document.getElementById("txtNumeroDoc").value;
	var CodEspecialista=document.getElementById("cmbEspecialista").value;
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			Resultado.innerHTML = ajax.responseText
		}
	};
	
	if((CodMedico=="Seleccione")&&(CodEspecialista=="Seleccione")&&(Identificacion=="")){
		//no selecciona nada
		alert("Seleccione Algun Parametro de Busqueda");
	}
	if(Identificacion==""){
		if((CodMedico!="Seleccione")&&(CodEspecialista=="Seleccione")){
			//solo busca por medico
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=11&Tipo=M&CodMedico="+CodMedico); //Posting txtname to Servlet
		}	
		if((CodMedico=="Seleccione")&&(CodEspecialista!="Seleccione")){
			//solo busca por especialidad
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=11&Tipo=E&CodEspecialista="+CodEspecialista); //Posting txtname to Servlet
		}	
		if((CodMedico!="Seleccione")&&(CodEspecialista!="Seleccione")){
			//busca por medico y por especialidad
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=11&Tipo=ME&CodMedico="+CodMedico+"&CodEspecialista="+CodEspecialista); //Posting txtname to Servlet
		}
	}else{
		//busca por documento
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=11&Tipo=ID&Identificacion="+Identificacion); //Posting txtname to Servlet

	}
}

function ActualizarOrden(CodHorMed){
	var CodProg=document.getElementById("txtCodPro").value;
	var ValorPro=document.getElementById("txtValorPro").value;
	var NumAutori=document.getElementById("txtNumAutor").value;
	var Copago=document.getElementById("txtCopagoCex").value;
	var Moderacion=document.getElementById("txtModeracionCex").value;
	var CodCsC=document.getElementById("cmbSubCentCost").value;
	var CodSucur=document.getElementById("cmbSucur").value;
	var CodDx=document.getElementById("txtCodDx").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//window.location.reload();
			var dato=ajax.responseText;
			//alert(dato)
			if(dato=="1"){
				document.getElementById("btnPasarListaMedico").disabled=false;
				document.getElementById("btnActualizarOrden").disabled=true;
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AOCEX&CodHorMed="+CodHorMed+"&CodProg="+CodProg+"&ValorPro="+ValorPro+"&NumAutori="+NumAutori+
			"&Copago="+Copago+"&Moderacion="+Moderacion+"&CodCsC="+CodCsC+"&CodSucur="+CodSucur+"&CodDx="+CodDx);

}
function IngresarOrden(CodHorarioMedico){
	var CodProg=document.getElementById("txtCodPro").value;
	var CodDx=document.getElementById("txtCodDx").value;
	var ValorPro=document.getElementById("txtValorPro").value;
	var NumAutori=document.getElementById("txtNumAutor").value;
	var Copago=document.getElementById("txtCopagoCex").value;
	var Moderacion=document.getElementById("txtModeracionCex").value;
	var CodCsC=document.getElementById("cmbSubCentCost").value;
	var CodSucur=document.getElementById("cmbSucur").value;
	var CodEnt=document.getElementById("CodEnt").value;
	var NitEnti=document.getElementById("NitEnti").value;
	var CodUsu=document.getElementById("txtCodusuario").value;
	document.getElementById("btnPasarListaMedico").disabled=true;
	if(Copago==""){Copago="0";}	
	if(Moderacion==""){Moderacion="0";}
	if(CodProg==""){
		alert("Digite el programa.");
	}else{
		if(CodDx==""){
			alert("Digite el diagnostico.");
		}else{
			if(NumAutori==""){
				alert("Digite numero de autorizacion.");
				document.getElementById("txtNumAutor").focus();
			}else{
				if(CodCsC=="Seleccione"){
					alert("Seleccione el Subcentro de costo.");
					document.getElementById("cmbSubCentCost").focus();
				}else{	
					ajax=getXMLObject();
					ajax.open("POST","ControlAsignarCita",true);
					ajax.onreadystatechange = function(){
						if(ajax.readyState == 4){
							//window.location.reload();
							var dato=ajax.responseText;
							//	alert(dato)
							if(dato=="1"){
								//document.getElementById("btnPasarListaMedico").disabled=false;
								//document.getElementById("btnIngresarOrden").disabled=true;
								ActivarCitaMedico(CodHorarioMedico)
							}
						}
					}
					ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=IOCEX&CodHorMed="+CodHorarioMedico+"&CodProg="+CodProg+"&CodDx="+CodDx+"&ValorPro="+ValorPro+"&NumAutori="+NumAutori+
							"&Copago="+Copago+"&Moderacion="+Moderacion+"&CodCsC="+CodCsC+"&CodSucur="+CodSucur+"&CodEnt="+CodEnt+"&NitEnti="+NitEnti+"&CodUsu="+CodUsu);
				}
			}
		}
	}
}

function ActivarCitaMedico(CodHorarioMedico){
	ajax=getXMLObject();
	document.getElementById("btnPasarListaMedico").disabled=true;
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var CodEnc=ajax.responseText;
			//alert(CodEnc);
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12&CodHorarioMedico="+CodHorarioMedico);
}

function CargarDxNota(){
	var total=document.getElementById('txtContador').value;
	var txtSeldx=document.getElementById("txtSeldx");
	var texthc=document.getElementById("txtRespuesta").value;
	var cont=total;	
	if(total!=1){
		for(var h=0; h<=total-1; h++){			
			if(document.form1.chkPorAtender[h].checked){
				diagnosticos=document.form1.chkPorAtender[h].value;	
				texthc=texthc+diagnosticos+"-->";
				document.getElementById("txtRespuesta").value=texthc;
				ActualizarResultadosCE();
			}	
			cont--;
		}
	}
}

function ActivarCita(){	
	var total=document.getElementById('txtContador').value;
	var cont=total;	
	if(total!=1){
		for(var h=0; h<=total-1; h++){			
			if(document.form1.chkPorAtender[h].checked){
				CodHorarioMedico=document.form1.chkPorAtender[h].value;
				ActivarCitaMedico(CodHorarioMedico);
			}			
			cont--;
		}
	}
	if(total==1){
		if(document.form1.chkPorAtender.checked){
			CodHorarioMedico=document.form1.chkPorAtender.value;
			ActivarCitaMedico(CodHorarioMedico);
		}
		else{
			alert("Seleccione Algun Dato.");
		}
	}
}

function CargarListaMedico(){
	ajax=getXMLObject();
	var Formulario=document.getElementById("Formulario");
	var CodUsuario=document.getElementById("txtCodusuario").value;
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			Formulario.innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=13&CodUsuario="+CodUsuario); //Posting txtname to Servlet
}
function reload(){
	window.location.reload();
}
function CargarDatosPaciente(){
	var CodHorarioMedico=document.getElementById("CodHorarioMedico").value;
	var CodPac=document.getElementById("CodPac").value;
	var DocPaciente=document.getElementById("CedPac").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			document.getElementById("FormularioCE").innerHTML = ajax.responseText;
			document.getElementById("CodPac").value=CodPac;
		}
	}		
 	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=14&CodigoPaciente="+CodPac+"&CodHorarioMedico="+CodHorarioMedico); //Posting txtname to Servlet
}

function AtenderCita(CodHorarioMedico,CodPac,DocPaciente,CodAdm){	
	//alert("agm_AtencionPaciente.jsp?CodHorarioMedico="+CodHorarioMedico+"&CodPac="+CodPac+"&DocPaciente="+DocPaciente);
	window.location.href="agm_AtencionPaciente.jsp?CodHorarioMedico="+CodHorarioMedico+"&CodPac="+CodPac+"&DocPaciente="+DocPaciente+"&CodAdm="+CodAdm;
}
/**************************INMUNOTERAPIA********************************/
function InmunoterapiaCE(){
	var CodHorarioMedico=document.getElementById("CodHorarioMedico").value;
	var CodPac=document.getElementById("CodPac").value;
	var DocPaciente=document.getElementById("CedPac").value;
	DivValor=document.getElementById('HistoriaPaciente');
	ajax=getXMLObject();
    ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
    ajax.onreadystatechange  = function(){
    	if (ajax.readyState == 4) {				
    		Contenido=ajax.responseText;
    		//alert(Contenido)
			DivValor.innerHTML=Contenido;
  	   	}
	  }
  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=inm&CodPac="+CodPac); //Posting txtname to Servlet
}

function IngresarInmuno(){
	var Dilucion=document.getElementById("cmbDilucion").value;
	var ViaAdmi=document.getElementById("cmbViaAdministracion").value;
	var VolInye=document.getElementById("txtVolumenInyectado").value;
	var Alergeno=document.getElementById("cmbAlergeno").value;
	var Via=document.getElementById("cmbVia").value;
	var Observacion=document.getElementById("txtObservacion").value;
	var TipoControl="";
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			TipoControl=radioButtons[x].id;
		}
	}	
	var CodPac=document.getElementById("CodPac").value;
	var CodHorarioMedico=document.getElementById("CodHorarioMedico").value;
	var Codusuario=document.getElementById("txtCodusuario").value;
	if(Dilucion=="Seleccione"){
		alert("Seleccione Dilucion.");
		document.getElementById("cmbDilucion").focus();
	}else{
		if(ViaAdmi=="Seleccione"){
			alert("Seleccione Via Administracion.");
			document.getElementById("cmbViaAdministracion").focus();
		}else{
			if(VolInye==""){
				alert("Ingrese Volumen Inyectado.");
				document.getElementById("txtVolumenInyectado").focus();
			}else{
				if(Alergeno=="Seleccione"){
					alert("Seleccione el Alergeno.");
					document.getElementById("cmbAlergeno").focus();
				}else{
					if(Via=="Seleccione"){
						alert("Seleccione la Via.");
						document.getElementById("cmbVia").focus();
					}else{
						if(TipoControl==""){
							alert("Seleccione el tipo de control.");
						}else{
							ajax=getXMLObject();
							ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
							ajax.onreadystatechange  = function(){
								if (ajax.readyState == 4) {
									//contenido.innerHTML = ajax.responseText
									InmunoterapiaCE();
								}
							};
							ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
							ajax.send("valor=IGINM&Dilucion="+Dilucion+"&ViaAdmi="+ViaAdmi+
									"&VolInye="+VolInye+"&Alergeno="+Alergeno+"&Via="+Via+
									"&Observacion="+Observacion+"&TipoControl="+TipoControl+
									"&CodPac="+CodPac+"&CodHorarioMedico="+CodHorarioMedico+
									"&Codusuario="+Codusuario);
						}
					}
					
				}
			}
		}
	}

}

function ImprimirHistoInmuno(CodPac){
	pagina="agm_FormatoInmuno.jsp?CodPac="+CodPac;		
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
}
/**********************FIN INMUNOTERAPIA********************************/

/**************************************************************************************/
/*****************************FORMULACION SOLA****************************************/
function BuscarPacienteFormulacion(){
	DivValor=document.getElementById('HistoriaPaciente');
	var TipoDoc=document.getElementById('cmbTipoDoc').value;
	var NumDocumento=document.getElementById("txtNumDocumento").value;
	ajax=getXMLObject();
	    ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
	    		//alert(Contenido)
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=F15&TipoDoc="+TipoDoc+"&NumDocumento="+NumDocumento); //Posting txtname to Servlet
	//alert("Orden de Servicio");
	
}
/***************************************************************************************/
/***************************ORDEN DE SERVICIO***************************/
function OrdenServicioCE(){
	DivValor=document.getElementById('HistoriaPaciente');
	var CodPac=document.getElementById('CodPac').value;
	ajax=getXMLObject();
	    ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
	    		//alert(Contenido)
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=15&CodPac="+CodPac); //Posting txtname to Servlet
	//alert("Orden de Servicio");
	}

function Radio2CE() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('opciones');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
	         ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
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

/**************************************************************************************/
function AsignarMediCE(){
	/************FORMULACION*******************/
	var CodPac=document.getElementById("CodPac").value;
	var codCita=document.getElementById("CodHorarioMedico").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	//falta una observacion de formulacion.
	/*******DETALLE FORMULACION****************/
	var CodFormulacion_fk=document.getElementById("txtCodFormulacion").value;
	var CodigoMed=document.getElementById("txtCodigoMed").value;
	var Cantidad=document.getElementById("txtCantidad").value;
	var Dias=document.getElementById("txtDias").value;
	///////////////////////////////////////
	var CantidadDosis=document.getElementById("cmbCantidad").value;
	var Lapso=document.getElementById("cmbLapso").value;
	var Administracion=document.getElementById("cmbViaAdmi").value;
	///////////////////////////////////////
	var ConteForm=document.getElementById("ConteForm");
	var DetOrden=encodeURIComponent(document.getElementById("txtDetOrden").value);
	var Dosificacion="";
	///////////////////////////////////////
	/*if(CantidadDosis=="Seleccione"){
		alert("Seleccione Cantidad De La Dosis.");
	}else{
		if(Lapso=="Seleccione"){
			alert("Seleccione Lapso De Tiempo.");
		}else{
			if(Administracion=="Seleccione"){
				alert("Seleccione Tipo De Administracion.");
			}
		}
	}*/
	///////////////////////////////////////
	var Observacion=document.getElementById("txtObservacion").value;
	/*if((CantidadDosis!="Seleccione")&&(Lapso!="Seleccione")&&(Administracion!="Seleccione")){
		Dosificacion="Dar "+CantidadDosis+" Cada "+Lapso+" Horas. "+Administracion;
		//document.getElementById("txtObservacion").value=Dosificacion;
	}
	if((CantidadDosis=="")&&(Lapso=="")&&(Administracion=="")){
		Dosificacion="";
	}*/
	//////////////////////////////////////////////////////////////
	Dosificacion="Dar "+Cantidad+" por "+Dias+" Dias. "+Observacion;
	
	//alert("valor=99&codPac="+CodPac+"&codCita="+codCita+"&usuario="+usuario+"&hora="+hora+"&fecha="+fecha+"&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden); //Posting txtname to Servlet
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	/////////////////////////////////////////////////////////////
	if(CodFormulacion_fk==""){
		// si es primer medicamento.
		//if((Cantidad!="")&&(Administracion!="Seleccione")){
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				document.getElementById("txtCodigoMed").value="";
				document.getElementById("txtMedicamento").value="";
				document.getElementById("txtMedicamento").focus();
				ConteForm.innerHTML=ajax.responseText;
			}
		}
		//alert("CodFormulacion= vacio "+"valor=9&codPac="+CodPac+"&codAdm="+CodAdm+"&usuario="+usuario+"&codCama="+CodCama+"&codArea="+CodArea+"&codSubarea="+CodSubarea+"&hora="+hora+"&fecha="+fecha+"&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=99&codPac="+CodPac+"&codCita="+codCita+"&usuario="+usuario+"&hora="+hora+"&fecha="+fecha+"&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden); //Posting txtname to Servlet
		//}
	}
	
	if(CodFormulacion_fk!=""){
		// de segundo medicamento en adelante.
		//if((Cantidad!="")&&(Administracion!="Seleccione")){
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				document.getElementById("txtCodigoMed").value="";
				document.getElementById("txtMedicamento").value="";
				document.getElementById("txtMedicamento").focus();
				ConteForm.innerHTML=ajax.responseText;
			}
		}
		//alert("CodFormulacion= lleno "+"valor=9.1&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=99.1&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden+"&codCita="+codCita); //Posting txtname to Servlet
		//}
	}
	
}

function FinalFormulacionCE(){
	var opcion=confirm("Desea Finalizar La Formulacion?");	
	if(opcion){
		var codFormulacion_fk=document.getElementById("txtCodFormulacion").value; 
		ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				var validar=ajax.responseText;
				if(validar=="1"){
					ReporteFormulacionCE(codFormulacion_fk);
					window.location.reload();
					//OrdenServicio();
				}else{
					alert("Error En Actualizacion de Datos.")
				}
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=100&codFormulacion_fk="+codFormulacion_fk); //Posting txtname to Servlet
	}else{
		
	}
}

function ImprimirTotalReciboCaja(){
	
    pagina="agm_FormulacionCE.jsp?FechaIni="+FechaIni+"&FechaFin="+FechaFin	;		
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
    //OrdenServicio();
}

function ReporteFormulacionCE(CodFormulacion){
	
    pagina="agm_FormulacionCE.jsp?CodFormulacion="+CodFormulacion;			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
    //OrdenServicio();
}

function OmitirDetalleFormulacionCE(codDetFormulacion){
	var CodFormulacion_fk=document.getElementById("txtCodFormulacion").value;
	var ConteForm=document.getElementById("ConteForm");	
	ajax=getXMLObject();	
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("txtCodigoMed").value="";
			document.getElementById("txtMedicamento").value="";
			document.getElementById("txtMedicamento").focus();
			ConteForm.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=111&codDetFormulacion_fk="+codDetFormulacion+"&codFormulacion_fk="+CodFormulacion_fk); //Posting txtname to Servlet
}
/**************************************************************************************/

function BuscarTipoMediCE(){	
	var Tipo=document.getElementById("txtTipo").value;
	ajax=getXMLObject();
	Contenido=document.getElementById('DetAdministra');
	var CodMedicamento=document.getElementById("txtCodigoMed").value;
	var CodFormulacion_fk=document.getElementById("txtCodFormulacion").value;
	if(Tipo=="0"){		
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				Contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=8.1&CodMedicamento="+CodMedicamento+"&codFormulacion_fk="+CodFormulacion_fk); //Posting txtname to Servlet
	}
}
function EnviarLaboratoriosCE(CodEstudio,TipoEstu,contador){
	var CodigoPac=document.getElementById("CodPac").value;
	var HoraPeticion=document.getElementById("txtHora").value;
	var FechaPeticion=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value; 
	var TipoPyP=document.getElementById("cmbTipoPyP").value;
	var codCita=document.getElementById("CodHorarioMedico").value;
	var CodFormlab=document.getElementById("CodFormlab").value;
	Contenido=document.getElementById('Cargados');
	//alert(contador)
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	if(CodFormlab==""){		
		//alert("cont en js "+contador);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var cf=ajax.responseText;
				var y=cf.split("|").length;
		     	var z=cf.split("|");		     	
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
		     	fecha=z[0];
			    hora=z[1];
			    }
				//Contenido.innerHTML=ajax.responseText;				
				document.getElementById('examenes').innerHTML="";
				mostrardetalleformulacionestudios(fecha,hora);
			}
		}
		
		//alert("valor=18&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPaciente="+CodigoPac+"&HoraPeticion="+HoraPeticion+"&FechaPeticion="+FechaPeticion+"&CodUsuario="+usuario+"&TipoPyP="+TipoPyP+"&codCita="+codCita+"&contador="+contador);
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=18&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPaciente="+CodigoPac+"&HoraPeticion="+HoraPeticion+"&FechaPeticion="+FechaPeticion+"&CodUsuario="+usuario+"&TipoPyP="+TipoPyP+"&codCita="+codCita+"&contador="+contador);
	}else{
		//alert("CodFormlab esta lleno")
		var fechaFr=document.getElementById("txtfechaFr").value;
		var horaFr=document.getElementById("txthoraFr").value;
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				
				document.getElementById('examenes').innerHTML="";
				mostrardetalleformulacionestudios(fechaFr,horaFr)
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=18.2&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPaciente="+CodigoPac+"&horaFr="+horaFr+"&fechaFr="+fechaFr+"&CodUsuario="+usuario+"&TipoPyP="+TipoPyP+"&codCita="+codCita+"&CodFormlab="+CodFormlab);
	}
	
}
function mostrardetalleformulacionestudios(fecha,hora){
	//alert(fecha+" y "+hora);
	Contenido=document.getElementById('Cargados');
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			Contenido.innerHTML=ajax.responseText;
			//document.getElementById("CodFormlab").value=CodFormlab;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=18.1&fechaFr="+fecha+"&horaFr="+hora);

}

function OmitirDetalleLab(CodDetLab,CodFormlab){
	//var fechaFr=document.getElementById("txtfechaFr").value;
	//var horaFr=document.getElementById("txthoraFr").value;
	//var CodFormlab=document.getElementById("CodFormlab").value;
	//alert(CodDetLab)
	//alert("valor=18.3&CodDetLab="+CodDetLab+"&CodFormlab="+CodFormlab);
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//mostrardetalleformulacionestudios(fechaFr,horaFr)
			//alert("Estudio Eliminado. "+ajax.responseText);
			document.getElementById('Cargados').innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=18.3&CodDetLab="+CodDetLab+"&CodFormlab="+CodFormlab);
}

function FinalizarFormLab(CodFormlab){
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//CodFormlab=ajax.responseText;
			//alert("aqui viene el reporte");
			OrdenServicioCE();
			ImprimirEstudiosCE(CodFormlab);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=18.4&CodFormlab="+CodFormlab);
}
function ImprimirEstudiosCE(CodFormlab){
	pagina="agm_FormulacionEstudiosCE.jsp?CodFormlab="+CodFormlab;			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
}

function GuardarLaboratoriosCE2(){
	var CodigoPac=document.getElementById("CodPac").value;
	var HoraPeticion=document.getElementById("txtHora").value;
	var FechaPeticion=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value; 
	var TipoPyP=document.getElementById("cmbTipoPyP").value;
	var codCita=document.getElementById("CodHorarioMedico").value;
	var CodEstudio=document.getElementById("cmbExamen").value;
	var CodFormlab=document.getElementById("CodFormlab").value;
	var TipoEstu='1';
	//var CodFormlab=document.getElementById("CodFormlab").value;	
	//alert("valor=18&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPaciente="+CodigoPac+"&HoraPeticion="+HoraPeticion+"&FechaPeticion="+FechaPeticion+"&CodUsuario="+usuario+"&TipoPyP="+TipoPyP+"&codCita="+codCita);
	//alert(CodFormlab);
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){			
			//alert(ajax.responseText);
			document.getElementById('Cargados').innerHTML=ajax.responseText;
			//mostrardetalleformulacionestudios(fechaFr,horaFr)
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=18.1&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPaciente="+CodigoPac+"&HoraPeticion="+HoraPeticion+"&FechaPeticion="+FechaPeticion+"&CodUsuario="+usuario+"&TipoPyP="+TipoPyP+"&codCita="+codCita+"&CodFormlab="+CodFormlab);
	//alert("valor=18.1&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPaciente="+CodigoPac+"&HoraPeticion="+HoraPeticion+"&FechaPeticion="+FechaPeticion+"&CodUsuario="+usuario+"&TipoPyP="+TipoPyP+"&codCita="+codCita+"&CodFormlab="+CodFormlab);

	
	
}
function GuardarLaboratoriosCE(){	
	var total=document.getElementById('yo').value;
	var cont=total;	
	var contador=0;
	if(total!=1){		
		for(var h=0; h<=total-1; h++){			
			if(document.form1.ca[h].checked){
				contador=contador+1;
				CodEstudio=document.form1.ca[h].value;
				TipoEstu=document.form1.txtTipo[h].value;
				//alert("CodEstudio "+CodEstudio+" TipoEstu "+TipoEstu+" contador "+contador);
				EnviarLaboratoriosCE(CodEstudio,TipoEstu,contador);
			}
			cont--;
		}		
	}
	else{
		if(document.form1.ca.checked){
//			alert("1");
			CodEstudio=document.form1.ca.value;
			TipoEstu=document.form1.txtTipo.value;
			EnviarLaboratoriosCE(CodEstudio,TipoEstu,"1");
		}
	}
}
function VerExamenesCE(){	
	DivValor=document.getElementById('examenes');
	var CodArea=document.getElementById("cmbgrupos").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			Contenido=ajax.responseText;
			DivValor.innerHTML=Contenido;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=17&CodArea="+CodArea); //Posting txtname to Servle	
}
/*function VerExamenesCE(){	
	DivValor=document.getElementById('examenes');
	var CodArea=document.getElementById("cmbgrupos").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			Contenido=ajax.responseText;
			DivValor.innerHTML=Contenido;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=17&CodArea="+CodArea); //Posting txtname to Servle	
}*/

/*************************** FIN ORDEN DE SERVICIO***************************/
/*************************** FORMATOS         **************************/
function FormatosCE(){
	DivValor=document.getElementById('HistoriaPaciente');
	var usuario=document.getElementById("txtCodusuario").value; 
	var CodPaciente=document.getElementById("CodPac").value;
	ajax=getXMLObject();
	    ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
				CargarFormatosCE();
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=19&CodUsuario="+usuario+"&CodigoPaciente="+CodPaciente); //Posting txtname to Servlet
	    
}

function AsignarFormatoCE(){
	
	document.getElementById("btnVerFormatos").disabled=true;
	
	var CodFormato=document.getElementById("txtCodFormato").value;
	var CodPaciente=document.getElementById("CodPac").value;
	var CodUsuario=document.getElementById("txtCodusuario").value;
	var Hora=document.getElementById("txtHora").value;
	var Fecha=document.getElementById("txtFecha").value;
	var CodHorarioMedico=document.getElementById("CodHorarioMedico").value;	
	if(CodPaciente==""){
		alert("No Se Ha Seleccionado Ningun Paciente!!!");
	}		
	if(CodPaciente!=""){
		if (confirm("Desea Asignarle el Formato al Paciente?")) {
		ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				
				document.getElementById('FormatosPaciente').innerHTML = ajax.responseText;
				CargarFormatosCE();
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=20&CodFormato="+CodFormato+"&CodigoPaciente="+CodPaciente+"&CodUsuario="+CodUsuario+"&hora="+Hora+"&fecha="+Fecha+"&CodHorarioMedico="+CodHorarioMedico); //Posting txtname to Servlet
		
		}else{document.getElementById("btnVerFormatos").disabled=false;}
	}	
}

function MostrarAtencionCE(CodHorarioMedico){
	
	DivValor=document.getElementById('FormatosAnteriores');
	ajax=getXMLObject();
	    ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=5.2&CodHorarioMedico="+CodHorarioMedico); //Posting txtname to Servlet
}

function CargarFormatosCE(){	
	var CodPac=document.getElementById("CodPac").value;
	var CodHorarioMedico=document.getElementById("CodHorarioMedico").value;
	var CodUsuario=document.getElementById("txtCodusuario").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita");
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById('FormatosPaciente').innerHTML = ajax.responseText;
			document.getElementById("btnVerFormatos").disabled=false;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	//alert("valor=21&CodigoPaciente="+CodPac+"&CodHorarioMedico="+CodHorarioMedico+"&CodUsuario="+CodUsuario);
	ajax.send("valor=21&CodigoPaciente="+CodPac+"&CodHorarioMedico="+CodHorarioMedico+"&CodUsuario="+CodUsuario);
}

function VerAreasCE(CodFormato,dia,mes,anio,horas,minutos,segundos,CodHorarioMedico){
	
	var Fecha=anio+"-"+mes+"-"+dia;
	var Hora=horas+":"+minutos+":"+segundos;
	ajax=getXMLObject();
	//ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			document.getElementById('areas').innerHTML = ajax.responseText;
			var div =document.getElementById("formulario");
			div.style.display='none';
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	//alert("valor=22&CodFormato="+CodFormato+"&hora="+Hora+"&fecha="+Fecha+"&CodHorarioMedico="+CodHorarioMedico); //Posting txtname to Servlet
	ajax.send("valor=22&CodFormato="+CodFormato+"&hora="+Hora+"&fecha="+Fecha+"&CodHorarioMedico="+CodHorarioMedico); //Posting txtname to Servlet
	//ajax.send("valor=0&CodFormato="+CodFormato+"&HoraFormato="+Hora+"&FechaFormato="+Fecha); //Posting txtname to Servlet
		   //var x;
}

function mostarpreguntasCE(CodArea,CodHorarioMedico){
	//alert("6");
	var CodPac=document.getElementById("CodPac").value;
	var FechaFormato=document.getElementById("FechaFormato").value;
	var HoraFormato=document.getElementById("HoraFormato").value;
	var CodUsuario=document.getElementById("txtCodusuario").value;
	//alert("CodPac "+CodPac+" FechaFormato "+FechaFormato+" HoraFormato "+HoraFormato+" CodUsuario "+CodUsuario);
	var e;
	ajax=getXMLObject();
	//ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			div =document.getElementById('formulario');
			div.style.display = '';
			var resul=ajax.responseText;
			for(e=0;e<resul.length;e++){
				resul=resul.replace('@', 'ñ');
			}
			document.getElementById('formulario').innerHTML =resul;
			
		}
	};
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	//alert("valor=23&CodArea="+CodArea+"&CodPac="+CodPac+"&fecha="+FechaFormato+"&hora="+HoraFormato+"&CodUsuario="+CodUsuario+"&CodHorarioMedico="+CodHorarioMedico); //Posting txtname to Servlet
	ajax.send("valor=23&CodArea="+CodArea+"&CodPac="+CodPac+"&fecha="+FechaFormato+"&hora="+HoraFormato+"&CodUsuario="+CodUsuario+"&CodHorarioMedico="+CodHorarioMedico); //Posting txtname to Servlet
	//ajax.send("valor=1&CodArea="+CodArea+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&FechaFormato="+FechaFormato+"&HoraFormato="+HoraFormato+"&CodUsuario="+CodUsuario); //Posting txtname to Servlet
}

function IngresarDiagnosticoInicialCEf(){
	//alert("11f");
	ajax=getXMLObject();
	var CodResul=document.getElementById("txtCodResultado").value;
	var Resul=document.getElementById("txtNomDiagnos").value;
	var Resul1=document.getElementById("txtNomDiagnos1").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value; 
	Resul=Resul+"-"+CodDiagnostico;
	//var Codi=Resul+"-"+CodDiagnostico;
	Resul=Resul1+"-->"+Resul;
	//alert("Resul"+Resul);
	
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		//alert("fdxdg "+ajax.responseText);	
		document.getElementById("DiaCE").innerHTML=ajax.responseText;
		///alert(Resul)
		Resul1=document.getElementById("txtNomDiagnos1").value=Resul;
		document.getElementById("txtCodDiagnostico").value="";
		document.getElementById("txtNomDiagnos").value="";			
	}
			
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=24&Resul="+Resul+"&CodResul="+CodResul); //Posting txtname to Servlet
	//alert("Ingreso Exitoso.")
	
}
function IngresarDiagnosticoInicialCE(){
	//alert("11");
	ajax=getXMLObject();	
	var CodResul=document.getElementById("txtCodResultado").value;
	var Resul=document.getElementById("txtNomDiagnos").value;
	var Resul1=document.getElementById("txtNomDiagnos1").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value; 
	Resul=Resul+"-"+CodDiagnostico;
	//var Codi=Resul+"-"+CodDiagnostico;
	Resul=Resul1+"-->"+Resul;
	
	if(CodDiagnostico==""){alert("Este Codigo no Existe En El CIE10.");}
	if(CodDiagnostico!=""){
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			//alert(ajax.responseText);	
			document.getElementById("DiaCE").innerHTML=ajax.responseText;
			///alert(Resul)
			Resul1=document.getElementById("txtNomDiagnos1").value=Resul;
			document.getElementById("txtCodDiagnostico").value="";
			document.getElementById("txtNomDiagnos").value="";			
		}
				
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=24&Resul="+Resul+"&CodResul="+CodResul); //Posting txtname to Servlet
		//alert("Ingreso Exitoso.")
	}
}

function ActualizarResultHistoriasCE(Resul,CodResul){

	    ajax=getXMLObject();	
		var Codi=encodeURIComponent(Resul)
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		ajax.send("valor=24&Resul="+Codi+"&CodResul="+CodResul); //Posting txtname to Servlet    
}
function ActualizarResultadosCE(){
	var c=document.getElementById('txtTotal').value;
	var oscar=c;
	var a;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			CodResul=document.form1.txtCodResultado[i].value;
			//Resul=encodeURIComponent(document.form1.txtRespuesta[i].value);
			Resul=document.form1.txtRespuesta[i].value;
			for(a=0;a<Resul.length;a++){
				Resul=Resul.replace('Ñ','@');
				Resul=Resul.replace('ñ','@');				
			}			
			ActualizarResultHistoriasCE(Resul,CodResul);
	      oscar--; 
	     }	
	 }else{	
		 Resul=document.getElementById("txtRespuesta").value;
		 for(e=0;e<Resul.length;e++){
				Resul=Resul.replace('Ñ','@');
				Resul=Resul.replace('ñ','@');				
			}
		 //CodResul=encodeURIComponent(document.getElementById("txtCodResultado").value);
		 CodResul=document.getElementById("txtCodResultado").value;
		 ActualizarResultHistoriasCE(Resul,CodResul);
	 }
}

function OmitirFormatoCE(CodForPacCE,CodHorarioMedico){
	
	var opcion=confirm("Desea Eliminar Este Formato?");
	
	if(opcion){
		ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				document.getElementById('FormatosPaciente').innerHTML = ajax.responseText;
				CargarFormatosCE();
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=25&CodForPacCE="+CodForPacCE+"&CodHorarioMedico="+CodHorarioMedico); //Posting txtname to Servlet
	}
	else{
	}	

}

function GuardarFormatoCE(CodForPacCE,dia,mes,anio,horas,minutos,segundos){
	var opcion=confirm("Desea Guardar Este Formato?");
	if(opcion){
	var FechaFormato=dia+"-"+mes+"-"+anio;
	var HoraFormato=horas+":"+minutos+":"+segundos;
	ajax=getXMLObject();	
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			if(validar!=""){
				alert(validar);
			}
			else{
				CargarFormatosCE();
			}
			//document.getElementById('').innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=26&CodForPacCE="+CodForPacCE+"&fecha="+FechaFormato+"&hora="+HoraFormato); //Posting txtname to Servlet
	}else{}
}
function ImprimirFormatoCExterna(usuario,CodFormato,FechaFormato,HoraFormato,CodPac,TipoFormato,CodHorarioMedico){
	
		pagina="agm_ImprimirFormatoCE.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario;			
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);	
}

function mostrarInformesHemodinamia(idcodInforme){
	
	pagina="cat_reporteCateterismo.jsp?CodInfCateterismo="+idcodInforme;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Informe_Cateterismo",opciones);
}

function mostarexamenesImagenes(codIce,usuario) {		
	pagina="img_ReporteImg.jsp?codIce="+codIce+"&usuario="+usuario			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}


function mostrarInformesRmc(idcodInforme){
	
	pagina="rmc_reporte_resonancia.jsp?CodInformeCardiologia="+idcodInforme;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Informe_Cardiologia",opciones);
}

function mostrarInformesCardiologia(idcodInforme){
	
	pagina="eco_reporte_cardiologia.jsp?CodInformeCardiologia="+idcodInforme;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Informe_Cardiologia",opciones);
}

function ImprimirFormatoCE(usuario,CodFormato,anio,mes,dia,hora,minuto,segundo,CodPac,TipoFormato,CodHorarioMedico){
	
	var FechaFormato=anio+"-"+mes+"-"+dia
	var HoraFormato=hora+":"+minuto+":"+segundo
	if(TipoFormato=="2"){
		pagina="agm_ImprimirFormatoCEUnido.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario;			
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);
	}else{
		pagina="agm_ImprimirFormatoCE.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario;			
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);
	}
}

function IngresarDiagnosticoInicialCE(){
	ajax=getXMLObject();	
	var Resul=document.getElementById("txtNomDiagnos").value;
	var CodPac=document.getElementById("CodPac").value;
	var CodUsu=document.getElementById("txtCodusuario").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	if(CodDiagnostico==""){alert("Este Codigo no Existe En El CIE10.");}
	if(CodDiagnostico!=""){
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			//alert(ajax.responseText);
			document.getElementById("DxInCEX").innerHTML=ajax.responseText;
			document.getElementById("txtNomDiagnos").value="";
			document.getElementById("txtCodDiagnostico").value="";
			document.getElementById("txtNomDiagnos").focus();
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=DXICE&CodPac="+CodPac+"&CodUsu="+CodUsu+"&CodAdm="+CodAdm+"&CodDiagnostico="+CodDiagnostico); //Posting txtname to Servlet
	}
}

function AsignarDiagRelCE (){
	ajax=getXMLObject();
	var CodDiagnosticoRela2=document.getElementById("txtCodDiagnosticoRela2").value;
	var TipoDiagRel2=document.getElementById("txtTipoDiagRel2").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;
	var CodUsu=document.getElementById("txtCodusuario").value;
	if(CodDiagnosticoRela2==""){
		alert("Seleccione El Diagnostico Relacionado");
	}
	if(CodDiagnosticoRela2!=""){
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		
		document.getElementById("CXDIRE").innerHTML=ajax.responseText;
		document.getElementById("txtNomDiagnosRela2").value="";
		document.getElementById("txtCodDiagnosticoRela2").value="";
		document.getElementById("txtNomDiagnosRela2").focus();
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=DXRCE&CodDiagnostico="+CodDiagnosticoRela2+"&TipoDiag="+TipoDiagRel2+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&CodUsu="+CodUsu); //Posting txtname to Servlet
	}
}

function AsignarDiagEgCE (){
	ajax=getXMLObject();
	var CodDiagnosticoEgreso=document.getElementById("txtCodDiagnosticoEgreso").value;
	var TipoDiagEG=document.getElementById("txtTipoDiagEG").value;	
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;
	var CodUsu=document.getElementById("txtCodusuario").value;
	if(CodDiagnosticoEgreso==""){
		alert("Seleccione El Diagnostico de Egreso.");
	}
	if(CodDiagnosticoEgreso!=""){
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		document.getElementById("CXDIEG").innerHTML=ajax.responseText;
		document.getElementById("txtNomDiagnosRelaEgreso").value="";
		document.getElementById("txtCodDiagnosticoEgreso").value="";
		document.getElementById("txtNomDiagnosRelaEgreso").focus(); 	
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=DXRCE&CodDiagnostico="+CodDiagnosticoEgreso+"&TipoDiag="+TipoDiagEG+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&CodUsu="+CodUsu); //Posting txtname to Servlet
	}
}
function OmitirDxI(coddxi){
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			VerFinalAtencionCE();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=odi&coddxi="+coddxi); //Posting txtname to Servlet
}

function OmitirDxE(coddxe){
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			VerFinalAtencionCE();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ode&coddxe="+coddxe); //Posting txtname to Servlet
}
function VerFinalAtencionCE(){
var CodHorarioMedico=document.getElementById("CodHorarioMedico").value;
var CodAdm=document.getElementById("CodAdm").value;
DivValor=document.getElementById('HistoriaPaciente');
		ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=27&CodHorarioMedico="+CodHorarioMedico+"&CodAdm="+CodAdm); //Posting txtname to Servlet
}
function FinalAtencionCE(){
	var opcion=confirm("Desea Finalizar Esta Atencion?");	
	
	 
		if(opcion){
			
			var CodHorarioMedico=document.getElementById("CodHorarioMedico").value;
			
			var CodAdm=document.getElementById("CodAdm").value;
			var CodPac=document.getElementById("CodPac").value;
			var finacons=document.getElementById("cmbfinalidadcons").value;
			var cauext=document.getElementById("cmbcausaexterna").value;
			var tipdiag=document.getElementById("cmbtipdiag").value;
			var CodUsu=document.getElementById("txtCodusuario").value;
			ajax=getXMLObject();
			ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if(ajax.readyState == 4){
					var da=ajax.responseText;
					if(da=="-1"){
						alert("No Tiene Diagnostico de Egreso diligenciado.");
						document.getElementById("txtNomDiagnosRelaEgreso").focus();
					}else{
						alert(da);
						window.location.href("agm_ListaMedico.jsp");
					}
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=28&CodHorarioMedico="+CodHorarioMedico+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&finacons="+finacons+"&cauext="+cauext+"&tipdiag="+tipdiag+"&CodUsu="+CodUsu); //Posting txtname to Servlet
		}
		else{
			//alert("valor=28&CodHorarioMedico="+CodHorarioMedico+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&finacons="+finacons+"&cauext="+cauext+"&tipdiag="+tipdiag+"&CodUsu="+CodUsu);
		}
	}

/************************************************** REALIZAR AIEPI ***********************************************/
function realizarAiepi(){
	DivValor=document.getElementById('HistoriaPaciente');
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPaciente=document.getElementById("CodPac").value;
	ajax=getXMLObject();
    ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
    ajax.onreadystatechange  = function(){
    	if (ajax.readyState == 4) {			
    		Contenido=ajax.responseText;
			DivValor.innerHTML=Contenido;
  	   	}
	  };
  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=10.0&CodUsuario="+usuario+"&CodiPaciente="+CodPaciente); //Posting txtname to Servlet
}

 function p() {
	var imc2="";
	var talla2="";
	talla=document.getElementById("talla").value;
	peso=document.getElementById("peso").value;
	talla2=(talla/100);
	imc2=(peso / (talla2 * talla2));
	//alert("peso: "+peso+" ,talla: "+talla+" , talla2: "+talla2+", imc2: "+imc2);
	document.getElementById("imc").value=imc2;
}
 
 function consultarAiepi(){
		DivValor=document.getElementById('HistoriaPaciente');
		CodPaciente=document.getElementById("CodPac").value;
		ajax=getXMLObject();
		    ajax.open("POST","AiepiMultiplePacientes",true); //getname will be the servlet name
		    ajax.onreadystatechange  = function(){
		    	if (ajax.readyState == 4) {				
		    		Contenido=ajax.responseText;
					DivValor.innerHTML=Contenido;
		  	   	}
			  };
		  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=4.5&CodPaciente="+CodPaciente); //Posting txtname to Servlet
		}