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
/***********************ABRIR LOS PDF**************************/
function Abrir(ano,mes,dia,hora,minuto,segundo,codpa,codexa,codgenero) {	
	var fecha=ano+"-"+mes+"-"+dia
	var horas=hora+":"+minuto+":"+segundo			
	pagina="Lab_Reporte_Grupo.jsp?hora="+horas+"&fecha="+fecha+"&subarea="+codexa+"&codpac="+codpa+"&codge="+codgenero+"";			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);
}

function Abrir_ventana (ano,mes,dia,hora,minuto,segundo,tipo,exa,codpa,codres,valorini,valorfinal) {

	fecha=ano+"-"+mes+"-"+dia
	horas=hora+":"+minuto+":"+segundo		
	if(tipo=="2"){
		pagina="Lab_Reporte_Rango.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+"&valorini="+valorini+"&valorfi="+valorfinal+"";			
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);
	}else{
		pagina="Lab_Reporte_Texto.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+"";
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);
	}	
}

function mostarexamenes (codIce,usuario) {		
	pagina="img_ReporteImg.jsp?codIce="+codIce+"&usuario="+usuario;			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);		
}
/***********************FIN DE ABRIR LOS PDF*******************/
function ListaEspera(){
	var xmlhttp = new getXMLObject();
	if(xmlhttp) { 	
		xmlhttp.open("POST","ControladorMedico",true); //getname will be the servlet name
		xmlhttp.onreadystatechange=function(){
			if (xmlhttp.readyState == 4) {
				var lista=xmlhttp.responseText;
				document.getElementById('document').innerHTML = lista;
			}
		};
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send("valor=0"); //Posting txtname to Servlet
	}
}

function ListaPorAdmitir(){
	var xmlhttp = new getXMLObject();
	if(xmlhttp) { 	
		xmlhttp.open("POST","ControladorMedico",true); //getname will be the servlet name
		xmlhttp.onreadystatechange=function(){
			if (xmlhttp.readyState == 4) {
				var lista=xmlhttp.responseText;
				document.getElementById('document').innerHTML = lista;
			}
		};
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send("valor=12"); //Posting txtname to Servlet
	}
}

function OmitirPendiente(CodColaFin){
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
    	if (ajax.readyState == 4) {				
    		window.location.reload();
    	}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=13&CodColaFin="+CodColaFin); //Posting txtname to Servlet
}

function Atender(CodAdmCola,CedPac){
	window.location.href="Adm_FormatosTriage.jsp?CodAdmCola="+CodAdmCola+"&CedPac="+CedPac;
}

function cargarFormatosTriage(){	
	var xmlhttp = new getXMLObject();
	CodAdmCola=document.getElementById("txtCodAdmCola").value;	
	CedPac=document.getElementById("txtCedPac").value;
	if(xmlhttp) {
		xmlhttp.open("POST","ControladorMedico",true); //getname will be the servlet name
		xmlhttp.onreadystatechange=function(){
			if (xmlhttp.readyState == 4) {
				document.getElementById('document').innerHTML = xmlhttp.responseText;
			}
		};			
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send("valor=1&CodAdmCola="+CodAdmCola+"&CedPac="+CedPac); //Posting txtname to Servlet
	}
}
/********************************INICIO DE MEDICAMENTOS*********************************/
function OrdenesMedicasTriage(){
	DivValor=document.getElementById('HistoriaPaciente');
	CedPac=document.getElementById("txtCedPac").value;
	CodAdmCola=document.getElementById("txtCodAdmCola").value;	
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			Contenido=ajax.responseText;
			DivValor.innerHTML=Contenido;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Medi&CedPac="+CedPac+"&CodAdmCola="+CodAdmCola); //Posting txtname to Servlet
	
}

function BuscarTipoMediTr(){	
	var Tipo=document.getElementById("txtTipo").value;
	ajax=getXMLObject();
	Contenido=document.getElementById('DetAdministra');
	var CodMedicamento=document.getElementById("txtCodigoMed").value;
	var CodFormulacion_fk=document.getElementById("txtCodFormulacion").value;
	if(Tipo=="0"){		
		ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
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
function CalcularCantidad(){
	var CantidadDosis=document.getElementById("cmbCantidad").value;
	var Lapso=document.getElementById("cmbLapso").value;
	var Administracion=document.getElementById("cmbViaAdmi").value;
	if(CantidadDosis=="Seleccione"){
		document.getElementById("txtCantidad").value="";
		//document.getElementById("txtObservacion").value="";
	}
	else{
		if(Lapso=="Seleccione"){
			document.getElementById("txtCantidad").value="";
			//document.getElementById("txtObservacion").value="";
		}
		else{
			if(Administracion=="Seleccione"){
				//document.getElementById("txtObservacion").value="";
			}
		}
	}
	if((CantidadDosis!="Seleccione")&&(Lapso!="Seleccione")){
		var Tiempo= 24 / Lapso;
		var CantidadTotal=CantidadDosis * Tiempo;
		document.getElementById("txtCantidad").value=CantidadTotal;
	}
	
	if((CantidadDosis!="Seleccione")&&(Lapso!="Seleccione")&&(Administracion!="Seleccione")){
		var Dosificacion="Dar "+CantidadDosis+" Cada "+Lapso+" Horas. "+Administracion;
		//document.getElementById("txtObservacion").value=Dosificacion;
	}
}

function AsignarMediTr(){
	/************FORMULACION*******************/
	var codCita=document.getElementById("txtCodCola").value;	
	var usuario=document.getElementById("txtUsuario").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	//falta una observacion de formulacion.
	/*******DETALLE FORMULACION****************/
	var CodFormulacion_fk=document.getElementById("txtCodFormulacion").value;
	var CodigoMed=document.getElementById("txtCodigoMed").value;
	var Cantidad=document.getElementById("txtCantidad").value;
	///////////////////////////////////////
	var CantidadDosis=document.getElementById("cmbCantidad").value;
	var Lapso=document.getElementById("cmbLapso").value;
	var Administracion=document.getElementById("cmbViaAdmi").value;
	///////////////////////////////////////
	var ConteForm=document.getElementById("ConteForm");
	var DetOrden=encodeURIComponent(document.getElementById("txtDetOrden").value);
	var Dosificacion="";
	///////////////////////////////////////
	if(CantidadDosis=="Seleccione"){
		alert("Seleccione Cantidad De La Dosis.");
	}else{
		if(Lapso=="Seleccione"){
			alert("Seleccione Lapso De Tiempo.");
		}else{
			if(Administracion=="Seleccione"){
				alert("Seleccione Tipo De Administracion.");
			}
		}
	}
	///////////////////////////////////////
	var Observacion=document.getElementById("txtObservacion").value;
	if((CantidadDosis!="Seleccione")&&(Lapso!="Seleccione")&&(Administracion!="Seleccione")){
		Dosificacion="Dar "+CantidadDosis+" Cada "+Lapso+" Horas. "+Administracion;
		//document.getElementById("txtObservacion").value=Dosificacion;
	}
	if((CantidadDosis=="")&&(Lapso=="")&&(Administracion=="")){
		Dosificacion="";
	}
	//alert("valor=99&codCita="+codCita+"&usuario="+usuario+"&hora="+hora+"&fecha="+fecha+"&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden);
	//////////////////////////////////////////////////////////////
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	/////////////////////////////////////////////////////////////
	if(CodFormulacion_fk==""){
		// si es primer medicamento.
		if((Cantidad!="")&&(Administracion!="Seleccione")){
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
		ajax.send("valor=99&codCita="+codCita+"&usuario="+usuario+"&hora="+hora+"&fecha="+fecha+"&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden); //Posting txtname to Servlet
		}
	}
	
	if(CodFormulacion_fk!=""){
		// de segundo medicamento en adelante.
		if((Cantidad!="")&&(Administracion!="Seleccione")){
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
		}
	}
	
}
/*******************************FIN DE MEDICAMENTOS***************************************/
/*************************IMAGENOLOGIA******************************/
function MostrarImagenologia(){
	DivValor=document.getElementById('HistoriaPaciente');
	CedPac=document.getElementById("txtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			Contenido=ajax.responseText;
			DivValor.innerHTML=Contenido;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=imag&CedPac="+CedPac); //Posting txtname to Servlet
}
/*************************FIN DE IMAGENOLOGIA******************************/
/*************************LABORATORIO***********************/
function MenuLaboratorio(){
	DivValor=document.getElementById('HistoriaPaciente');
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			Contenido=ajax.responseText;
			DivValor.innerHTML=Contenido;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=menulab"); //Posting txtname to Servlet
}
function Radio() {		
	ajax=getXMLObject();
	var contenido=document.getElementById('ContenidoLaboratorio');
	var radioButtons = document.getElementsByName("radiobutton");	
	var CedPac=document.getElementById("txtCedPac").value;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&CedPac="+CedPac); //Posting txtname to Servlet
		}	     
	}
}
function historial(CodExamen,CedPac){
	ajax=getXMLObject();
	var DivValor=document.getElementById("HistorialLaboratorios");
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			Contenido=ajax.responseText;
			DivValor.innerHTML=Contenido;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=hstlab&CedPac="+CedPac+"&CodExamen="+CodExamen); //Posting txtname to Servlet
}
function mostrarHistorial(){
	var c=document.getElementById('txtContador').value;
	var CedPac=document.getElementById("txtCedPac").value;
	var oscar=c;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			if (document.form1.chkExamenes[i].checked) {
				CodExamen=document.form1.chkExamenes[i].value;       	 
				historial(CodExamen,CedPac);
			}     	  
			oscar--; 
		}	
	} 
	else{
		if(document.form1.chkExamenes.checked){
			CodExamen=document.form1.chkExamenes.value;
			historial(CodExamen,CedPac);
		}
	}
}
/*************************FIN LABORATORIO***********************/

/********************ANTECEDENTES*********/
function Antecedentes(){
	DivValor=document.getElementById('HistoriaPaciente');
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			Contenido=ajax.responseText;
			DivValor.innerHTML=Contenido;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=antecedentes"); //Posting txtname to Servlet
}
function RadioAntecedentes() {	
	ajax=getXMLObject();	
	var contenido=document.getElementById('Antecedente');
	var radioButtons = document.getElementsByName("radiobutton");
	var CedPac=document.getElementById("txtCedPac").value;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;
					// alert(ajax.responseText);
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&CedPac="+CedPac); //Posting txtname to Servlet
		}	     
	}
}
/********************************PERSONALES*************************/
function DiagnosticoPaciente(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtUsuario").value;
	var CedPac=document.getElementById("txtCedPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	var observacion=document.getElementById("txtObservacioDiagnostico").value;
	var CodAdmCola=document.getElementById("txtCodAdmCola").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CedPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}
	if(CodDiagnostico==""){
		alert("El Diagnostico No Puede Ir Vacio.");
	}
	if((usuario!="null")&&(CedPac!="")&&(hora!="")&&(fecha!="")&&(CodDiagnostico!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControladorMedico",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarDiagnostico();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=6&CedPac="+CedPac+"&CodDiagnostico="+CodDiagnostico+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&observacion="+observacion+"&CodAdmCola="+CodAdmCola); //Posting txtname to Servlet
		}
	}
}
function CargarDiagnostico(){	
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("txtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=perso&CedPac="+CedPac); //Posting txtname to Servlet
}

function Observacion (CodDiag) {		
	pagina="hic_ObserDiagnostico.jsp?CodDiag="+CodDiag;			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=yes, width=400, height=200, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);		
}

/**************************FIN DE PERSONALES************************/
/***************************QUIRURJICOS************************/
function IngresarProcedimiento(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtUsuario").value;
	var CodPac="null";
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CedPac=document.getElementById("txtCedPac").value;
	var txtFechaCx=document.getElementById("txtFechaCx").value;
	var txtObservacionCx=document.getElementById("txtObservacionCx").value;
	var txtCodigoQx=document.getElementById("txtCodigoQx").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	if(txtFechaCx==""){
		alert("No Hay Ingresado La fecha de la Cirujia.");
	}
	if(txtObservacionCx==""){
		alert("No Hay Ingresado Ninguna Observacion.");
	}
	if(txtCodigoQx==""){
		alert("No Hay Seleccionado Ningun Procedimiento.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(txtFechaCx!="")&&(txtObservacionCx!="")&&(txtCodigoQx!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControladorMedico",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarProcedimiento();
				}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngrQx&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&fechaQx="+txtFechaCx+"&observacion="+txtObservacionCx+"&codigoQx="+txtCodigoQx+"&CedPac="+CedPac); //Posting txtname to Servlet
		}
	}
}
 
function CargarProcedimiento(){
	var contenido=document.getElementById('Antecedente');
	var CodPac=document.getElementById("txtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=qx&CedPac="+CedPac); //Posting txtname to Servlet
}


/***************************FIN QUIRURJICOS ************************/
/****************************ALERGICOS*******************************/
function CargarAlergias(){	
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("txtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=alerg&CedPac="+CedPac); //Posting txtname to Servlet
}
function IngresaAlergia(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtUsuario").value;
	var CodPac="null";
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var Alergia=document.getElementById("txtAlergia").value;
	var Reaccion=document.getElementById("txtReaccion").value;
	var CedPac=document.getElementById("txtCedPac").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	if(Alergia==""){
		alert("No Hay Ingresado La Alergia.");
	}
	if(Reaccion==""){
		alert("No Hay Ingresado La Reaccion.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(Alergia!="")&&(Reaccion!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControladorMedico",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarAlergias();
				}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngreAler&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Alergia="+Alergia+"&Reaccion="+Reaccion+"&CedPac="+CedPac); //Posting txtname to Servlet
		}
	}
}
/****************************FIN ALERGICOS*******************************/
/*************************** TOXICOLOGIA ***************************/
function IngresarToxico(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtUsuario").value;
	var CodPac="null";
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var Toxicologia=document.getElementById("cmbToxicologia").value;
	var ObservacionTx=document.getElementById("txtObservacionTx").value;
	var CedPac=document.getElementById("txtCedPac").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	if(Toxicologia=="Seleccione"){
		alert("No Hay Seleccionado La Toxicologia.");
	}
	if(ObservacionTx==""){
		alert("No Hay Ingresado Ninguna Observacion.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(Toxicologia!="")&&(ObservacionTx!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControladorMedico",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarToxicologia();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngTox&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&tipoTx="+Toxicologia+"&ObservacionTx="+ObservacionTx+"&CedPac="+CedPac); //Posting txtname to Servlet
		}
		
	}
}
 
function CargarToxicologia(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("txtCedPac").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=tx&CedPac="+CedPac); //Posting txtname to Servlet
}



/***************************FIN TOXICOLOGIA ************************/
/****************************FAMILIARES*******************************/
function IngresaAntFamiliares(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtUsuario").value;
	var CodPac="null";
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var Diagnostico=document.getElementById("txtCodDiagnostico").value;
	var ObserFami=document.getElementById("txtObseFami").value;
	var familiar=document.getElementById("cmbFamilia").value;
	var CedPac=document.getElementById("txtCedPac").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	if(familiar=="Seleccion"){
		alert("No Ha Seleccionado Ningun Familiar.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(familiar!="Seleccion")){
		if(xmlhttp){
			xmlhttp.open("POST","ControladorMedico",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarAnteFamiliar();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngreFami&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Diagnostico="+Diagnostico+"&ObserFami="+ObserFami+"&familiar="+familiar+"&CedPac="+CedPac); //Posting txtname to Servlet
		}
	}
}

function CargarAnteFamiliar(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("txtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=fami&CedPac="+CedPac); //Posting txtname to Servlet
}

function MostrarObserFami(CodAntFami){
	pagina="hic_ObserAnteFami.jsp?CodAntFami="+CodAntFami;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=400, height=200, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}
/****************************FIN FAMILIARES*******************************/
/*************************** TRANSFUCIONES ***************************/
function RadioTrans() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('OpcTransf');
	var radioButtons = document.getElementsByName("radioTrans");
	//var CodPac=document.getElementById("CodPac").value;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
	         ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
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

function GuardarSiTrans(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtUsuario").value;
	var CodPac="null";
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CedPac=document.getElementById("txtCedPac").value;
	var transfucion="Si";
	var ObservacionTr=document.getElementById("txtObservacionTr").value;
	var Cantidad=document.getElementById("txtCantidad").value;
	var FechaTransfucion=document.getElementById("txtFechaTransfucion").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	if(Cantidad==""){
		alert("No Hay Ingresado La Cantidad.");
	}
	if(FechaTransfucion==""){
		alert("No Hay Ingresado La Fecha De Transfucion.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(Cantidad!="")&&(FechaTransfucion!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControladorMedico",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarTransfucion();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngTran&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Cantidad="+Cantidad+"&FechaTransfucion="+FechaTransfucion+"&transfucion="+transfucion+"&ObservacionTr="+ObservacionTr+"&CedPac="+CedPac); //Posting txtname to Servlet
		}
		
	}
}

function CargarTransfucion(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("txtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=trans&CedPac="+CedPac); //Posting txtname to Servlet
}

function GuardarNoTrans(){
	//alert()
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtUsuario").value;
	var CodPac="null";
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CedPac=document.getElementById("txtCedPac").value;
	var transfucion="No";
	var ObservacionTr="No Se Le Han Hecho Transfuciones De Sanguineas";
	var Cantidad="Ninguna";
	var FechaTransfucion="No Refiere";
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}		
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControladorMedico",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarTransfucion();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngTran&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Cantidad="+Cantidad+"&FechaTransfucion="+FechaTransfucion+"&transfucion="+transfucion+"&ObservacionTr="+ObservacionTr+"&CedPac="+CedPac); //Posting txtname to Servlet
		}
		
	}
}

/***************************FIN TRANSFUCIONES ************************/
/***************************MEDICAMENTOS************************/
function IngresarMd(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtUsuario").value;
	var CodPac="null";
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CedPac=document.getElementById("txtCedPac").value;
	var txtObservacionMd=document.getElementById("txtObservacionMd").value;
	var txtCodigoMed=document.getElementById("txtCodigoMed").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	
	if(txtObservacionMd==""){
		alert("No Hay Ingresado Ninguna Observacion.");
	}
	if(txtCodigoMed==""){
		alert("No Hay Seleccionado Ningun Procedimiento.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(txtObservacionMd!="")&&(txtCodigoMed!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControladorMedico",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarMedicamentos();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngMed&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&observacion="+txtObservacionMd+"&codigoMed="+txtCodigoMed+"&CedPac="+CedPac); //Posting txtname to Servlet
		}
		
	}
}
 
function CargarMedicamentos(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("txtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=medi&CedPac="+CedPac); //Posting txtname to Servlet
}


/***************************FIN MEDICAMENTOS ************************/
/*********************FIN ANTECEDENTES****************/
/*********************formatos************************/
/*function FinalizarAtencion(){
	var xmlhttp = new getXMLObject();
	var CodAdmCola=document.getElementById("txtCodAdmCola").value;	
		xmlhttp.open("POST","ControladorMedico",true); //getname will be the servlet name
		xmlhttp.onreadystatechange  = function(){
			if (xmlhttp.readyState == 4) {
				alert(xmlhttp.responseText);
				window.location.href="Adm_ListaMedico.jsp";
	    	}
		};
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send("valor=8&CodAdmCola="+CodAdmCola); //Posting txtname to Servlet
}*/


function Formatos(){
	DivValor=document.getElementById('HistoriaPaciente');
	var CodigoUsuario=document.getElementById("txtUsuario").value;
	ajax=getXMLObject();
	    ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
	    		//alert(Contenido)
				DivValor.innerHTML=Contenido;
				cargarFormatosCola();
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=formatos&CodigoUsuario="+CodigoUsuario); //Posting txtname to Servlet
	    
}

function cargarFormatosCola(){
	var CodAdmCola=document.getElementById("txtCodCola").value;
	var CedPac=document.getElementById("txtCedPac").value;
	var usuario=document.getElementById("txtUsuario").value;
	//alert("Formatos Cargados Exitosamente.");
	xmlhttp = new getXMLObject();
	xmlhttp.open("POST","ControladorMedico",true);
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4){			
			document.getElementById("FormatosPaciente").innerHTML=xmlhttp.responseText;
		}
	};
	xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	xmlhttp.send("valor=5&CodAdmCola="+CodAdmCola+"&CedPac="+CedPac+"&CodUsu="+usuario); //Posting txtname to Servlet
}

function AsignarFormato(){
	
	var xmlhttp = new getXMLObject();
	var CodAdmCola=document.getElementById("txtCodAdmCola").value;
	var CedPac=document.getElementById("txtCedPac").value;
	var CodFormato=document.getElementById("txtCodFormato").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtUsuario").value;
	
	xmlhttp.open("POST","ControladorMedico",true); //getname will be the servlet name
	xmlhttp.onreadystatechange  = function(){
		if(xmlhttp.readyState == 4){
			//document.getElementById('FormatosPaciente').innerHTML = xmlhttp.responseText;
			cargarFormatosCola();
		}
	};
	xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	xmlhttp.send("valor=2&CodAdmCola="+CodAdmCola+"&CedPac="+CedPac+"&CodFormato="+CodFormato+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario); //Posting txtname to Servlet
		
		
}

function VerAreas(CodFormato,dia,mes,anio,horas,minutos,segundos){	
	var xmlhttp = new getXMLObject();
	var Fecha=anio+"-"+mes+"-"+dia;
	var Hora=horas+":"+minutos+":"+segundos;

		    xmlhttp.open("POST","ControladorMedico",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = function(){
		    	if (xmlhttp.readyState == 4) {
		    		document.getElementById('areas').innerHTML = xmlhttp.responseText;
		    		var div =document.getElementById("formulario");
					div.style.display='none';
		    	}
		    };
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send("valor=3&CodFormato="+CodFormato+"&HoraFormato="+Hora+"&FechaFormato="+Fecha); //Posting txtname to Servlet

}

function mostarpreguntas(CodArea){
	var xmlhttp = new getXMLObject();
	var CodAdmCola=document.getElementById("txtCodAdmCola").value;
	var CedPac=document.getElementById("txtCedPac").value;
	var FechaFormato=document.getElementById("FechaFormato").value;
	var HoraFormato=document.getElementById("HoraFormato").value;
	var usuario=document.getElementById("txtUsuario").value;
	var e;

	    xmlhttp.open("POST","ControladorMedico",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = function(){
	    	if (xmlhttp.readyState == 4) {
	    		div = document.getElementById('formulario');
				div.style.display = '';
				var resul=xmlhttp.responseText;
				for(e=0;e<resul.length;e++){
					resul=resul.replace('@', 'ñ');
				}				
				//alert(resul);
				document.getElementById('formulario').innerHTML = resul;
				
	    	}
	    	
	    };
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send("valor=4&CodArea="+CodArea+"&CodAdmCola="+CodAdmCola+"&CedPac="+CedPac+"&FechaFormato="+FechaFormato+"&HoraFormato="+HoraFormato+"&usuario="+usuario); //Posting txtname to Servlet

	
}

function IngresarDiagnosticoSolo(){

	ajax=getXMLObject();	
	var CodResul=document.getElementById("txtCodResultado5").value;
	var Resul=document.getElementById("txtNomDiagnos").value;
	var CodPac=document.getElementById("txtCedPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtUsuario").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	var Resul1=document.getElementById("txtResult1").value; 
	
		Resul=Resul1+"-->"+Resul;
		//alert("Resul "+Resul+" Resul1 "+Resul1);
	if(CodDiagnostico==""){alert("Este Codigo no Existe En El CIE10.");}
	if(CodDiagnostico!=""){
		ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			//alert(ajax.responseText);
			document.getElementById("DiagnosticoIniciales").innerHTML=ajax.responseText;
			Resul1=document.getElementById("txtResult1").value=Resul+"-"+CodDiagnostico;
			document.getElementById("txtCodDiagnostico").value="";
			document.getElementById("txtNomDiagnos").value="";
			
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		//alert("valor=1.1&Resul="+Resul+"&CodResul="+CodResul+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&CodDiagnostico="+CodDiagnostico); //Posting txtname to Servlet
		ajax.send("valor=1.1&Resul="+Resul+"&CodResul="+CodResul+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&CodDiagnostico="+CodDiagnostico); //Posting txtname to Servlet
	}
}
/////////////////////////////////////////////////////////////////////////////////
function ActualizarResultHistorias(Resul,CodResul){
	var xmlhttp = new getXMLObject();	
		var hora=document.getElementById("HoraFormato").value;
		var fecha=document.getElementById("FechaFormato").value;
		var Codi=encodeURIComponent(Resul);
		xmlhttp.open("POST","ControladorMedico",true); //getname will be the servlet name
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xmlhttp.send("valor=7&Resul="+Codi+"&CodResul="+CodResul); //Posting txtname to Servlet    
}


function ActualizarResultados(){	
	var c=document.getElementById('txtTotal').value;
	var oscar=c;
	var e;
	if(c!=1){
		for(var i=0; i<=c-1; i++){			
			CodResul=document.form1.txtCodResultado[i].value;
			Resul=document.form1.txtRespuesta[i].value;
			for(e=0;e<Resul.length;e++){
				Resul=Resul.replace('Ñ','@');
				Resul=Resul.replace('ñ','@');				
			}
			ActualizarResultHistorias(Resul,CodResul);
			oscar--; 
	     }	
	 }else{	
		 Resul=document.getElementById("txtRespuesta").value;
		 for(e=0;e<Resul.length;e++){
				Resul=Resul.replace('Ñ','@');
				Resul=Resul.replace('ñ','@');				
			}
		 CodResul=document.getElementById("txtCodResultado").value;
		 ActualizarResultHistorias(Resul,CodResul);
	 }
}
function ImprimirFormato(usuario,CodFormato,anio,mes,dia,hora,minuto,segundo,CedPac){
	var FechaFormato=anio+"-"+mes+"-"+dia
	var HoraFormato=hora+":"+minuto+":"+segundo
	pagina="adm_ImprimirFormatosCola.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CedPac="+CedPac+"&usuario="+usuario;			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);
}

function GuardarFormato(CodigoFormatoPaciente,dia,mes,anio,horas,minutos,segundos){
	var opcion=confirm("Desea Guardar Este Formato?");
	if(opcion){
		var FechaFormato=dia+"-"+mes+"-"+anio;
		var HoraFormato=horas+":"+minutos+":"+segundos;	
		ajax=getXMLObject();	
		ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				cargarFormatosCola();
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=8&CodigoFormatoPaciente="+CodigoFormatoPaciente+"&FechaFormato="+FechaFormato+"&HoraFormato="+HoraFormato); //Posting txtname to Servlet    
	}
	else{}
}

function OmitirFormato(CodigoFormatoPaciente,anio,mes,dia,horas,minutos,segundos){
	var FechaFormato=anio+"-"+mes+"-"+dia;
	var HoraFormato=horas+":"+minutos+":"+segundos;	
	var opcion=confirm("Desea Eliminar Este Formato?");	
	if(opcion){
		ajax=getXMLObject();
		ajax.open("POST","ControladorMedico",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				cargarFormatosCola();
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=10&CodigoFormatoPaciente="+CodigoFormatoPaciente+"&FechaFormato="+FechaFormato+"&HoraFormato="+HoraFormato); //Posting txtname to Servlet
	}
	else{
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////
function FinalizarAtencion(){	
		var xmlhttp = new getXMLObject();
		xmlhttp.open("POST","ControladorMedico",true); //getname will be the servlet name
		xmlhttp.onreadystatechange  = function(){
			if (xmlhttp.readyState == 4) {
				document.getElementById('HistoriaPaciente').innerHTML = xmlhttp.responseText;
			}
		};
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send("valor=9"); //Posting txtname to Servlet
}

function DarSalida(){
	var opcion=confirm("Desea Finalizar Esta Atencion?");
	if(opcion){
		var CodAdmCola=document.getElementById("txtCodAdmCola").value;
		var CategoriaTriage=document.getElementById("cmbTipoTriage").value;
		var hora=document.getElementById("txtHora").value;
		var fecha=document.getElementById("txtFecha").value;
		var usuario=document.getElementById("txtUsuario").value;	
		var valorSalida="";
		var tipoSalida="";
		var estado="";
		var radiobutton = document.getElementsByName("radiobutton");
		for (var y = 0; y < radiobutton.length; y ++) {
			if (radiobutton[y].checked) {
				valorSalida=radiobutton[y].value;
				tipoSalida=radiobutton[y].id;
			}
		}
		if(tipoSalida=="1"){estado="3";}
		if(tipoSalida=="2"){estado="3";}
		if(tipoSalida=="3"){estado="0";}
		if(CategoriaTriage=="Seleccione"){
			alert("Seleccione Tipo Triage");
		}else{
			if(tipoSalida==""){
				alert("Seleccione Tipo Salida");
			}
		}
		if((CategoriaTriage!="Seleccione")&&(tipoSalida!="")){
			var xmlhttp = new getXMLObject();
			xmlhttp.open("POST","ControladorMedico",true); //getname will be the servlet name
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					alert( xmlhttp.responseText);
					window.location.href="Adm_ListaMedico.jsp";
				}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=11&CodAdmCola="+CodAdmCola+"&CategoriaTriage="+CategoriaTriage+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&valorSalida="+valorSalida+"&tipoSalida="+tipoSalida+"&estado="+estado); //Posting txtname to Servlet
		}
	}
	else{}
}

