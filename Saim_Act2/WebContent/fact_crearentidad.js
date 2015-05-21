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
String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ''); };

function maximaLongitud(texto,maxlong) { 
	  var tecla, in_value, out_value; 

	  if (texto.value.length > maxlong) { 
	    in_value = texto.value; 
	    out_value = in_value.substring(0,maxlong); 
	    texto.value = out_value; 
	    return false; 
	  } 
	  return true;
}

/*Solo permite n�meros*/
function soloNumero(evt){
	var keyPressed = (evt.which) ? evt.which : event.keyCode;
	//alert(keyPressed);
	//patron = /\d/;//48 - 57 , 96 - 105
	//return patron.test(String.fromCharCode(keyPressed));
	//alert(!(keyPressed > 31 && (keyPressed < 48 || keyPressed > 57)));
	return !(keyPressed > 31 && (keyPressed < 48 || keyPressed > 57));
}

/**
 * @author Carlos A. V&aacute;squez S&aacute;nchez
 * @param num String Valor que representa la expresi&oacute;n a ser validada.
 * @return {@link Boolean} que es true si el valor dado como par&aacute;metro es num&eacute;rico y false en caso contrario.
 */
function isN(num){
	if(num.length == 0){
		return false;
	}else{
		for(var i=0; i<num.length; i++){
			if(isNaN(num.charAt(i)) || num.charAt(i) == " "){
				return false;
			}
		}
	}
	return true;
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////
function A(tecla, e){
	tecla = e.keyCode; e.which;
	if(tecla == 13){
		CrearEntidad();
	}
}

function B(tecla, e){
	tecla = e.keyCode; e.which;
	if(tecla == 13){
		ActualizarEntidad();
	}
}

function Radio() {
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x=0; x<radioButtons.length; x++) {
		if (radioButtons[x].checked) {
			var val = radioButtons[x].id;
			ajax.open("POST", "ControlCrearEntidad", true);
			ajax.onreadystatechange = function(){
				if (ajax.readyState == 4) {
					if(ajax.status == 200) {
						contenido.innerHTML = ajax.responseText;
						if(val == "Ingresar"){
							cargarDepto();
						}
					}else{
						alert("Errores during AJAX call. Please try again");
					}
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val);
		}
	}
}

function CrearEntidad(){
	var NitEntidad = document.getElementById("txtNitEntidad").value.trim();
	var NombreEntidad = document.getElementById("txtNombreEntidad").value.trim();
	var dir = document.getElementById("txtDir").value.trim();
	var tel = document.getElementById("txtTel").value.trim();
	var fax = document.getElementById("txtFax").value.trim();
	var contacto = document.getElementById("txtContacto").value.trim();
	var depto = document.getElementById("listDepto");
	var ciudad = document.getElementById("listCiudad");
	var obs = document.getElementById("taObservacion").value.trim();
	var regimen = document.getElementById("listRegimen");
	var modPago = document.getElementById("listModPago");
	var codPrestador = document.getElementById("txtCodPrestador").value.trim();
	var Estado = document.getElementById("listEstado").value;
	
	var NombreRepresentante = document.getElementById("txtNombreRepresentante").value.trim();
	var TipoId = document.getElementById("cmbTipoDocumento").value;
	var NumId = document.getElementById("txtNumeroIdentificacion").value;
	var usuario = document.getElementById("txtCodusuario").value;
	
	
	if(NitEntidad == ""){
		alert("Digite el NIT de la entidad");
		document.getElementById("txtNitEntidad").focus();
		document.getElementById("txtNitEntidad").select();
	}else if(NombreEntidad == ""){
		alert("Digite el nombre de la entidad");
		document.getElementById("txtNombreEntidad").focus();
		document.getElementById("txtNombreEntidad").select();
	}else if(dir == ""){
		alert("Digite la direcci\xF3n de la entidad");
		document.getElementById("txtDir").focus();
		document.getElementById("txtDir").select();
	}else if(tel == ""){
		alert("Digite el tel\xE9fono de la entidad");
		document.getElementById("txtTel").focus();
		document.getElementById("txtTel").select();
	}/*else if(fax == ""){
		alert("Digite el fax de la entidad");
		document.getElementById("txtFax").focus();
		document.getElementById("txtFax").select();
	}else if(contacto == ""){
		alert("Digite el contacto de la entidad");
		document.getElementById("txtContacto").focus();
		document.getElementById("txtContacto").select();
	}*/else if(depto.selectedIndex == 0){
		alert("Seleccione el departamento");
		depto.focus();
	}else if(ciudad.selectedIndex == 0){
		alert("Seleccione la ciudad");
		ciudad.focus();
	}else if(regimen.selectedIndex == 0){
		alert("Seleccione el r\xE9gimen de la entidad");
		regimen.focus();
	}else if(modPago.selectedIndex == 0){
		alert("Seleccione la modalidad de pago de la entidad");
		modPago.focus();
	}else if(codPrestador == ""){
		alert("Digite el C\xF3digo Prestador de la entidad");
		document.getElementById("txtCodPrestador").focus();
		document.getElementById("txtCodPrestador").select();
	}/*else if(TipoId == ""){
		document.getElementById("cmbTipoDocumento").focus();
		alert("Seleccione el tipo de identificaci\xF3n");
	}*/else if(!isN(NumId)){
		if(NumId == ""){
			alert("Digite el n\xFAmero de identificaci\xF3n");
			document.getElementById("txtNumeroIdentificacion").focus();
			document.getElementById("txtNumeroIdentificacion").select();
		}else{
			alert("Debe digitar n\xFAmeros en el campo del n\xFAmero de identificaci\xF3n");
			document.getElementById("txtNumeroIdentificacion").focus();
			document.getElementById("txtNumeroIdentificacion").select();
		}
	}else if(NombreRepresentante == ""){
		alert("Digite el nombre del representante");
		document.getElementById("txtNombreRepresentante").focus();
		document.getElementById("txtNombreRepresentante").select();
	}else{
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrearEntidad", true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var validar = ajax.responseText;
				alert(validar);
				if(validar == "Esta Nit Ya le Pertenece a Una Entidad."){
					document.getElementById("txtNitEntidad").focus();
					document.getElementById("txtNitEntidad").select();
				}else{
					document.getElementById("BorrarForm").click();
					//window.location.reload();
				}
			}
		}
		//dir;tel;fax;contacto;depto;ciudad;obs;regimen;modPago;codPrestador;Estado;
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&NitEntidad="+NitEntidad+"&NombreEntidad="+NombreEntidad+"&dir="+dir+"&tel="+tel+"&fax="+fax+"&contacto="+contacto+"&depto="+depto.value.trim()+"&ciudad="+ciudad.value.trim()+"&obs="+obs+"&regimen="+regimen.value+"&modPago="+modPago.value+"&codPrestador="+codPrestador+"&Estado="+Estado+"&NombreRepresentante="+NombreRepresentante+"&TipoIdentificacion="+TipoId+"&NumeroIdentificacion="+NumId+"&usuario="+usuario);
	}
}

function BuscarEntidad(){
	var contenido = document.getElementById('Entidad');
	var CodEntidad = document.getElementById("cmbEntidad").value.trim();
	if(CodEntidad != "Seleccione"){
		ajax = getXMLObject();
		ajax.open("POST","ControlCrearEntidad",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				contenido.innerHTML = ajax.responseText	
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=verentidad&CodEntidad="+CodEntidad);
	}else{
		contenido.innerHTML = "";
		//alert("Error!");
	}
}
function ActualizarEntidad(){
	var NitEntidad = document.getElementById("txtNitEntidad").value.trim();
	var NombreEntidad = document.getElementById("txtNombreEntidad").value.trim();
	var dir = document.getElementById("txtDir").value.trim();
	var tel = document.getElementById("txtTel").value.trim();
	var fax = document.getElementById("txtFax").value.trim();
	var contacto = document.getElementById("txtContacto").value.trim();
	var depto = document.getElementById("listDepto");
	var ciudad = document.getElementById("listCiudad");
	var obs = document.getElementById("taObservacion").value.trim();
	var regimen = document.getElementById("listRegimen");
	var modPago = document.getElementById("listModPago");
	var codPrestador = document.getElementById("txtCodPrestador").value.trim();
	var Estado = document.getElementById("listEstado").value;
	
	var NombreRepresentante = document.getElementById("txtNombreRepresentante").value.trim();
	var TipoId = document.getElementById("cmbTipoDocumento").value;
	var NumId = document.getElementById("txtNumeroIdentificacion").value;
	
	var CodEntidad = document.getElementById("cmbEntidad").value.trim();
	var usuario = document.getElementById("txtCodusuario").value;
	
	if(NitEntidad == ""){
		alert("Digite el NIT de la entidad");
		document.getElementById("txtNitEntidad").focus();
		document.getElementById("txtNitEntidad").select();
	}else if(NombreEntidad == ""){
		alert("Digite el nombre de la entidad");
		document.getElementById("txtNombreEntidad").focus();
		document.getElementById("txtNombreEntidad").select();
	}else if(dir == ""){
		alert("Digite la direcci\xF3n de la entidad");
		document.getElementById("txtDir").focus();
		document.getElementById("txtDir").select();
	}else if(tel == ""){
		alert("Digite el tel\xE9fono de la entidad");
		document.getElementById("txtTel").focus();
		document.getElementById("txtTel").select();
	}else if(depto.selectedIndex == 0){
		alert("Seleccione el departamento");
		depto.focus();
	}else if(ciudad.selectedIndex == 0){
		alert("Seleccione la ciudad");
		ciudad.focus();
	}else if(regimen.selectedIndex == 0){
		alert("Seleccione el r\xE9gimen de la entidad");
		regimen.focus();
	}else if(modPago.selectedIndex == 0){
		alert("Seleccione la modalidad de pago de la entidad");
		modPago.focus();
	}else if(codPrestador == ""){
		alert("Digite el C\xF3digo Prestador de la entidad");
		document.getElementById("txtCodPrestador").focus();
		document.getElementById("txtCodPrestador").select();
	}else if(!isN(NumId)){
		if(NumId == ""){
			alert("Digite el n\xFAmero de identificaci\xF3n");
			document.getElementById("txtNumeroIdentificacion").focus();
			document.getElementById("txtNumeroIdentificacion").select();
		}else{
			alert("Debe digitar n\xFAmeros en el campo del n\xFAmero de identificaci\xF3n");
			document.getElementById("txtNumeroIdentificacion").focus();
			document.getElementById("txtNumeroIdentificacion").select();
		}
	}else if(NombreRepresentante == ""){
		alert("Digite el nombre del representante");
		document.getElementById("txtNombreRepresentante").focus();
		document.getElementById("txtNombreRepresentante").select();
	}else{
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrearEntidad", true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var validar=ajax.responseText;
				alert(validar);
				//window.location.reload();
				document.getElementById("cmbEntidad").selectedIndex = 0;
				document.getElementById("Entidad").innerHTML = "";
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2&NitEntidad="+NitEntidad+"&NombreEntidad="+NombreEntidad+"&dir="+dir+"&tel="+tel+"&fax="+fax+"&contacto="+contacto+"&depto="+depto.value+"&ciudad="+ciudad.value.trim()+"&obs="+obs+"&regimen="+regimen.value+"&modPago="+modPago.value+"&codPrestador="+codPrestador+"&Estado="+Estado+"&NombreRepresentante="+NombreRepresentante+"&TipoIdentificacion="+TipoId+"&NumeroIdentificacion="+NumId+"&CodEntidad="+CodEntidad+"&usuario="+usuario);
	}
}
//Cargar Depto
function cargarDepto() {
	if(xmlhttp) {
	    xmlhttp.open("POST", "ControlPais?yo=cargaDeptos", true);
	    xmlhttp.onreadystatechange  = function() {
	    	if (xmlhttp.readyState == 4) {
	    		if(xmlhttp.status == 200) {
	    			var f = xmlhttp.responseText.split("_");
	    			var d = f.length;
	    			//alert("Departamentos:\n"+f);
	    			//alert("Longitud: "+d);
	    			document.getElementById('listDepto').length = d;
	    			for(var x=0; x<d-1; x++){
	    				document.getElementById('listDepto').options[x+1].text = f[x];
	    				document.getElementById('listDepto').options[x+1].value = f[x];
	    			}
	    		}else {
	    			alert("Errores during AJAX call\nNo pudo cargar la lista de departamentos");
	    		}
	    	}
	 	};
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send("");
	}
}

function ajaxxMun(lista, Olista) {
	if(xmlhttp) {
		var dep = lista.options[lista.selectedIndex].text.trim();
	    xmlhttp.open("POST", "ControlPais", true);
	    xmlhttp.onreadystatechange  = function() {
	    	if (xmlhttp.readyState == 4) {
	    		if(xmlhttp.status == 200) {
	    			var z = xmlhttp.responseText.split("_");
	    	     	var y = z.length;
	    	     	//alert("Municipios:\n"+z);
	    			//alert("Longitud: "+y);
	    	     	document.getElementById(Olista).length = y;
	    	     	for(var x=0; x<y-1; x++){
	    	     		document.getElementById(Olista).options[x+1].text = z[x];
	    	     		document.getElementById(Olista).options[x+1].value = z[x];
	    		    }
	    	    }else {
	    	    	alert("Error during AJAX call\nNo se pudo cargar la lista de ciudades");
	    	    }
	    	}
	    };
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send("yo=3&de="+dep);
	}
}
function Borrar() {
	document.getElementById("txtNitEntidad").value = "";
	document.getElementById("txtNombreEntidad").value = "";
	document.getElementById("txtDir").value = "";
	document.getElementById("txtTel").value = "";
	document.getElementById("txtFax").value = "";
	document.getElementById("txtContacto").value = "";
	document.getElementById("listDepto").selectedIndex = 0;
	
	document.getElementById("listCiudad").length = 1;
	document.getElementById("listCiudad").options[0].text = "Seleccione ...";
	document.getElementById("listCiudad").options[0].value = "Seleccione ...";
	//document.getElementById("listCiudad").selectedIndex = 0;
	
	document.getElementById("taObservacion").value = "";
	document.getElementById("listRegimen").selectedIndex = 0;
	document.getElementById("listModPago").selectedIndex = 0;
	document.getElementById("txtCodPrestador").value = "";
	document.getElementById("listEstado").selectedIndex = 0;
	
	//document.getElementById("cmbTipoDocumento").selectedIndex = 0;//No hay necesidad por el momento al solo haber una eleccion
	document.getElementById("txtNumeroIdentificacion").value = "";
	document.getElementById("txtNombreRepresentante").value = "";
	
	document.getElementById("txtNitEntidad").focus();
}