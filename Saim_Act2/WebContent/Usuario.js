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
////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
function CargarPagina(){
	
	var Docu=document.getElementById("txtCedulaParametro").value;
	//alert(Docu);
	if(Docu==""){

	}else{
		document.getElementById("txtNumeroDocumento").value=Docu;
		document.getElementById("cmbTipoDoc").value="Cedula Ciudadania";
		BuscarUsuario();		
	}
}

function CargarListaUsuarios(){
	ajax=getXMLObject();
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("Resultado").innerHTML=ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CLU"); //Posting txtname to Servlet

}


function Radio() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('Creaciones');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					document.getElementById("Actualizaciones").innerHTML='';
					contenido.innerHTML = ajax.responseText	
					//VerActualizaciones(val);
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val); //Posting txtname to Servlet
		}	     
	}
}

function CambiarOcupacion(){
	//alert();
	var CodProfesion=document.getElementById("txtprofesion").value;	
	ajax=getXMLObject();
	ajax.open("POST","Control_Usuarios",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//var validar=ajax.responseText;
			document.getElementById("Ocupa").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=COU&CodProfesion="+CodProfesion);
}

function MedicoCEX(){
	var vuetpro = document.getElementById("txtprofesion").selectedIndex;
	var pro = document.getElementById("txtprofesion").options[vuetpro].text;
	if((pro=="Administracion")||(pro=="Seleccione")){
		alert("Seleccione una profesion del area asistencial.");
		document.getElementById("chkMedCex").checked=false;
		document.getElementById("txtprofesion").focus();
	}else{
		var e=document.getElementById("chkMedCex");
		if(e.checked){
			var p="Tiempo Consulta<input name='txtTiempoConsulta' type='text' id='txtTiempoConsulta' /><input name='Activo' type='hidden' id='Activo' value='1' />";
			document.getElementById("DetMedCex").innerHTML=p;
		}else{
			var a="<input name='Activo' type='hidden' id='Activo' value='0' />";
			document.getElementById("DetMedCex").innerHTML=a;
		}
	}
}

function BuscarUsuario(){
	var NumeroDocumento=document.getElementById("txtNumeroDocumento").value;
	var tipoDocumento=document.getElementById("cmbTipoDoc").value;
	if(tipoDocumento=="Seleccione"){
		alert("Seleccione Tipo Documento");
	}else{
		if(NumeroDocumento==""){
			alert("Digite El Numero de Documento.");
		}
	}
	if((tipoDocumento!="Seleccione")&&(NumeroDocumento!="")){
		ajax=getXMLObject();
		ajax.open("POST","Control_Usuarios",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//var validar=ajax.responseText;
				document.getElementById("Resultado").innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=-1&NumeroDocumento="+NumeroDocumento+"&tipoDocumento="+tipoDocumento);
	}
}
function GuardarDatosPersonales(){
	var nom=document.getElementById("txtnombre").value;
	var ape=document.getElementById("txtapellido").value;
	var dire=document.getElementById("txtdireccion").value;
	var tel=document.getElementById("txttelefono").value;
	var mail=document.getElementById("txtemail").value;
	/*******************************************************************/
	//var CodProfesion=document.getElementById("txtprofesion").value;
	var vuetpro = document.getElementById("txtprofesion").selectedIndex;
	var pro = document.getElementById("txtprofesion").options[vuetpro].text;
	
	var CodOcupacion=document.getElementById("txtocupacion").value;
	var vuetocu = document.getElementById("txtocupacion").selectedIndex;
	var ocu = document.getElementById("txtocupacion").options[vuetocu].text;
	/********************************************************************/
	
	var ced=document.getElementById("txtcedula").value;
	var NumeroDocumento=document.getElementById("txtNumeroDocumento").value;
	var estado=document.getElementById("cmbestado").value;
	var tipoDocumento=document.getElementById("cmbTipoDoc").value;
	
	var usu=document.getElementById("txtusuario").value;
	var contra=document.getElementById("txtcontrasena").value;
	var repcontra=document.getElementById("txtrepcontra").value;
	
	var Activo=document.getElementById("Activo").value;
	if(Activo=="1"){
		var Tconsulta=document.getElementById("txtTiempoConsulta").value;
	}
	if(Activo=="0"){
		Tconsulta="NA";
	}
	
	 var i;      
     for(i=0;i<dire.length;i++){
       dire=dire.replace('#','Nº');          
     }
	
	if(contra!=repcontra){
		alert("Las Contraseñas No Coinciden")
	}
	else{
		if((nom=="")||(ape=="")||(dire=="")||(tel=="")||(pro=="")||(usu=="")||(contra=="")||(repcontra=="")||(ced=="")||(estado=="Seleccione")){
			alert("Llene Todos Los Campos Requeridos.");
		}
		else{

			ajax=getXMLObject();
			ajax.open("POST","Control_Usuarios",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					//var validar=ajax.responseText;
					document.getElementById("OpcionSeguridad").innerHTML=ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=0&CodOcupacion="+CodOcupacion+"&Tconsulta="+Tconsulta+"&nom="+nom+"&ape="+ape+"&dire="+dire+"&tel="+tel+"&mail="+mail+"&pro="+pro+"&ocu="+ocu+"&usu="+usu+"&contra="+contra+"&ced="+ced+"&estado="+estado+"&NumeroDocumento="+NumeroDocumento+"&tipoDocumento="+tipoDocumento);
		}
	}
}

function ActualizarDatosUsuario(){
	var nom=document.getElementById("txtnombre").value;
	var ape=document.getElementById("txtapellido").value;
	var dire=document.getElementById("txtdireccion").value;
	var tel=document.getElementById("txttelefono").value;
	var mail=document.getElementById("txtemail").value;
	//var pro=document.getElementById("txtprofesion").value;
	//var ocu=document.getElementById("txtocupacion").value;
	var ced=document.getElementById("txtcedula").value;
	var NumeroDocumento=document.getElementById("txtNumeroDocumento").value;
	var estado=document.getElementById("cmbestado").value;
	var tipoDocumento=document.getElementById("cmbTipoDoc").value;
	var CodigoDatosPersonales=document.getElementById("txtCodigoDatosPersonales").value;
	
	/*******************************************************************/
	//var CodProfesion=document.getElementById("txtprofesion").value;
	var vuetpro = document.getElementById("txtprofesion").selectedIndex;
	var pro = document.getElementById("txtprofesion").options[vuetpro].text;
	
	var CodOcupacion=document.getElementById("txtocupacion").value;
	var vuetocu = document.getElementById("txtocupacion").selectedIndex;
	var ocu = document.getElementById("txtocupacion").options[vuetocu].text;
	
	var Activo=document.getElementById("Activo").value;
	if(Activo=="1"){
		var Tconsulta=document.getElementById("txtTiempoConsulta").value;
	}
	if(Activo=="0"){
		Tconsulta="NA";
	}
	
	/********************************************************************/
	//Activo
	
	ajax=getXMLObject();
	ajax.open("POST","Control_Usuarios",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Datos Actualizados Con Exito");
			BuscarUsuario();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	//alert("valor=ActPer&nom="+nom+"&ape="+ape+"&dire="+dire+"&tel="+tel+"&mail="+mail+"&pro="+pro+"&ocu="+ocu+"&ced="+ced+"&NumeroDocumento="+NumeroDocumento+"&estado="+estado+"&tipoDocumento="+tipoDocumento+"&CodigoDatosPersonales="+CodigoDatosPersonales+"&Tconsulta="+Tconsulta+"&Activo="+Activo+"&CodOcupacion="+CodOcupacion);
	ajax.send("valor=ActPer&nom="+nom+"&ape="+ape+"&dire="+dire+"&tel="+tel+"&mail="+mail+
			"&pro="+pro+"&ocu="+ocu+"&ced="+ced+"&NumeroDocumento="+NumeroDocumento+"&estado="+estado+
			"&tipoDocumento="+tipoDocumento+"&CodigoDatosPersonales="+CodigoDatosPersonales+
			"&Tconsulta="+Tconsulta+"&Activo="+Activo+"&CodOcupacion="+CodOcupacion);
}

function ActualizarUsuyContra(){
	var usu=document.getElementById("txtusuario").value;
	var contra=document.getElementById("txtcontrasena").value;
	var repcontra=document.getElementById("txtrepcontra").value;
	var CodUsuarioAct=document.getElementById("txtCodUsuarioAct").value;
	if(contra==""){
		alert("Digite La Contrase\u00f1a Anterior.");
	}else{
		if(repcontra==""){
			alert("Digite La Nueva Contrase\u00f1a");
		}
	}
	if((contra!="")&&(repcontra!="")){
		ajax=getXMLObject();
		ajax.open("POST","Control_Usuarios",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var validar=ajax.responseText;
				if(validar=="1"){
					alert("Contrase\u00f1a Actualizada Con Exito.");
					window.location.reload();
				}else{
					alert(validar);
				}
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ActUsu&usu="+usu+"&AntiContra="+contra+"&NuevaContra="+repcontra+"&CodUsuario="+CodUsuarioAct);
	}
	
}

function checkAllPA() {
	 var i;
	   var nodoCheck = document.getElementsByTagName("input");
	   var varCheck = document.getElementById("allPA").checked;
	   for (i=0; i<nodoCheck.length; i++){
	       if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name == "chkAutorizadoOpcMenu" && nodoCheck[i].disabled == false) {
	           nodoCheck[i].checked = varCheck;
	       }
	   } 
	}

function checkAllPD() {
	 var i;
	   var nodoCheck = document.getElementsByTagName("input");
	   var varCheck = document.getElementById("allPD").checked;
	   for (i=0; i<nodoCheck.length; i++){
	       if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name == "chkDisponibleOpcMenu" && nodoCheck[i].disabled == false) {
	           nodoCheck[i].checked = varCheck;
	       }
	   } 
	}


function pestanas(TipoComando){
	var CodUsuarioAct=document.getElementById("txtCodUsuarioAct").value;
	var CodDatosPersonales=document.getElementById("CodUsuario").value;
	if(TipoComando=="1"){
		TipoComando="PerMo";
	}
	if(TipoComando=="2"){
		TipoComando="PerFor";
	}
	if(TipoComando=="3"){
		TipoComando="PerHc";
	}
	if(TipoComando=="4"){
		TipoComando="PerBo";
	}
	if(TipoComando=="5"){
		TipoComando="PerPro";
	}
	if(TipoComando=="6"){
		TipoComando="Firma";
	}
	//alert("TipoComando "+TipoComando+" CodUsuarioAct "+CodUsuarioAct);
	ajax=getXMLObject();
	ajax.open("POST","Control_Usuarios",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			//alert(validar);
			document.getElementById("ContPestanas").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor="+TipoComando+"&CodUsuario="+CodUsuarioAct+"&CodDatosPersonales="+CodDatosPersonales);

}

function GuardarProgramacion(){	
	//alert("guardarprogrmacion")
	var c=document.getElementById('txtConDisponiblesPro').value;
	var CodigoUsuario=document.getElementById('txtCodUsuarioAct').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkDisponiblePro[i].checked) {
			CodigoPermiso=document.form1.chkDisponiblePro[i].value;
			GuardarRelacionPro(CodigoUsuario,CodigoPermiso);
			oscar--;
			}
		}
	}
	else{
		CodigoPermiso=document.form1.chkDisponiblePro.value;
		GuardarRelacionPro(CodigoUsuario,CodigoPermiso);
	}
	
}

function GuardarRelacionPro(CodigoUsuario,CodigoPermiso){
	ajax=getXMLObject();
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionPermisoPro();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GuardarPro&CodigoUsuario="+CodigoUsuario+"&CodigoPermiso="+CodigoPermiso); //Posting txtname to Servlet    
}


function RelacionPermisoPro(){
	//alert("RelacionPermisoBo");
	ajax=getXMLObject();
	var contenido=document.getElementById('ContPestanas');
	var CodigoUsuario=document.getElementById("txtCodUsuarioAct").value;
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=PerPro&CodUsuario="+CodigoUsuario); //Posting txtname to Servlet
}

function OmitirPermisoPro(){

	var c=document.getElementById('txtContAutorizadasPro').value;
	var CodigoUsuario=document.getElementById('txtCodUsuarioAct').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkAutorizadasPro[i].checked) {
				CodigoAsignacion=document.form1.chkAutorizadasPro[i].value;
			OmitirRelacionPro(CodigoAsignacion,CodigoUsuario);
			oscar--;
			}
		}
	}
	else{
		CodigoAsignacion=document.form1.chkAutorizadasPro.value;
		OmitirRelacionPro(CodigoAsignacion,CodigoUsuario);
	}
}

function OmitirRelacionPro(CodigoAsignacion,CodigoUsuario){
	//alert("OmitirRelacionBo");
	ajax=getXMLObject();
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionPermisoPro();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmitirPro&CodigoAsignacion="+CodigoAsignacion+"&CodigoUsuario="+CodigoUsuario); //Posting txtname to Servlet    

}

function RelacionFormatos(){
	ajax=getXMLObject();
	var contenido=document.getElementById('ContPestanas');
	var CodigoUsuario=document.getElementById("txtCodUsuarioAct").value;
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=PerFor&CodUsuario="+CodigoUsuario); //Posting txtname to Servlet
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
	var CodigoUsuario=document.getElementById('txtCodUsuarioAct').value;
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

function OmitirRelacionCE(CodigoAsignacion){
	ajax=getXMLObject();
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionFormatos();
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
			RelacionFormatos();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GuardarCE&CodigoUsuario="+CodigoUsuario+"&CodigoFormato="+CodigoFormato); //Posting txtname to Servlet    
}

function GuardarRelacionFormatosCE(){	
	var c=document.getElementById('txtConDisponiblesCE').value;
	var CodigoUsuario=document.getElementById('txtCodUsuarioAct').value;
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
	var contenido=document.getElementById('ContPestanas');
	var CodigoUsuario=document.getElementById("txtCodUsuarioAct").value;
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=PerHc&CodUsuario="+CodigoUsuario); //Posting txtname to Servlet
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
	var CodigoUsuario=document.getElementById('txtCodUsuarioAct').value;
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


function RelacionPermisoBo(){
	//alert("RelacionPermisoBo");
	ajax=getXMLObject();
	var contenido=document.getElementById('ContPestanas');
	var CodigoUsuario=document.getElementById("txtCodUsuarioAct").value;
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=PerBo&CodUsuario="+CodigoUsuario); //Posting txtname to Servlet
}

function GuardarRelacionBo(CodigoUsuario,CodigoPermiso){
	ajax=getXMLObject();
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionPermisoBo();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GuardarBo&CodigoUsuario="+CodigoUsuario+"&CodigoPermiso="+CodigoPermiso); //Posting txtname to Servlet    
}

function GuardarBodega(){	
	var c=document.getElementById('txtConDisponiblesBo').value;
	var CodigoUsuario=document.getElementById('txtCodUsuarioAct').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkDisponibleBo[i].checked) {
			CodigoPermiso=document.form1.chkDisponibleBo[i].value;
			GuardarRelacionBo(CodigoUsuario,CodigoPermiso);
			oscar--;
			}
		}
	}
	else{
		CodigoPermiso=document.form1.chkDisponibleBo.value;
		GuardarRelacionBo(CodigoUsuario,CodigoPermiso);
	}
}

function OmitirRelacionBo(CodigoAsignacion,CodigoUsuario){
	//alert("OmitirRelacionBo");
	ajax=getXMLObject();
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			RelacionPermisoBo();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmitirBo&CodigoAsignacion="+CodigoAsignacion+"&CodigoUsuario="+CodigoUsuario); //Posting txtname to Servlet    

}

function OmitirPermisoBo(){
	//alert("OmitirPermisoBo");
	var c=document.getElementById('txtContAutorizadasBo').value;
	var CodigoUsuario=document.getElementById('txtCodUsuarioAct').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkAutorizadasBo[i].checked) {
				CodigoAsignacion=document.form1.chkAutorizadasBo[i].value;
			OmitirRelacionBo(CodigoAsignacion,CodigoUsuario);
			oscar--;
			}
		}
	}
	else{
		CodigoAsignacion=document.form1.chkAutorizadasHC.value;
		OmitirRelacionBo(CodigoAsignacion,CodigoUsuario);
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

function VerOpcionesDisponibles(){
	ajax=getXMLObject();
	var contenido=document.getElementById('OpMenus');
	var CodigoMenu=document.getElementById("cmbMenu").value;
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OpcMenu&CodigoMenu="+CodigoMenu); //Posting txtname to Servlet
}

function VerOpcionesMenu(){
	ajax=getXMLObject();
	var contenido=document.getElementById('PerDispo');
	var CodigoOpcMenu=document.getElementById("cmbOpcMenu").value;
	var CodigoUsuario=document.getElementById('txtCodUsuarioAct').value;
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
			VerAutorizadosMenu(CodigoUsuario,CodigoOpcMenu);
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OpcDisMenu&CodigoOpcMenu="+CodigoOpcMenu+"&CodUsuario="+CodigoUsuario); //Posting txtname to Servlet
}

function VerAutorizadosMenu(CodigoUsuario,CodigoOpcMenu){
	//alert()
	ajax=getXMLObject();
	var contenido=document.getElementById('PerAuto');
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OpcAutMenu&CodigoOpcMenu="+CodigoOpcMenu+"&CodUsuario="+CodigoUsuario); //Posting txtname to Servlet

}


function OmitirRelacionPer(CodigoAsignacion){
	//alert("CodigoAsignacion"+ CodigoAsignacion)
	ajax=getXMLObject();
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			VerOpcionesMenu();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmiPer&CodigoAsignacion="+CodigoAsignacion); //Posting txtname to Servlet   
}

function OmitirRelacionPermisos(){
	var c=document.getElementById('txtContadorOpcMenAuto').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkAutorizadoOpcMenu[i].checked) {
				CodigoAsignacion=document.form1.chkAutorizadoOpcMenu[i].value;
			OmitirRelacionPer(CodigoAsignacion);
			oscar--;
			}
		}
	}
	else{
		CodigoAsignacion=document.form1.chkAutorizadoOpcMenu.value;
		OmitirRelacionPer(CodigoAsignacion);
	}
	
}

function GuardarRelacionPer(CodigoOpcionDispo,CodigoUsuario){
	//alert("CodigoOpcionDispo"+ CodigoOpcionDispo+" CodigoUsuario "+CodigoUsuario);
	ajax=getXMLObject();
	ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			VerOpcionesMenu();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AsiPer&CodigoOpcionDispo="+CodigoOpcionDispo+"&CodUsuario="+CodigoUsuario); //Posting txtname to Servlet  
}

function GuardarRelacionPermisos(){
	var CodigoUsuario=document.getElementById('txtCodUsuarioAct').value;
	var c=document.getElementById('txtContadorOpcMenDisp').value;
	var oscar=c;
	if(c!=1){
		for(var i=0 ; i<=c-1 ; i++){
			if (form1.chkDisponibleOpcMenu[i].checked) {
				CodigoOpcionDispo=document.form1.chkDisponibleOpcMenu[i].value;
			GuardarRelacionPer(CodigoOpcionDispo,CodigoUsuario);
			oscar--;
			}
		}
	}
	else{
		CodigoOpcionDispo=document.form1.chkDisponibleOpcMenu.value;
		GuardarRelacionPer(CodigoOpcionDispo,CodigoUsuario);
	}
	
}



