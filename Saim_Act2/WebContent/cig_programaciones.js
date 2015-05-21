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
/////////////////////////////////////////////////////////////////////////////////
	var peticion = null;
	var elementoSeleccionado = -1;
	var sugerencias = null;
	var articulos= null;
	var cacheSugerencias = {};
	
	var patron = new Array(2, 2, 4);
	function masca(d, pat, nums, dias, mes, annio) {
		var sep = "/";
		if (d.valant != d.value) {
			val = d.value
			largo = val.length
			ini = val.substring(0, 2);
			if ((ini > 31) || (ini == "00")) {
				// alert("Dia No Valido!!!");
				val = d.value = "01";
				// d.focus();
			}
			val = val.split(sep);

			val2 = ''
			for (r = 0; r < val.length; r++) {
				val2 += val[r]
			}
			ini = val2.substring(2, 4);
			if ((ini == "04") || (ini == "06") || (ini == "09") || (ini == "11")) {
				x = val2.substring(0, 2);
				if (x == "31") {
					val2 = "30";
					val2 = val2 + ini;
				}
			}
			if ((ini > 12) || (ini == "00")) {
				// alert("Mes No Valido!!!");
				mescj = val2.substring(0, 2);
				val2 = mescj + "01";
				// d.focus();
			}

			ano = val2.substring(4, 8);
			dia = val2.substring(0, 2);

			if (ini == "02") {
				if ((dia == "30") || (dia == "31") || (dia == "29")) {
					if (ano.length == 4) {
						if ((ano % 100 != 0)
								&& ((ano % 4 == 0) || (ano % 400 == 0))) {
							val2 = "29";
							val2 = val2 + ini;
						} else {
							val2 = "28";
							val2 = val2 + ini;
						}
						val2 = val2 + ano;
					}
				}
			}

			/*
			 * /////////////////////////////////////////////////// //Validar fecha
			 * mayor que la fecha actual
			 * 
			 * if(ano.length==4){ if (ano==annio) { if(ini==mes){
			 * if((dia==dias)||(dia<dias)){ alert("La fecha de Vencimiento no puede
			 * ser menor o igual a la fecha Actual"); val2=''; } }else{ if(ini<mes){
			 * alert("La fecha de Vencimiento no puede ser menor o igual a la fecha
			 * Actual"); val2=''; } } } if (ano<annio) { alert("La fecha de
			 * Vencimiento no puede ser menor o igual a la fecha Actual"); val2=''; } }
			 * 
			 * ///////////////////////////////////////////////////
			 */
			if (nums) {
				for (z = 0; z < val2.length; z++) {
					if (isNaN(val2.charAt(z))) {
						letra = new RegExp(val2.charAt(z), "g")
						val2 = val2.replace(letra, "")

					}
				}
			}

			val = ''
			val3 = new Array()
			for (s = 0; s < pat.length; s++) {
				val3[s] = val2.substring(0, pat[s])
				val2 = val2.substring(pat[s])
			}

			for (q = 0; q < val3.length; q++) {
				if (q == 0) {
					val = val3[q]
				} else {
					if (val3[q] != "") {
						val += sep + val3[q]
					}
				}
			}
			d.value = val
			d.valant = val
		}
		var elEvento =  window.event;  // arguments[0] ||
	    var tecla = elEvento.keyCode;
	    if(tecla == 13) { 
	    		document.getElementById("ref0").focus();
	    		
	    }
		
	}
	
function VerFormulario(){
	ajax = getXMLObject();
	var fechap=document.getElementById('FP').value;
	var codigou=document.getElementById("user").value;
	var tp=document.getElementById('tp').value;
	//alert("Ver Formulario , codigo de usuario "+codigou);
	
	var contenido=document.getElementById('Formulario');
	//alert("FP "+fechap);
	if(tp!="Seleccione"){
	ajax.open("POST", "ControlProgramacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//contenido1="";
			//alert("antes");
			contenido.innerHTML = ajax.responseText;
			//alert("despues");
		//	document.getElementById('yulita').disabled=true;
			VerPreliminar(fechap,tp,codigou);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=formp&fechap="+fechap+"&tp="+tp+"&codigou="+codigou);
	}else{
		alert("No ha digitado un tipo de servicio");
	}
	}

function VerPreliminar(fechap,tp,codigou){
	ajax = getXMLObject();
	//alert("VerPreliminar");
	//alert("");
	//alert("tp "+tp);
	
	var contenido=document.getElementById('Vista');
	//alert("FP "+fechap);
	ajax.open("POST", "ControlProgramacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//contenido1="";
			//alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText;
			//document.getElementById('yulita').disabled=true;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VerDetP&fechap="+fechap+"&tp="+tp+"&codigou="+codigou);
	}
	
function Agregar1(codigo, usuario,tp){
	ajax = getXMLObject();
	if(tp==1){
		var hora=document.getElementById('hora').value;
		var rhora=document.getElementById('rhora').value;
		var npac=document.getElementById('npac').value;
		var proc=document.getElementById('proc').value;
		var eps=document.getElementById('eps').value;
		var ciru=document.getElementById('ciru').value;
		var ayud=document.getElementById('ayud').value;
		var anes=document.getElementById('anes').value;
		var q=document.getElementById('Quir').selectedIndex;
		var qui=document.getElementById('Quir').options[q].value;
		//var quirofano=document.getElementById('Quir').value;
		var tel=document.getElementById('tel').value;
		
		/*var serv=document.getElementById('serv');
		if(serv.selectedIndex == -1){
			return;
		}
		valor =serv.Value;
		txt = serv.options[serv.selectedIndex].text;
		servicio=txt;
		*/
		
		var servicio=document.getElementById('serv').value;
		var hab=document.getElementById('hab').value;
		//var hora=h+" "+rhora;
		//alert("valor quirofano"+qui);
		//alert("");
		//alert("tp "+tp);
	//alert("valor=GuardarDetP1&hora="+hora+"&npac="+npac+"&proc="+proc+"&eps="+eps+"&ciru="+ciru+"&ayud="+ayud+"&anes="+anes+"&codigo="+codigo+"&usuario="+usuario+"&tp="+tp+"&qui="+qui+"&tel="+tel+"&serv="+servicio+"&hab="+hab+"&rhora="+rhora);
		var contenido=document.getElementById('Vista');
		//alert("FP "+fechap);
		if((hora=="")||(npac=="")||(tel=="")||(proc=="")||(ciru=="")||(ayud=="")||(anes=="")||(eps=="")||(servicio=="")||(hab=="")){
			alert("No ha llenado todos los datos");
		}else{
		ajax.open("POST", "ControlProgramacion", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				//contenido1="";
				contenido.innerHTML = ajax.responseText;
				document.getElementById('hora').value="";
				document.getElementById('npac').value="";
				document.getElementById('proc').value="";
				document.getElementById('eps').value="";
				document.getElementById('ciru').value="";
				document.getElementById('ayud').value="";
				document.getElementById('anes').value="";
				document.getElementById('tel').value="";
				document.getElementById('serv').value="";
				document.getElementById('hab').value="";
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GuardarDetP1&hora="+hora+"&npac="+npac+"&proc="+proc+"&eps="+eps+"&ciru="+ciru+"&ayud="+ayud+"&anes="+anes+"&codigo="+codigo+"&usuario="+usuario+"&tp="+tp+"&qui="+qui+"&tel="+tel+"&serv="+servicio+"&hab="+hab+"&rhora="+rhora);
		}
	}else{
		if(tp==3){
			hora=document.getElementById('hora').value;
			rhora=document.getElementById('rhora').value;
			npac=document.getElementById('npac').value;
			var intra=document.getElementById('intra').value;
			var sistema=document.getElementById('sistema').value;
			eps=document.getElementById('eps').value;
			var obs=document.getElementById('obs').value;
			var diag=document.getElementById('diag').value;
			var edad=document.getElementById('edad').value;
			var se=document.getElementById('se').value;
			var ocita=document.getElementById('ocita').value;
			var tdoc=document.getElementById('tdoc').value;
			var numdoc=document.getElementById('numdoc').value;
			tel=document.getElementById('tel').value;
			
			servicio=document.getElementById('serv').value;
			hab=document.getElementById('hab').value;
			contenido=document.getElementById('Vista');
			//alert("valor=GuardarDetP1&codigo="+codigo+"&usuario="+usuario+"&tp="+tp+"&hora="+hora+"&rhora="+rhora+"&npac="+npac+"&intra="+intra+"&sistema="+sistema+"&eps="+eps+"&obs="+obs+"&diag="+diag+"&edad="+edad+"&se="+se+"&ocita="+ocita+"&tdoc="+tdoc+"&numdoc="+numdoc);
			if((hora=="")||(npac=="")||(tel=="")||(numdoc=="")||(diag=="")||(eps=="")||(servicio=="")||(hab=="")){
				alert("No ha llenado todos los datos");
			}else{
			ajax.open("POST", "ControlProgramacion", true);
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					//contenido1="";
					contenido.innerHTML = ajax.responseText;
					document.getElementById('hora').value="";
					document.getElementById('npac').value="";
					document.getElementById('sistema').value="";
					document.getElementById('eps').value="";
					document.getElementById('intra').value="";
					document.getElementById('obs').value="";
					document.getElementById('diag').value="";
					document.getElementById('tel').value="";
					document.getElementById('serv').value="";
					document.getElementById('hab').value="";
					document.getElementById('eps').value="";
					document.getElementById('numdoc').value="";
					document.getElementById('edad').value="";
					document.getElementById('ocita').value="";
					//11
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=GuardarDetP1&codigo="+codigo+"&usuario="+usuario+"&tp="+tp+"&hora="+hora+"&rhora="+rhora+"&npac="+npac+"&intra="+intra+"&sistema="+sistema+"&eps="+eps+"&obs="+obs+"&diag="+diag+"&edad="+edad+"&se="+se+"&ocita="+ocita+"&tdoc="+tdoc+"&numdoc="+numdoc+"&serv="+servicio+"&hab="+hab+"&tel="+tel);
			}
			
		}else{
			if((tp==4)||(tp==5)){
				hora=document.getElementById('hora').value;
			    rhora=document.getElementById('rhora').value;
				npac=document.getElementById('npac').value;
				proc=document.getElementById('proc').value;
				eps=document.getElementById('eps').value;
				ciru=document.getElementById('ciru').value;
			    obs=document.getElementById('obs').value;
				anes=document.getElementById('anes').value;
				q=document.getElementById('Quir').selectedIndex;
				qui=document.getElementById('Quir').options[q].value;
				//var quirofano=document.getElementById('Quir').value;
				 tel=document.getElementById('tel').value;
					
			    servicio=document.getElementById('serv').value;
				hab=document.getElementById('hab').value;
				//var hora=h+" "+rhora;
				//alert("valor quirofano"+qui);
				//alert("");
				//alert("tp "+tp);
			//alert("valor=GuardarDetP1&hora="+hora+"&npac="+npac+"&proc="+proc+"&eps="+eps+"&ciru="+ciru+"&ayud="+ayud+"&anes="+anes+"&codigo="+codigo+"&usuario="+usuario+"&tp="+tp+"&qui="+qui+"&tel="+tel+"&serv="+servicio+"&hab="+hab+"&rhora="+rhora);
				contenido=document.getElementById('Vista');
				//alert("FP "+fechap);
				if((hora=="")||(npac=="")||(tel=="")||(proc=="")||(ciru=="seleccione")||(anes=="seleccione")||(eps=="seleccione")||(servicio=="seleccione")||(hab=="")||(qui=="seleccione")){
					alert("No ha llenado todos los datos");
				}else{
				ajax.open("POST", "ControlProgramacion", true);
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						//contenido1="";
						contenido.innerHTML = ajax.responseText;
						document.getElementById('hora').value="";
						document.getElementById('npac').value="";
						document.getElementById('proc').value="";
						document.getElementById('eps').value="";
						document.getElementById('ciru').value="";
						document.getElementById('obs').value="";
						document.getElementById('anes').value="";
						document.getElementById('tel').value="";
						document.getElementById('serv').value="";
						document.getElementById('hab').value="";
					}
				}
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=GuardarDetP1&hora="+hora+"&npac="+npac+"&proc="+proc+"&eps="+eps+"&ciru="+ciru+"&obs="+obs+"&anes="+anes+"&codigo="+codigo+"&usuario="+usuario+"&tp="+tp+"&qui="+qui+"&tel="+tel+"&serv="+servicio+"&hab="+hab+"&rhora="+rhora);
				
				}
			}
		}
	}
}

function Agregar2(codigo, usuario,tp){
	ajax = getXMLObject();
	
	var hora=document.getElementById('hora').value;
	var rhora=document.getElementById('rhora').value;
	var npac=document.getElementById('npac').value;
	var proc=document.getElementById('proc').value;
	var eps=document.getElementById('eps').value;
	var ciru=document.getElementById('ciru').value;
	var ayud=document.getElementById('ayud').value;
	var anes=document.getElementById('anes').value;
	//var hora=h+" "+rhora;
	//var q=document.getElementById('Quir').selectedIndex;
//	var qui=document.getElementById('Quir').options[q].value;
	//var quirofano=document.getElementById('Quir').value;
	//var tel=document.getElementById('tel').value;
	//var serv=document.getElementById('serv').value;
	//var hab=document.getElementById('hab').value;
	//alert("valor quirofano"+qui);
	//alert("");
	//alert("tp "+tp);
	//alert("valor=GuardarDetP1&hora="+hora+"&npac="+npac+"&proc="+proc+"&eps="+eps+"&ciru="+ciru+"&ayud="+ayud+"&anes="+anes+"&codigo="+codigo+"&usuario="+usuario+"&tp="+tp);
	var contenido=document.getElementById('Vista');
	//alert("FP "+fechap);
	if((hora=="")||(npac=="")||(proc=="")||(ciru=="seleccione")||(ayud=="")||(anes=="seleccione")||(eps=="seleccione")){
		alert("No ha llenado todos los datos");
	}else{
	ajax.open("POST", "ControlProgramacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//contenido1="";
			contenido.innerHTML = ajax.responseText;
			document.getElementById('hora').value="";
			document.getElementById('npac').value="";
			document.getElementById('proc').value="";
			document.getElementById('eps').value="";
			document.getElementById('ciru').value="";
			document.getElementById('ayud').value="";
			document.getElementById('anes').value="";
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GuardarDetP1&hora="+hora+"&npac="+npac+"&proc="+proc+"&eps="+eps+"&ciru="+ciru+"&ayud="+ayud+"&anes="+anes+"&codigo="+codigo+"&usuario="+usuario+"&tp="+tp+"&rhora="+rhora);
	}
	
}

function GenerarRep1(){
	var tp=document.getElementById("tp").value;
	var user=document.getElementById("user");
	var Idia=document.getElementById("Idia").value;
	var Imes=document.getElementById("Imes").value;
	var Iano=document.getElementById("Iano").value;
	var Fano=document.getElementById("Fano").value;
	var Fdia=document.getElementById("Fdia").value;
	var Fmes=document.getElementById("Fmes").value;
	var contenido=document.getElementById("vista");
	//alert("tp "+tp);
	
	ajax = getXMLObject();
	ajax.open("POST", "ControlProgramacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML=ajax.responseText;
	}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GenerarRep&user="+user+"&tp="+tp+"&Idia="+Idia+"&Imes="+Imes+"&Iano="+Iano+"&Fano="+Fano+"&Fdia="+Fdia+"&Fmes="+Fmes);
	
}

function Eliminar(coddet,codigo,tp,usuario){
	ajax = getXMLObject();
	ajax.open("POST", "ControlProgramacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert(ajax.responseText);
			VerDatos(codigo,tp,usuario);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Elim&coddet="+coddet+"&codigo="+codigo+"&tp="+tp);
}

function VerDatos(codigo,tp,usuario){
	ajax = getXMLObject();
	var contenido=document.getElementById("Vista");
	ajax.open("POST", "ControlProgramacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VerDatos&codigo="+codigo+"&tp="+tp+"&usuario="+usuario);
}

function Finalizar(codigou, codigo,tp){
	ajax = getXMLObject();
	ajax.open("POST", "ControlProgramacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert(ajax.responseText);
			window.location.reload();
			if(tp==1){
				verrep1(codigo);
			}else{
				if(tp==2){
					verrep2(codigo);
				}else{
					if(tp==3){
						verrep3(codigo);
					}else{
						if((tp==4)||(tp==5)){
						verrep45(codigo);
						}
					}
				}
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=FinalP&codigou="+codigou+"&codigo="+codigo);
}

function verrep1(codigo){
	pagina="cig_ReporteProgCirugia.jsp?codigo="+codigo;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);	
}

function verrep2(codigo){
	pagina="cig_ReporteProgCardio.jsp?codigo="+codigo;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);	
	
}

function verrep3(codigo){
	pagina="cig_ReporteProgOnco.jsp?codigo="+codigo;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);	
	
}


function verrep45(codigo){
	pagina="cig_ReporteProgHemoEndos.jsp?codigo="+codigo;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);	
	
}

var numerofiltro=/^[0-9]*$/;  
var patronh = new Array(2, 2);
function checkhora(){
	var cc="hora";
	var e=document.getElementById(cc).value;
	largo = e.length
	//alert(e);
	var	val = e.replace(":","");
	//alert(val);
	var returnval=numerofiltro.test(val)
	if (returnval==false){
		y=e.substring(0,largo-1);
		document.getElementById(cc).value = y;
	}
	
	if((largo==3)&&(e.substring(2)!=":")){
		y=e.substring(0,2);
		z=e.substring(2);
		y=y+":"+z;
	document.getElementById(cc).value = y;
	}
	

	
	if(largo==2){
		//y=e.substring(0,5);
		if(e>24)
		document.getElementById(cc).value = "00";
	}
	
	if(largo==5){
		y=e.substring(3,5);
		z=e.substring(0,2);
		//alert(y);
		if(y>59)
		document.getElementById(cc).value = z+":00";
		document.getElementById("rhora").focus();
	}
	
	if(largo>5){
		y=e.substring(0,5);
		document.getElementById(cc).value = y;
	}

}



function tipoarea(){
	ajax=getXMLObject();
	var codserv=document.getElementById('serv').value;
	var serv=document.getElementById('serv');
	if(serv.selectedIndex == -1){
		return;
	}
	valor =serv.Value;
	txt = serv.options[serv.selectedIndex].text;
	servicio=txt;
	//alert("codserv"+codserv);
	//alert("serv"+servicio);
	
	var contenido=document.getElementById('subarea');

	if(servicio!='seleccione'){
		
		ajax.open("POST","ControlProgramacion",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert(ajax.resposeText);
				//alert("valor=VistaSubArea&serv="+servicio+"&codserv="+codserv);
				contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VistaSubArea&serv="+servicio+"&codserv="+codserv);
		
	}else{
	alert("No ha escogido una opcion valida ");
	}
	
	//alert("pruebaa de onchange"+serv);
	
	
	
}


