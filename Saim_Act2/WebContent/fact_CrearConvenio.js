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

//función de la fecha
var patron = new Array(2,2,4);
function masca(d, pat, nums){
	var sep="/";
	if(d.valant != d.value){
	    val = d.value;
	    largo = val.length;
	    ini = val.substring(0,2);
	    if(ini>31){
	        alert("D\xEDa no v\xE1lido!!!");
	        val = d.value = "";
	        d.focus();
	    }
	    val = val.split(sep);
	    
	    val2 = ''
	    for(var r=0;r<val.length;r++){
	        val2 += val[r]
	    }
	    ini = val2.substring(2,4);
	    if((ini=="04")||(ini=="06")||(ini=="09")||(ini=="11")){
	        x = val2.substring(0,2);
	        if(x == "31"){
	            val2 = "30";
	            val2 = val2+ini;
	        }
	    }
	    if(ini>12){
	        alert("Mes no v\xE1lido!!!");
	        val2 = d.value = "";
	        d.focus();
	    }
	    
	    ano = val2.substring(4,8);
	    dia = val2.substring(0,2);
	    if(ini == "02"){
	        if((dia=="30")||(dia=="31")||(dia=="29")){
	        	if(ano.length==4){
	        		if ( ( ano % 100 != 0) && ((ano % 4 == 0) || (ano % 400 == 0))) {
	        			val2="29";
	        			val2=val2+ini;
	        		}else{
	        			val2="28";
	        			val2=val2+ini;
	        		}
	        		val2=val2+ano;
	        	}
	        }
	    }

	    if(nums){
	    	for(var z=0;z<val2.length;z++){
	            if(!isN(val2.charAt(z))){
	                letra = new RegExp(val2.charAt(z),"g")
	                val2 = val2.replace(letra,"")
	            }
	        }
	    }
	    
	    val = ''
	    val3 = new Array()
	    for(var s=0; s<pat.length; s++){
	    	val3[s] = val2.substring(0,pat[s])
	        val2 = val2.substring(pat[s])
	    }
	    
	    for(var q=0;q<val3.length; q++){
	        if(q ==0){
	            val = val3[q]         
	        }else{
	            if(val3[q] != ""){
	                val += sep + val3[q]
	            }
	        }
	    }
	    
	    d.value = val
	    d.valant = val
    }
}

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
/**
 * Solo permite n&uacute;meros
 * @param evt
 * @return valores num&eecute;ricos
 */
function soloNumero(evt){
	var keyPressed = (evt.which) ? evt.which : event.keyCode;
	//alert(keyPressed);
	//patron = /\d/;//48 - 57 , 96 - 105
	//return patron.test(String.fromCharCode(keyPressed));
	//alert(!(keyPressed > 31 && (keyPressed < 48 || keyPressed > 57)));
	return !(keyPressed > 31 && (keyPressed < 48 || keyPressed > 57));
}
/**
 * 
 * @param texto
 * @param maxlong
 * @return El texto digitado si no sobrepasa el l&iacute;mite establecido.
 */
function maximaLongitud(texto,maxlong) { 
	  var in_value, out_value; 

	  if (texto.value.length > maxlong) { 
	    in_value = texto.value; 
	    out_value = in_value.substring(0,maxlong);
	    texto.value = out_value; 
	    return false; 
	  } 
	  return true;
}
/**
 * Compara si la fec1 es mayor a fec2.
 * @param fec1
 * @param fec2
 * @return true si fec1 es mayor a fec2, false en caso contrario.
 */
function isFechaMayor(fec1, fec2) {
	var an1 = ""; var an2 = "";
	var mes1 = ""; var mes2 = "";
	var dia1 = ""; var dia2 = "";
	
	an1 = fec1.substring(6, 10);
	an2 += fec2.substring(6, 10);
	
	mes1 = fec1.substring(3, 5);
	mes2 += fec2.substring(3, 5);
	
	dia1 = fec1.substring(0, 2);
	dia2 += fec2.substring(0, 2);
	
	/*alert("parseInt(an1): "+parseInt(an1)+"\nparseInt(an2): "+parseInt(an2)+
	"\nparseInt(mes1): "+parseInt(mes1)+"\nparseInt(mes2): "+parseInt(mes2)+
	"\nparseInt(dia1): "+parseInt(dia1)+"\nparseInt(dia2): "+parseInt(dia2));*/
	if(parseInt(an1) > parseInt(an2)){
		return true;
	}else if(parseInt(an1) == parseInt(an2)){
		if(parseInt(mes1) > parseInt(mes2)){
			return true;
		}else if(parseInt(mes1) == parseInt(mes2)){
			if(parseInt(dia1) > parseInt(dia2)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}else{
		return false;
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Ejecuta la funci&oacute;n 'Ingresar()', si oprime la tecla 'Enter'
 * @param tecla
 * @param e
 * @return
 */
function A(tecla, e){
	tecla = e.keyCode; e.which;
	if(tecla == 13){
		Ingresar();
	}
}

/**
 * Ejecuta la funci&oacute;n 'Actualizar()', si oprime la tecla 'Enter'
 * @param tecla
 * @param e
 */
function B(tecla, e){
	tecla = e.keyCode; e.which;
	if(tecla == 13){
		Actualizar();
	}
}

function Radio() {
	var ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	var title = document.getElementById('title');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x=0; x<radioButtons.length; x++) {
		if (radioButtons[x].checked) {
			var val = radioButtons[x].value;
			ajax.open("POST", "ControlCrearConvenio", true);
			ajax.onreadystatechange = function(){
				if (ajax.readyState == 4) {
					if(ajax.status == 200) {
						if(val!='ActualizarRb'){
							title.innerHTML="<div align='center' class='style11' id='cabecera2' style='width:100%;'>Datos del Convenio </div>";
							contenido.style.width='900px';
							contenido.className='centerOp900px';
							contenido.innerHTML = ajax.responseText;
						}else{
							title.innerHTML="";
							contenido.style.width='100%';
							contenido.className='';
							contenido.innerHTML = ajax.responseText;
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

function Ingresar(){
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	var tope=document.getElementById("tope").value;
	var numContrato = document.getElementById("NumContrato").value.trim();
	var valor = document.getElementById("Valor");
	var fechaI = document.getElementById("FechaI").value.trim();
	var fechaF = document.getElementById("FechaF").value.trim();
	var modalidad = "";
	var nivComplejidad = document.getElementById("listNivelC");
	var codTarifa = document.getElementById('listTarifa2').childNodes;
	var autoriza = document.getElementById("Autoriza").value.trim();
	var codEntidad = document.getElementById("listEntidad");
	var obs = document.getElementById("Observacion").value.trim();//Puede ir vacio 
	var Modalidad_Pago=document.getElementById("Modalidad_Pago").value;
	//numContrato, valor, fechaI, fechaF, modalidad, nivComplejidad, codTarifa1, codTarifa2, autoriza, codEntidad
	var tarifas = "";
	var mod = document.getElementsByName("checkboxModalidad");
	for(var i=0; i<mod.length; i++){
		if(mod[i].checked){
			if(i != (mod.length-1)){
				modalidad += "" + mod[i].value + "-";
			}else{
				modalidad += "" + mod[i].value;
			}
		}
	}
	
	if(numContrato == ""){
		alert("Digite el n\xFAmero del contrato");
		document.getElementById("NumContrato").focus();
		document.getElementById("NumContrato").select();
	}else if(!isN(valor.value)){
		if(valor.value == ""){
			alert("Digite el valor del contrato");
			valor.focus();
		}else{
			alert("Debe digitar n\xFAmeros en el campo del Valor");
			valor.focus();
			valor.select();
		}
	}else if(fechaI == ""){
		alert("Digite la fecha de inicio");
		document.getElementById("FechaI").focus();
		document.getElementById("FechaI").select();
	}else if(fechaF == ""){
		alert("Digite la fecha final");
		document.getElementById("FechaF").focus();
		document.getElementById("FechaF").select();
	}else if(!isFechaMayor(fechaF, fechaI)){
		alert("Digite la fecha final mayor a la fecha inicial");
		document.getElementById("FechaF").focus();
		document.getElementById("FechaF").select();
	}else if(modalidad == ""){
		alert("Escoja la(s) modalidad(es) de pago");
	}else if(nivComplejidad.selectedIndex == 0){
		alert("Escoja un nivel de complejidad");
		nivComplejidad.focus();
	}else if(codTarifa.length<1){
		alert("Escoja un manual tarifario");
	}else if(autoriza == ""){
		alert("Digite qui\xE9n autoriza");
		document.getElementById("Autoriza").focus();
		document.getElementById("Autoriza").select();
	}else if(codEntidad.selectedIndex == 0){
		alert("Escoja una entidad");
		codEntidad.focus();
	}else{
		for(var i=0;i<codTarifa.length;i++){
			tarifas += codTarifa[i].value+",";
		}
		tarifas = tarifas.substring(0, tarifas.length-1);
		var ajax = getXMLObject();
		ajax.open("POST", "ControlCrearConvenio", true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				if(ajax.responseText == "Ingreso exitoso"){
					document.getElementById("BorrarForm").click();
					//document.getElementById("NumContrato").focus();
					alert(ajax.responseText);
				}else if(ajax.responseText == "Esta empresa ya tiene convenio activo"){
					alert("Esta empresa ya tiene convenio activo");
					document.getElementById("listEntidad").focus();
					document.getElementById("listEntidad").select();
				}
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Ingresar&numContrato="+numContrato+"&valorConv="+valor.value+"&fechaI="+fechaI+"&fechaF="+fechaF+"&modalidad="+modalidad+"&nivComplejidad="+nivComplejidad.value.trim()+"&codTarifa1="+tarifas+"&autoriza="+autoriza+"&codEntidad="+codEntidad.value.trim()+"&obs="+obs+"&Modalidad_Pago="+Modalidad_Pago+"&tope="+tope+"&user="+txtCodusuario);
	}
}

function Buscar(){
	var title = document.getElementById('actConvenio');
	var contenido = document.getElementById('objeto');
	var cod = document.getElementById("lista").value.trim();
	if(cod != "Seleccione"){
		var ajax = getXMLObject();
		ajax.open("POST", "ControlCrearConvenio", true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				title.innerHTML="<div align='center' class='style11' id='cabecera2' style='width:100%;'>Datos del Convenio </div>";
				contenido.style.width='900px';
				contenido.className='centerOp900px';
				contenido.innerHTML = ajax.responseText;	
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ver&cod="+cod);
	}else{
		contenido.innerHTML = "";
	}
}

function Actualizar(){
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	var tope=document.getElementById("tope").value;
	var estc=document.getElementById("ec").value;
	var cod = document.getElementById("lista").value.trim();
	var numContrato = document.getElementById("NumContrato").value.trim();
	var valor = document.getElementById("Valor");
	var fechaI = document.getElementById("FechaI").value.trim();
	var fechaF = document.getElementById("FechaF").value.trim();
	var modalidad = "";
	var nivComplejidad = document.getElementById("listNivelC");
	var codTarifa = document.getElementById("listTarifa2").childNodes;
	var autoriza = document.getElementById("Autoriza").value.trim();
	var codEntidad = document.getElementById("listEntidad");
	var obs = document.getElementById("Observacion").value.trim();//Puede ir vacio
	var Modalidad_Pago=document.getElementById("Modalidad_Pago").value;
	var mod = document.getElementsByName("checkboxModalidad");
	var tarifas = "";
	for(var i=0; i<mod.length; i++){
		if(mod[i].checked){
			if(i != (mod.length-1)){
				modalidad += "" + mod[i].value + "-";
			}else{
				modalidad += "" + mod[i].value;
			}
		}
	}
	if(numContrato == ""){
		alert("Digite el n\xFAmero del contrato");
		document.getElementById("NumContrato").focus();
		document.getElementById("NumContrato").select();
	}else if(!isN(valor.value)){
		if(valor.value == ""){
			alert("Digite el valor del contrato");
			valor.focus();
		}else{
			alert("Debe digitar n\xFAmeros en el campo del Valor");
			valor.focus();
			valor.select();
		}
	}else if(fechaI == ""){
		alert("Digite la fecha de inicio");
		document.getElementById("FechaI").focus();
		document.getElementById("FechaI").select();
	}else if(fechaF == ""){
		alert("Digite la fecha final");
		document.getElementById("FechaF").focus();
		document.getElementById("FechaF").select();
	}else if(!isFechaMayor(fechaF, fechaI)){
		alert("Digite la fecha final mayor a la fecha inicial");
		document.getElementById("FechaF").focus();
		document.getElementById("FechaF").select();
	}else if(modalidad == ""){
		alert("Escoja la(s) modalidad(es) de pago");
	}else if(nivComplejidad.selectedIndex == 0){
		alert("Escoja un nivel de complejidad");
		nivComplejidad.focus();
	}else 
		if(codTarifa.length<1){
			alert("Escoja un manual tarifario");
		}else if(autoriza == ""){
		alert("Digite qui\xE9n autoriza");
		document.getElementById("Autoriza").focus();
		document.getElementById("Autoriza").select();
	}else if(codEntidad.selectedIndex == 0){
		alert("Escoja una entidad");
		codEntidad.focus();
	}else{
		for(var i=0;i<codTarifa.length;i++){
			tarifas += codTarifa[i].value+",";
		}
		tarifas = tarifas.substring(0, tarifas.length-1);
		var ajax = getXMLObject();
		ajax.open("POST", "ControlCrearConvenio", true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				if(ajax.responseText == "Convenio actualizado exitosamente"){
					document.getElementById("Opcion").innerHTML = "";
					var xmlhttp1 = getXMLObject();
					xmlhttp1.open("POST", "ControlCrearConvenio", true);
					xmlhttp1.onreadystatechange = function(){
						if (xmlhttp1.readyState == 4) {
							if(xmlhttp1.status == 200) {
								document.getElementById("Opcion").innerHTML = xmlhttp1.responseText;
							}
						}
					};
					xmlhttp1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					xmlhttp1.send("valor=ActualizarRb");
				}
				alert(ajax.responseText);
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Actualizar&numContrato="+numContrato+"&valorConv="+valor.value+"&fechaI="+fechaI+"&fechaF="+fechaF+"&modalidad="+modalidad+"&nivComplejidad="+nivComplejidad.value.trim()+"&codTarifa1="+tarifas+"&autoriza="+autoriza+"&codEntidad="+codEntidad.value.trim()+"&obs="+obs+"&cod="+cod+"&Modalidad_Pago="+Modalidad_Pago+"&tope="+tope+"&estc="+estc+"&user="+txtCodusuario);
	}
}
function Borrar() {
	document.getElementById("NumContrato").value = "";
	document.getElementById("Valor").value = "";
	document.getElementById("FechaI").value = "";
	document.getElementById("FechaF").value = "";
	document.getElementById("listTarifa2").innerHTML = "";
	
	var mod = document.getElementsByName("checkboxModalidad");
	for(var i=0; i<mod.length; i++){
		if(mod[i].checked){
			mod[i].checked = false;
		}
	}
	
	document.getElementById("listNivelC").selectedIndex = 0;
	document.getElementById("listTarifa1").selectedIndex = 0;
	document.getElementById("listTarifa2").selectedIndex = 0;//Es opcional
	document.getElementById("Autoriza").value = "";
	document.getElementById("listEntidad").selectedIndex = 0;
	document.getElementById("Observacion").value = "";
	
	document.getElementById("NumContrato").focus();
}

function asignarTarifa(){
	var codTarifa = document.getElementById("listTarifa1").value;
	if(codTarifa!='0'){
		var indice = document.getElementById("listTarifa1").selectedIndex;
		var descTarifa= document.getElementById("listTarifa1").options[indice].text;
		var nodo = document.createElement('option');
		nodo.value = codTarifa;
		nodo.text = descTarifa;
		var tarifaNueva = true;
		var tarifasAsignadas = document.getElementById("listTarifa2").childNodes
		for (var i=0;i<tarifasAsignadas.length;i++){
			if(tarifasAsignadas[i].value==codTarifa){
				tarifaNueva  = false;
				break;
			}
		}
		if(tarifaNueva){
			document.getElementById("listTarifa2").add(nodo);
			document.getElementById("listTarifa1").selectedIndex = 0;
		}
	}
}

function eliminarTarifa(tecla, e){
	tecla = e.keyCode; e.which;
	if(tecla == 46){
		var elSel = document.getElementById('listTarifa2');
		for (i = elSel.length - 1; i>=0; i--) {
			if (elSel.options[i].selected) {
		      elSel.remove(i);
		    }
		}
	}
}