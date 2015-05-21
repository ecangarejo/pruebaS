
function getXMLObject(){
	var xmlhttp=null;
	if (typeof (XMLHttpRequest) != 'undefined') {
		try {
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
		}
	} else {
		try {
			xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
		} catch (e) {
			xmlhttp = new ActiveXObject('Msxml2.XMLHTTP');
		}
	}
	return xmlhttp;
}

function mouseOn(){
	$("#info").show('slow');
	$("#Menu").hide('slow');
}

function mouseOut(){
	setTimeout(function(){
		$("#Menu").show('fast');
		$("#info").hide('fast');
	},2000);
}

function hora(){
	var now = new Date();
	var hora = now.getHours();
	var minuto = now.getMinutes();
	var segundo = now.getSeconds();
	var HoraAc = hora+":"+minuto+":"+segundo;
	$('#TxtHora').val(HoraAc);
}

function horaFormato(){
	var now = new Date();
	var hora = now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
	return hora;
}

function CalendarioNew(campo){
	$(function() {
		$.datepicker.regional['es'] = {
				maxDate: '+0d',
				changeMonth: true,
				changeYear: true,
				firstDay: 1,
				closeText: 'Cerrar',
				prevText: '&#x3c;Ant',
				nextText: 'Sig&#x3e;',
				currentText: 'Hoy',
				monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
				'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
				monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
				'Jul','Ago','Sep','Oct','Nov','Dic'],
				dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
				dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
				dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
				dateFormat: 'yy-mm-dd',
				isRTL: false,
				showMonthAfterYear: false,
				yearSuffix: ''};
		$.datepicker.setDefaults($.datepicker.regional['es']);
	    $(campo).datepicker();
	  });
}

function Calendario(campo){
	jQuery(function($){
		$.datepicker.regional['es'] = {
			maxDate: '+0d',
			changeMonth: true,
			changeYear: true,
			showWeek: true,
			firstDay: 1,
			closeText: 'Cerrar',
			prevText: '&#x3c;Ant',
			nextText: 'Sig&#x3e;',
			currentText: 'Hoy',
			monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
			'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
			monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
			'Jul','Ago','Sep','Oct','Nov','Dic'],
			dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
			dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
			dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
			weekHeader: 'Sm',
			dateFormat: 'yy-mm-dd',
			isRTL: false,
			showMonthAfterYear: false,
			yearSuffix: ''};
		$.datepicker.setDefaults($.datepicker.regional['es']);
	});  
	$(document).ready(function() {
		$(campo).datepicker();
	});
}

function Calendario2(campo){
	jQuery(function($){
		$.datepicker.regional['es'] = {
			firstDay: 1,
			closeText: 'Cerrar',
			prevText: '&#x3c;Ant',
			nextText: 'Sig&#x3e;',
			currentText: 'Hoy',
			monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
			'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
			monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
			'Jul','Ago','Sep','Oct','Nov','Dic'],
			dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
			dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
			dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
			weekHeader: 'Sm',
			dateFormat: 'yy-mm-dd',
			isRTL: false,
			showMonthAfterYear: false,
			yearSuffix: ''};
		$.datepicker.setDefaults($.datepicker.regional['es']);
	});  
	$(document).ready(function() {
		$(campo).datepicker();
		$("#anim").change(function() {
		      $(campo).datepicker( "option", "showAnim", $( this ).val() );
		});
	});
}

function CalcularTrim(){
	var fecM = $("#TxtFechaGes").val();
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function (){
		if(ajax.readyState==4){			
			var dias = ajax.responseText;
			$("#TxtDias").val(dias);
			if(dias<=93){
				$("#Trimestre").val("Trimestre 1");
			}else if(dias<=186){
				$("#Trimestre").val("Trimestre 2");
			}else if(dias<=286){
				$("#Trimestre").val("Trimestre 3");
			}
			diasSemanas();
			ValGestante();
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=11&fecM="+fecM);
}

function FechaConcepcion(){
	var fecha = $("#TxtFechaP").val();
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function (){
		if(ajax.readyState==4){			
			$("#TxtFechaGes").val(ajax.responseText);
			CalcularTrim2();
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=25&fecha="+fecha);
}

function CalcularTrim2(){
	var fecha = $("#TxtFechaGes").val();
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function (){
		if(ajax.readyState==4){			
			var dias = ajax.responseText;
			$("#TxtDias").val(dias);
			if(dias<=93){
				$("#Trimestre").val("Trimestre 1");
			}else if(dias<=186){
				$("#Trimestre").val("Trimestre 2");
			}else if(dias<=286){
				$("#Trimestre").val("Trimestre 3");
			}
			diasSemanas();
			ValGestante();
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=11&fecM="+fecha);
}

function CalcularPartoEco(){
	var fecha = $("#TxtFechaE").val();
	var Sem = $("#TxtSem").val();
	var res = 40-Sem;
	var c2 = res*7;
	alert(c2);
	if(fecha==""){
		alert("Debe ingresar la fecha de la ultima ecografia!");
	}else if(Sem==""){
		alert("Debe ingresar las semanas de gestacion segun ecografia!");
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlConfiguracionPP",true);
		ajax.onreadystatechange = function (){
			if(ajax.readyState==4){
				$("#TxtFechaP").val(ajax.responseText);
				FechaConcepcion();
			}
		};
		ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ajax.send("op=24&Dias="+c2+"&fecha="+fecha);
	}
}

function CalcularParto(){
	var fecPar = $("#TxtFechaGes").val();
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function (){
		if(ajax.readyState==4){
			$("#TxtFechaP").val(ajax.responseText);
			CalcularTrim();
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=12&fecpar="+fecPar);
}

function gesEco(campo){
	var fechaEco = campo;
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function (){
		if (ajax.readyState == 4) {
			var id = $("[name='ges2']").attr('id');
			$("#"+id).val(ajax.responseText+" dias");
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=10&fececo="+fechaEco);
}

function Calendario3(campo){
	jQuery(function($){
		$.datepicker.regional['es'] = {
			minDate: '+0d',
			showWeek: true,
			firstDay: 1,
			closeText: 'Cerrar',
			prevText: '&#x3c;Ant',
			nextText: 'Sig&#x3e;',
			currentText: 'Hoy',
			monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
			'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
			monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
			'Jul','Ago','Sep','Oct','Nov','Dic'],
			dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
			dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
			dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
			weekHeader: 'Sm',
			dateFormat: 'yy-mm-dd',
			isRTL: false,
			showMonthAfterYear: false,
			yearSuffix: ''};
		$.datepicker.setDefaults($.datepicker.regional['es']);
	});  
	$(document).ready(function() {
		$(campo).datepicker();
	});
}

function orgFec2(campo){
	var fm = campo;
	var ret = fm.toString().split("/");
	var año = ret[2];var mes = ret[0];var dia = ret[1];
	var fecR = (mes+"-"+dia+"-"+año);
	return fecR;
}

function diasSemanas(){
	var Res = "";
	var Trim = document.getElementById("TxtDias").value;
	var Semanas =  Math.floor(Trim / 7);
	var Dias = Trim%7;
	if(Semanas != 1){
		Res = "Tiempo de Gestaci\u00F3n: "+Semanas+" Semanas con "+Dias+" dias";
	}else{
		Res = "Tiempo de Gestaci\u00F3n: "+Semanas+" Semana con "+Dias+" dias";
	}
	return Res;
}

function Trimestre(){
	var dias = $("#TxtDias").val();
	var trim = "";
	if(dias<=93){
		trim="Primer Trimestre";
	}else if(dias<=186){
		trim="Segundo Trimestre";
	}else if(dias<=286){
		trim="Tercer Trimestre";
	}
	return trim;
}

function ColorRB(id){
	$(id).hide();
}

function ColorTX(id){
	$(id).hide();
}

function ValidarRBs(){
	var res = null;
	$("#contenido2").find(":input:radio").each(function(){
		var id = $(this).attr("id");
		var nm = $(this).attr("name");
		if($("input[name='"+nm+"']:radio").is(':checked')){
			$('#x'+id).hide();
			res = true;
		} else {  
			$('#x'+id).css('color','red');
			$('#x'+id).show();
			res = false;
		}
    });
	return res;
}

function ValidarRBs2(){
	var res = null;
	$("#contenido2").find(".radSN").each(function(){
		var nm = $(this).attr("name");
		if($("input[name='"+nm+"']:radio").is(':checked')){
			$('#X'+nm).hide('slow');
			res = true;
		} else {  
			$('#X'+nm).css('color','red');
			$('#X'+nm).show('slow');
			res = false;
		}
    });
	return res;
}

/*function ValidarText(){
	var res = null;
	$("#contenido2").find(":input:text").each(function(){
		var id = $(this).attr("id");
		if($(this).val()==""){
			$('#S'+id).css('color','red');
			$('#S'+id).show();
			res = false;
		}else{  
			$('#S'+id).hide();
			res = true;
		}
    });
	return res;
}*/

function ValGestante(){
	var tdoc = $("#cmbTipoDoc").val();
	var ndoc = $("#txtNumDocumento").val();
	var Dias = $("#TxtDias").val();
	var Trim = $("#Trimestre").val();
	var FM = $("#TxtFechaGes").val();
	var FECo = $("#TxtFechaE").val();
	var Semanas = diasSemanas();
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	if(FM==""){ 
		alert("Debe ingresar la fecha de la ultima menstruacion!");
   	}else if(FECo==""){
   		alert("Debe ingresar la fecha de la ultima ecografia!");
   	}else{
   		if(Dias<7){
   			if(Dias==1){
   				alert(Dias+" Dia, aun no se encuentra en el tiempo de gestacion!");
   	   			$("#resultadoEnc1").html("");
   			}else if(Dias!=1){
   				alert(Dias+" Dias, aun no se encuentra en el tiempo de gestacion!");
   	   			$("#resultadoEnc1").html("");
   			}
   		}else if(Dias>281){
   			alert(Semanas+": Embarazo prolongado!");
   			$("#resultadoEnc1").html("");
   		}else{
   			alert(Semanas);
   			ajax.onreadystatechange = function (){
				if (ajax.readyState == 4) {
					$("#resultadoEnc1").html(ajax.responseText);
				}
			};
		}
   	}
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=2&trim="+Trim+"&tdoc="+tdoc+"&ndoc="+ndoc);
}

//Validaciones RadioButtons
function Solo(){
	var rbN1 = document.getElementById("2");
	var rbN2 = document.getElementById("4");
	var rbN3 = document.getElementById("6");
	var rbN4 = document.getElementById("8");
	if(rbN1.checked && rbN2.checked && rbN3.checked && rbN4.checked){
		$("#CheckSol").prop("checked", true);
	}else if(rbN1.checked==false || rbN2.checked==false ||
			rbN3.checked==false || rbN4.checked==false){
		$("#CheckSol").prop("checked", false);
	}
}

function soloNum(campo){
	var RegExPattern = /^\d*$/;
	if (campo.value.match(RegExPattern)){
    	return true;
    }else{    	
        alert("Solo puede ingresar numeros sin espacios!");                        
        campo.value="";
        campo.focus();
    }
}

function soloNum1(campo){
	var valor = campo;
	var RegExPattern = /^\d*$/;
    if(campo.value.match(RegExPattern)&&(valor.value<=4)){
    	return true;
    }else{
        alert("Solo puede ingresar numeros, sin espacios y menor o igual a 4");
        campo.value="";
        campo.focus();
    }
}

function soloNum2(campo){
	var valor = campo;
	var RegExPattern = /^\d*$/;
    if(campo.value.match(RegExPattern)&&(valor.value>=5)){
    	return true;
    }else{
        alert("Solo puede ingresar numeros, sin espacios y igual o mayor a 5");
        campo.value="";
        campo.focus();
    }
}

function soloNum3(campo){
	var valor = campo;
	var RegExPattern = /^\d*$/;
    if(campo.value.match(RegExPattern)&&(valor.value<=12)){
    	return true;
    }else{
        alert("Solo puede ingresar numeros, sin espacios y menor o igual a 12");
        campo.value="";
        campo.focus();
    }
}

function numMenor(campo){
	var valor = campo;
	var RegExPattern = /^\d*$/;
    if(campo.value.match(RegExPattern)&&(valor.value<=250)){
    	return true;
    }else{
        alert("Solo puede ingresar numeros, sin espacios y menor o igual a 250");
        campo.value="";
        campo.focus();
    }
}

function numTA(campo){
	var RegExPattern = "^[0-9]{1,3}(\/[0-9]{0,2})?$";
    if(campo.value.match(RegExPattern)){
    	return true;
    }else{
        alert("Solo puede ingresar numeros y en formato 000/00");
        campo.value="";
        campo.focus();
    }
}

function numSem(campo){
	var valor = campo;
	var RegExPattern = /^\d*$/;
    if(campo.value.match(RegExPattern)&&(valor.value<=40)){
    	return true;
    }else{
        alert("Solo puede ingresar numeros, sin espacios y menor o igual a 40");
        campo.value="";
        campo.focus();
    }
}

function numDias(campo){
	var valor = campo;
	var RegExPattern = /^\d*$/;
    if(campo.value.match(RegExPattern)&&(valor.value<=7)){
    	return true;
    }else{
        alert("Solo puede ingresar numeros, sin espacios y menor o igual a 7");
        campo.value="";
        campo.focus();
    }
}

function sumaTotal(numT){
	var Num1 = $("#TxtSubTotal").val();
	var Num2 = $("#TxtSubTotal2").val();
	var Num3 = $("#TxtSubTotal3").val();
	var Num4 = $("#TxtSubTotal4").val();
	var Num5 = $("#T5").val();
	if(Num1==""){
		Num1=0;
	}
	if(Num2==""){
		Num2=0;
	}
	if(Num3==""){
		Num3=0;
	}
	if(Num4==""){
		Num4=0;
	}
	if(Num5==""){
		Num5=0;
	}
	var c1 = parseInt(Num1);
	var c2 = parseInt(Num2);
	var c3 = parseInt(Num3);
	var c4 = parseInt(Num4);
	var c5 = parseInt(Num5);
	var Res = c1+c2+c3+c4+c5;
	$("#Tot"+numT).val(Res);
}

function numAlturaU(campo){
	var valor = campo;
	var RegExPattern = /^\d*$/;
    if(campo.value.match(RegExPattern)&&(valor.value<=35)){
    	return true;
    }else{
        alert("Solo puede ingresar numeros, sin espacios y menor o igual a 35 Cm.");
        campo.value="";
        campo.focus();
    }
}

function numPesoKg(campo){
	var valor = campo;
	var RegExPattern = /^\d*$/;
    if(campo.value.match(RegExPattern)&&(valor.value<=500)){
    	return true;
    }else{
        alert("Solo puede ingresar numeros y sin espacios");
        campo.value="";
        campo.focus();
    }
}

function desRadBut(){
	if(document.getElementById("1a").checked){
		document.getElementById("3a").checked=true;
		document.getElementById("4a").checked=true;
		document.getElementById("5a").checked=true;
		document.getElementById("6a").checked=true;
		document.getElementById("7a").checked=true;
		document.getElementById("8a").checked=true;
		document.getElementById("1b").checked=true;
		document.getElementById("2b").checked=true;
		document.getElementById("3b").checked=true;
		document.getElementById("4b").checked=true;
	}else{
		document.getElementById("3a").checked=false;
		document.getElementById("4a").checked=false;
		document.getElementById("5a").checked=false;
		document.getElementById("6a").checked=false;
		document.getElementById("7a").checked=false;
		document.getElementById("8a").checked=false;
		document.getElementById("1b").checked=false;
		document.getElementById("2b").checked=false;
		document.getElementById("3b").checked=false;
		document.getElementById("4b").checked=false;
	}
}

function RbChec(nom,nms){
	var res = $("input[name='"+nom+"']:checked").val();
	if(res=="Si"){
		$(nms).find("[value='0']").remove();
		$(nms).val(1);
		$(nms).removeAttr('disabled');
	}else if(res=="No"){
		$(nms).append("<option value='0'selected='selected'>0</option>");
		$(nms).attr('disabled','disabled');
	}
}

function marcar(){
	if($("#CheckSol").prop("checked")){
		$(".selconv").val(0);
		$(".rbConNo").prop("checked", true);
		$(".selconv").attr('disabled','disabled');
		$(".rbconv").attr('disabled','disabled');
	}else{
		$(".rbconv").removeAttr('disabled');
		$(".selconv").removeAttr('disabled');
		$(".rbConSi").prop("checked", true);
	}
}

function VerificarOpcionParto(){
	var opcion = $("input[name=opcionP]:checked").val();
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState==4){
			$("#resultadoE").html(ajax.responseText);
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=0parto&parto="+opcion);
}

//Metodo para verificar paciente gestante
function verificarGestante(){
	ajax=getXMLObject();
	var tdoc = $("#cmbTipoDoc").val();
	var ndoc = $("#txtNumDocumento").val();
	///alert();
	ajax.open("POST","ControlConfiguracionPP",true);
	if(tdoc=="Seleccione"||ndoc==""){
		alert("Datos incorrectos, por favor debe ingresar el documento del paciente a buscar!");
	}else if(tdoc=="RC"){
		alert("Datos incorrectos, por favor llene los datos correctamente!");
	}else{
		ajax.onreadystatechange = function(){
			if(ajax.readyState==4){
				if(ajax.responseText!=ndoc){
					$("#resultado").html(ajax.responseText);
					var est = document.getElementById("est");
					if(est!=null){
						$("#txtNumDocumento").val("");
					}
				}else if(ajax.responseText==ndoc){
					window.location.href="Adm_EncuestaGes2.jsp?tDoc="+tdoc+"&nDoc="+ndoc;
				}						
			}
		};
	}
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=1&tdoc="+tdoc+"&ndoc="+ndoc);
}

function fcha(){
	var f = new Date();
	var a=(f.getFullYear() + "-" + 
			(f.getMonth() +1) + "-" +
			f.getDate());
	$("#TxtfechaActual").val(a);
	return a;
}

function validarGuardarGes(){
	var res = null;
	$("#page").find(":input:radio").each(function(){
		var nm = $(this).attr("name");
		if($("input[name='"+nm+"']:radio").is(':checked')){
			res = true;
		}else{
			res = false;
		}
    });
	alert(res);
	return res;
}

function GuardarGestante1(){
	var FS = fcha();
	var res = validarGuardarGes();
	//Cajas de texto
	var TrimA = $("#Trimestre").val();
	var fecEco = $("#TxtFechaE").val();
	var fecM = $("#TxtFechaGes").val();
	var tipDoc = $("#cmbTipoDoc").val();
	var codUser = $("#txtCodusuario").val();
	var codPac = $("#TxtCodPac").val();
	var TxtFechaP = $("#TxtFechaP").val();
	var TxtCedPac = $("#txtNumDocumento").val();
	//Radios
	var RbAna = $("input[name=RadAn1]:checked").val();
	var RbPri = $("input[name=RadPri1]:checked").val();
	var RbSec = $("input[name=RadSec1]:checked").val();
	var RbBac = $("input[name=RadBac1]:checked").val();
	var RbEdf = $("input[name=RadNof1]:checked").val();
	var RbUni = $("input[name=RadUni1]:checked").val();
	var RbHijo = $("input[name=RadCon1]:checked").val();
	var RbNieto = $("input[name=RadCon2]:checked").val();
	var RbPadre = $("input[name=RadCon3]:checked").val();
	var RbCon = $("input[name=RadCon4]:checked").val();
	var tipoGes = $("input[name=opcionP]:checked").val();
	//Selects
	var tDoc = $("#cmbTipoDoc").val();
	var SelHijo = $("#select1").val();
	var SelNieto = $("#select2").val();
	var SelPadre = $("#select3").val();
	var SelCon = $("#select4").val();
	//Check
	if($("#CheckSol").prop("checked")){
		ChSolo2=$("#CheckSol").val();
	}else{
		ChSolo2="No";
	}
	//alert(res);
	if(res==false){
		alert("Faltaron datos por administrar!");
	}else if(res==true){
		ajax = getXMLObject();
		var Cad = null;
		ajax.open("POST","ControlConfiguracionPP", true);
		ajax.onreadystatechange = function (){
			if(ajax.readyState == 4) {
				Cad = ajax.responseText;
				if(Cad==TxtCedPac){
					alert("Redirigiendo a la encuesta");
					window.location.href="Adm_EncuestaGes2.jsp?tDoc="+tDoc+"&nDoc="+TxtCedPac;
				}else if(Cad!=TxtCedPac){
					alert("Se guardo exitosamente");
					window.location.href="Adm_EncuestaGes2.jsp?tDoc="+tDoc+"&nDoc="+TxtCedPac;
					document.getElementsByTagName("input");
				}
			}
		};
		ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ajax.send("&op=3&txtfechaP="+TxtFechaP+"&txtcedpac="+TxtCedPac+"&rbana="+RbAna+
				"&rbpri="+RbPri+"&rbsec="+RbSec+"&rbbac="+RbBac+"&rbedf="+RbEdf+
				"&rbuni="+RbUni+"&rbhijo="+RbHijo+"&rbnieto="+RbNieto+"&rbpadre="+RbPadre+
				"&rbcon="+RbCon+"&selhijo="+SelHijo+"&selnieto="+SelNieto+"&selpadre="+
				SelPadre+"&selcon="+SelCon+"&fechaEn="+FS+"&chsolo="+ChSolo2+"&trim="+TrimA+
				"&txtfecmen="+fecM+"&tipdoc="+tipDoc+"&coduser="+codUser+"&codpac="+codPac+
				"&tipoGes="+tipoGes+"&fecEco="+fecEco);
	}
}

function Enter(){
	if((window.event.keyCode==13)){
		verificarGestante();
	}
}

function Enter2(){
	if((window.event.keyCode==13)){
		ValidarGestante();
	}
}

function Enter3(){
	if((window.event.keyCode==13)){
		CalcularPartoEco();
	}
}

function Cargar1() {
	CargarPreguntas();
}

function ValidarArea1(){
	if(ValidarRbTx()==false){
		$("#contenido1").prop('checked',true);
		$("#contenido1").slideDown('slow');
		$('#SelA0').css('color','red');
		$('#SelA0').slideDown('slow');
		$("#SelMul0").slideDown('slow');
		$("#contenido4").hide("slide", { direction: "up" },'fast');
	}else if(ValRadD()==false){
		$("#contenido4").prop('checked',true);
		$("#contenido1").hide("slide", { direction: "up" },'fast');
		$("#contenido4").slideDown('slow');
	}else{
		GuardarE1();
		CargarPreguntas2();
		$("#Anterior").attr('disabled',false);
	}
}

function ValidarArea2(){
	if(ValidarRbTx1()==false){
		$("#contenido1").prop('checked',true);
		$("#contenido1").show('slow');
		$("#contenido2").hide("slide", { direction: "up" },'fast');
		$("#contenido3").hide("slide", { direction: "up" },'fast');
	}else if((ValidarRBs()==false)||(ValidarRBs2()==false)){
		$("#contenido2").prop('checked',true);
		$("#contenido2").show('slow');
		$("#contenido1").hide("slide", { direction: "up" },'fast');
		$("#contenido3").hide("slide", { direction: "up" },'fast');
	}else if((TxtExaFisicos()==false)||(RadExaFisicos()==false)){
		$("#contenido3").prop('checked',true);
		$("#contenido3").show('slow');
		$("#contenido1").hide("slide", { direction: "up" },'fast');
		$("#contenido2").hide("slide", { direction: "up" },'fast');
	}else{
		GuardarE2();
		CargarPreguntas3();
	}
}

function ValidarArea3(){
	if((ValidarTxtEvol()==false)||(ValidarRbEvol()==false)){
		$("#contenido1").prop('checked',true);
		$("#contenido1").show('slow');
		$("#contenido2").hide("slide", { direction: "up" },'fast');
		$("#contenido3").hide("slide", { direction: "up" },'fast');
	}else if(ValidarTxtEco()==false){
		$("#contenido2").prop('checked',true);
		$("#contenido2").show('slow');
		$("#contenido1").hide("slide", { direction: "up" },'fast');
		$("#contenido3").hide("slide", { direction: "up" },'fast');
	}else{
		GuardarE3();
		CargarPreguntas4();
	}
}

function Siguiente(){
	var Estado = document.getElementById("Estado").value;
	RecorrerRadioBD();
	if(Estado=="1"){
		ValidarArea1();
		//CargarPreguntas2();
	}else if(Estado=="2"){
		ValidarArea2();
		//CargarPreguntas3();
	}else if(Estado=="3"){
		ValidarArea3();
		//CargarPreguntas4();
	}else if(Estado=="4"){
		GuardarE4();
		//ReporteGestantes();
	}
}

function Anterior(){
	var Sig = document.getElementById("Siguiente");
	var Ant = document.getElementById("Anterior");	
	var Estado = document.getElementById("Estado").value;
	if(Estado=="2"){
		CargarPreguntas();
		Ant.disabled = true;
	}else if(Estado=="3"){
		CargarPreguntas2();
		Ant.disabled = false;
		Sig.disabled = false;
	}else if(Estado=="4"){
		CargarPreguntas3();
		Ant.disabled = false;
		Sig.disabled = false;
	}else if(Estado=="5"){
		CargarPreguntas4();
		Ant.disabled = false;
		Sig.disabled = false;
	}
}

////////////////Spinner///////////////////////
function Spinn(){
	$(function(){
		$( "#spinner" ).spinner({
	      min: 0,max: 100
	    });
	});
}
////////////////Funciones del Menu///////////////////////

///////////////Antecedentes
function ImprimirAntecedentes(Genero){
	var CodPac = $("#TxtCodPac").val();
	if(Genero=="1"){
		//genero Femenino
		paginaf="hic_ImprimirAntecedenteFemenino.jsp?CodPac="+CodPac;			
	    var opcionesf="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opcionesf =opcionesf+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(paginaf,"NUEVA",opcionesf);
	}if(Genero=="2"){
		//genero Masculino
		pagina="hic_ImprimirAntecedentes.jsp?CodPac="+CodPac;			
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
	}	
}

function Antecedentes(){
	$("#Siguiente").attr('disabled',true);
	$("#Anterior").attr('disabled',true);
	var CedPac = $("#TxtCedPac").val();
	var CodPac = $("#TxtCodPac").val();
	ajax=getXMLObject();
    ajax.open("POST","ControlMultiplePacientes",true);
    ajax.onreadystatechange  = function(){
    	if (ajax.readyState == 4) {				
    		var Contenido = ajax.responseText;
    		$("#preg").html(Contenido);
    		$("#cab").hide();
  	   	}
	  };
  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=4&CedPac="+CedPac+"&CodPac="+CodPac);
}
function RadioAntecedentes() {	
	ajax=getXMLObject();
	var radioButtons = document.getElementsByName("radiobutton");
	var CedPac = $("#TxtCedPac").val();
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
	         ajax.open("POST","ControlMultiplePacientes",true);
	         ajax.onreadystatechange  = function(){
	        	 if (ajax.readyState == 4) {
	        		 var Contenido = ajax.responseText;
	         		$("#Antecedente").html(Contenido);
	        	 }
	         };
	         ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	         ajax.send("valor="+val+"&CedPac="+CedPac);
		  }	     
    }
}
/////////////////Diagnosticos Personales
function DiagnosticoPaciente(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("TxtCod").value;
	var CedPac=document.getElementById("TxtCedPac").value;
	var hora = horaFormato();
	var fecha=document.getElementById("TxtFecha").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	var observacion=document.getElementById("txtObservacioDiagnostico").value;
	var CodPac=document.getElementById("TxtCodPac").value;
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
			xmlhttp.send("valor=6&CedPac="+CedPac+"&CodDiagnostico="+CodDiagnostico+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&observacion="+observacion+"&CodPac="+CodPac); //Posting txtname to Servlet "&CodAdm="+CodAdm+
		}
	}
}

function CargarDiagnostico(){	
	var contenido=document.getElementById("Antecedente");
	var CedPac=document.getElementById("TxtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControladorMedico",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=perso&CedPac="+CedPac);
}

function Observacion(CodDiag) {		
	pagina="hic_ObserDiagnostico.jsp?CodDiag="+CodDiag;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=400, height=200, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}

function omitirPersonal(CodigoAntPersonal){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarDiagnostico();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirAnte&CodigoAntPersonal="+CodigoAntPersonal); //Posting txtname to Servlet
}

/////////////////Diagnosticos Quirurjicos
function IngresarProcedimiento(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("TxtCod").value;
	var CodPac=document.getElementById("TxtCodPac").value;
	var hora = horaFormato();
	var fecha=document.getElementById("TxtFecha").value;
	var CedPac=document.getElementById("TxtCedPac").value;
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
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarProcedimiento();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngrQx&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&fechaQx="+txtFechaCx+"&observacion="+txtObservacionCx+"&codigoQx="+txtCodigoQx+"&cedula="+CedPac); //Posting txtname to Servlet
		}
	}
}

function omitirQuirurjico(CodigoQx){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarProcedimiento();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirQx&CodigoQx="+CodigoQx); //Posting txtname to Servlet
}

function CargarProcedimiento(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("TxtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6&CedPac="+CedPac); //Posting txtname to Servlet
}

////////////////////Antecedentes Alergias
function CargarAlergias(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("TxtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7&CedPac="+CedPac); //Posting txtname to Servlet
}

function omitirAlergico(CodigoAler){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarAlergias();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirAler&CodigoAler="+CodigoAler); //Posting txtname to Servlet
}

function IngresaAlergia(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("TxtCod").value;
	var CodPac=document.getElementById("TxtCodPac").value;
	var hora = horaFormato();
	var fecha=document.getElementById("TxtFecha").value;
	var Alergia=document.getElementById("txtAlergia").value;
	var Reaccion=document.getElementById("txtReaccion").value;
	var CedPac=document.getElementById("TxtCedPac").value;
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
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarAlergias();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngreAler&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Alergia="+Alergia+"&Reaccion="+Reaccion+"&cedula="+CedPac); //Posting txtname to Servlet
		}
	}
}

////////////////////////Antecedentes Toxicologia
function IngresarToxico(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("TxtCod").value;
	var CodPac=document.getElementById("TxtCodPac").value;
	var hora = horaFormato();
	var fecha=document.getElementById("TxtFecha").value;
	var Toxicologia=document.getElementById("cmbToxicologia").value;
	var ObservacionTx=document.getElementById("txtObservacionTx").value;
	var CedPac=document.getElementById("TxtCedPac").value;
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
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarToxicologia();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngTox&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&tipoTx="+Toxicologia+"&ObservacionTx="+ObservacionTx+"&cedula="+CedPac); //Posting txtname to Servlet
		}
	}
}
 
function CargarToxicologia(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("TxtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8&CedPac="+CedPac); //Posting txtname to Servlet
}

function omitirToxico(CodigoTx){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarToxicologia();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirToxico&CodigoTx="+CodigoTx); //Posting txtname to Servlet
}

/////////////////////////Antecedentes Familiares
function IngresaAntFamiliares(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("TxtCod").value;
	var CodPac=document.getElementById("TxtCodPac").value;
	var hora = horaFormato();
	var fecha=document.getElementById("TxtFecha").value;
	var Diagnostico=document.getElementById("txtCodDiagnostico").value;
	var ObserFami=document.getElementById("txtObseFami").value;
	var familiar=document.getElementById("cmbFamilia").value;
	var CedPac=document.getElementById("TxtCedPac").value;
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
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarAnteFamiliar();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngreFami&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Diagnostico="+Diagnostico+"&ObserFami="+ObserFami+"&familiar="+familiar+"&cedula="+CedPac); //Posting txtname to Servlet
		}
		
	}
}

function CargarAnteFamiliar(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("TxtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=9&CedPac="+CedPac); //Posting txtname to Servlet
}

//////////////////////Antecedentes Transfuciones
function RadioTrans() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('OpcTransf');
	var radioButtons = document.getElementsByName("radioTrans");
	var CodPac=document.getElementById("TxtCodPac").value;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
	         ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	         ajax.onreadystatechange  = function(){
	        	 if (ajax.readyState == 4) {
	        		 contenido.innerHTML = ajax.responseText;	
	        	 }
	         };
	         ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	         ajax.send("valor="+val+"&CodPac="+CodPac); //Posting txtname to Servlet
		  }	     
    }
}

function GuardarSiTrans(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("TxtCod").value;
	var CodPac=document.getElementById("TxtCodPac").value;
	var hora = horaFormato();
	var fecha=document.getElementById("TxtFecha").value;
	var CedPac=document.getElementById("TxtCedPac").value;
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
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarTransfucion();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngTran&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Cantidad="+Cantidad+"&FechaTransfucion="+FechaTransfucion+"&transfucion="+transfucion+"&ObservacionTr="+ObservacionTr+"&cedula="+CedPac); //Posting txtname to Servlet
		}
		
	}
}

function CargarTransfucion(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("TxtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	ajax.send("valor=10&CedPac="+CedPac); //Posting txtname to Servlet
}

function omitirTranfucion(CodigoTranfucion){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarTransfucion();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirTranfucion&CodigoTranfucion="+CodigoTranfucion); //Posting txtname to Servlet
}

function GuardarNoTrans(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("TxtCod").value;
	var CodPac=document.getElementById("TxtCodPac").value;
	var hora = horaFormato();
	var fecha=document.getElementById("TxtFecha").value;
	var CedPac=document.getElementById("TxtCedPac").value;
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
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarTransfucion();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngTran&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Cantidad="+Cantidad+"&FechaTransfucion="+FechaTransfucion+"&transfucion="+transfucion+"&ObservacionTr="+ObservacionTr+"&cedula="+CedPac); //Posting txtname to Servlet
		}
	}
}

////////////////////////Antecedentes GINECOBSTETRA

function IngresarGineco(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("TxtCod").value;
	var CedPac=document.getElementById("TxtCedPac").value;
	var hora = horaFormato();
	var fecha=document.getElementById("txtFecha").value;
	var CodPac=document.getElementById("TxtCodPac").value;
	var gestas=document.getElementById("txtGestas").value;
	var partos=document.getElementById("txtPartos").value;
	var abortos=document.getElementById("txtAbortos").value;
	var cesareas=document.getElementById("txtCesareas").value;
	var espontaneos=document.getElementById("txtEspontaneos").value;
	var menarquia=document.getElementById("txtMenarquia").value;
	var fum=document.getElementById("txtFum").value;
	var furs=document.getElementById("txtFurs").value;
	var ivs=document.getElementById("txtIvs").value;
	var its=document.getElementById("txtIts").value;
	var compasexual=document.getElementById("txtComSexu").value;
	
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
	if(CodPac==""){
		alert("El Diagnostico No Puede Ir Vacio.");
	}
	if((usuario!="null")&&(CedPac!="")&&(hora!="")&&(fecha!="")&&(CodPac!="")){
		ajax=getXMLObject();
		ajax.open("POST","ControlMultiplePacientes",true);
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				CargarGinecobstetra();
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=IngGine&CedPac="+CedPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&CodPac="+CodPac+"&gestas="+gestas+"&partos="+partos+"&abortos="+abortos+"&cesareas="+cesareas+"&espontaneos="+espontaneos+"&menarquia="+menarquia+"&fum="+fum+"&furs="+furs+"&ivs="+ivs+"&its="+its+"&compasexual="+compasexual); //Posting txtname to Servlet
	}
}

function CargarGinecobstetra(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("TxtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11&CedPac="+CedPac);
}

function ActualizarGineco(){
	ajax=getXMLObject();
	var CodigoGineco=document.getElementById("txtCodGineco").value;
	var gestas=document.getElementById("txtGestas").value;
	var partos=document.getElementById("txtPartos").value;
	var abortos=document.getElementById("txtAbortos").value;
	var cesareas=document.getElementById("txtCesareas").value;
	var espontaneos=document.getElementById("txtEspontaneos").value;
	var menarquia=document.getElementById("txtMenarquia").value;
	var fum=document.getElementById("txtFum").value;
	var furs=document.getElementById("txtFurs").value;
	var ivs=document.getElementById("txtIvs").value;
	var its=document.getElementById("txtIts").value;
	var compasexual=document.getElementById("txtComSexu").value;
	ajax.open("POST","ControlMultiplePacientes",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarGinecobstetra();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ActuGineco&CodigoGineco="+CodigoGineco+"&gestas="+gestas+"&partos="+partos+"&abortos="+abortos+"&cesareas="+cesareas+"&espontaneos="+espontaneos+"&menarquia="+menarquia+"&fum="+fum+"&furs="+furs+"&ivs="+ivs+"&its="+its+"&compasexual="+compasexual); //Posting txtname to Servlet
}

/////////////////////////Antecedentes Medicamentos
function IngresarMd(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("TxtCod").value;
	var CodPac=document.getElementById("TxtCodPac").value;
	var hora = horaFormato();
	var fecha=document.getElementById("TxtFecha").value;
	var CedPac=document.getElementById("TxtCedPac").value;
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
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarMedicamentos();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngMed&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&observacion="+txtObservacionMd+"&codigoMed="+txtCodigoMed+"&cedula="+CedPac); //Posting txtname to Servlet
		}
		
	}
}

function CargarMedicamentos(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("TxtCedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12&CedPac="+CedPac);
}

function omitirAntMedicamento(CodigoMedicamento){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarMedicamentos();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirAntMedica&CodigoMedicamento="+CodigoMedicamento); //Posting txtname to Servlet	
}

///////////////Imagenologia
function MostrarImagenologia(){
	$("#Siguiente").attr('disabled',true);
	$("#Anterior").attr('disabled',true);
	CodPac = $("#TxtCodPac").val();
	ajax=getXMLObject();
	ajax.open("POST","ControlFormatosPestanas",true);
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			$("#preg").html(ajax.responseText);
			$("#cab").hide();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4&CodPac="+CodPac);
}

function mostarexamenes(codIce,usuario) {		
	pagina="img_ReporteImg.jsp?codIce="+codIce+"&usuario="+usuario;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}

///////////////MostrarAtenciones
function MostrarAtenciones(){
	$("#Siguiente").attr('disabled',true);
	$("#Anterior").attr('disabled',true);
	var CodPac = $("#TxtCodPac").val();
	ajax=getXMLObject();
    ajax.open("POST","ControlVerFormatos",true);
    ajax.onreadystatechange  = function(){
    	if (ajax.readyState == 4) {				
    		$("#preg").html(ajax.responseText);
			$("#cab").hide();
  	   	}
	};
  	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=5&CodPac="+CodPac);
}

function MostrarAtencion(CodAdm){
	ajax=getXMLObject();
    ajax.open("POST","ControlVerFormatos",true);
    ajax.onreadystatechange  = function(){
    	if (ajax.readyState == 4) {				
    		$("#FormatosAnteriores").html(ajax.responseText);
  	   	}
	};
  	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=5.1&CodAdm="+CodAdm);
}

function MostrarAtencionCE(CodHorarioMedico){
	ajax=getXMLObject();
	ajax.open("POST","ControlVerFormatos",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			$("#FormatosAnteriores").html(ajax.responseText);
	   	}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5.2&CodHorarioMedico="+CodHorarioMedico);
}

function ImprimirFormatoCE(usuario,CodFormato,anio,mes,dia,hora,minuto,segundo,CodPac,TipoFormato,CodHorarioMedico){
	var FechaFormato=anio+"-"+mes+"-"+dia;
	var HoraFormato=hora+":"+minuto+":"+segundo;
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

function ImprimirFormato(usuario,CodFormato,anio,mes,dia,hora,minuto,segundo,CodPac,TipoFormato,CodAdmision,estado,Cofirma){
	var FechaFormato=anio+"-"+mes+"-"+dia;
	var HoraFormato=hora+":"+minuto+":"+segundo;
	if(estado=="0"){
		alert("Para Imprimir El Formato, Primero Debe Estar Guardado.");
	}else{
		if(Cofirma=="1"){
			//tiene cofirma
			if(TipoFormato=="1"){
				//Si el Formato es Historia Clinica de Urgencia y si es 1 no se puede repetir el mismo formato en la misma admision
				pagina="hic_ImprimirFormatoHCO.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario+"&CodAdmision="+CodAdmision;			
				var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
				opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
				window.open(pagina,"NUEVA",opciones);
			}
			if(TipoFormato=="0"){
				pagina="hic_ImprimirFormatoCO.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario;			
				var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
				opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
				window.open(pagina,"NUEVA",opciones);
			}
		}else{	
			// no tienen cofirm
			if(TipoFormato=="1"){
				//Si el Formato es Historia Clinica de Urgencia y si es 1 no se puede repetir el mismo formato en la misma admision
				pagina="hic_ImprimirFormatoH.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario+"&CodAdmision="+CodAdmision;			
				var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
				opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
				window.open(pagina,"NUEVA",opciones);
			}
			if(TipoFormato=="0"){
				pagina="hic_ImprimirFormato.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario;			
				var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
				opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
				window.open(pagina,"NUEVA",opciones);
			}
			if(TipoFormato=="2"){
				pagina="hic_ImprimirFormatoEnfermeria.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario;			
				var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
				opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
				window.open(pagina,"NUEVA",opciones);
			}
		}
	}
}

////////////////Preguntas de la encuesta//////////////////
function CargarPreguntas(){
	$("#Siguiente").attr('disabled',false);
	var trim = Trimestre();
	var etapa="Etapa 2-1";
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP", true);
	ajax.onreadystatechange = function (){
		if(ajax.readyState == 4) {
			$("#preg").html(ajax.responseText);
			$("#EtapaCabecera").html(etapa);
			$("#cab").show();
			cargarMenu();
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=5&etapa="+etapa+"&trim="+trim);
}

function CargarPreguntas2(){
	var trim = Trimestre();
	var etapa="Etapa 2-2";
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			$("#preg").html(ajax.responseText);
			$("#preg").animate({height:'slow'});
			$("#EtapaCabecera").html(etapa);
			Spinn();
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("op=6&etapa="+etapa+"&trim="+trim);
}

function CargarPreguntas3(){
	var fecA = fcha();
	var codPac = $("#TxtCodPac").val();
	var codPacI = $("#TxtCodInt").val();
	var trim = Trimestre();
	var cod = document.getElementById("TxtCod").value;
	var etapa="Etapa 2-3";
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			$("#preg").html(ajax.responseText);
			$("#EtapaCabecera").html(etapa);
			var vale = $("#tipGes").val();
			var id = $("[name='ges2']").attr('id');
			$("#"+id).val(vale);
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("op=7&etapa="+etapa+"&trim="+trim+"&codigo="+cod+"&fecA="+fecA+"&codpac="+codPac+"&codpacI="+codPacI);	
}

function CargarPreguntas4(){
	var trim = Trimestre();
	var etapa="Etapa 2-4";
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			$("#preg").html(ajax.responseText);
			$("#EtapaCabecera").html(etapa);
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("op=8&etapa="+etapa+"&trim="+trim);	
}

//////////////////Reportes///////////////////
function ReporteGestantes(){
	var trim = Trimestre();
	var etapa="REPORTES";
	ajax=getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			$("#preg").html(ajax.responseText);
			$("#EtapaCabecera").html(etapa);
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("op=reportes&trim="+trim);	
}

function orgFec(campo){
	var fm = campo;
	var ret = fm.toString().split("-");
	var año = ret[2];var mes = ret[0];var dia = ret[1];
	var fecM = (año+"-"+mes+"-"+dia);
	return fecM;
}

////////////////////Menu principal y datos de la gestante////////////////////
function cargarDatos(){
	var fecha = fcha();
	var tced = $("#TxtTipoDoc").val();
	var nced = $("#TxtCedPac").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4) {
			$("#Datos").html(ajax.responseText);
			CargarPreguntas();
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=4&tced="+tced+"&nced="+nced+"&fecha="+fecha);
}

function cargarMenu(){
	var codigo = $("#TxtCod").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4) {
			$("#Menu").html(ajax.responseText);
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=menu&codigo="+codigo);
}

function ComAnt(cont){
	if($("#RadSi"+cont).prop('checked')){
		$('#Txt'+cont).attr("disabled",false);
		$('#Txt'+cont).focus();
	}else if($("#RadNo"+cont).prop('checked')){
		$('#Txt'+cont).val('');
		$('#Txt'+cont).attr("disabled",true);
	}
}

function ComExaFis(cont){
	if($("#RadP"+cont).prop('checked')){
		$('#TxtCab3'+cont).attr("disabled",false);
		$('#TxtCab3'+cont).focus();
	}else if($("#RadN"+cont).prop('checked')){
		$('#TxtCab3'+cont).val('');
		$('#TxtCab3'+cont).attr("disabled",true);
	}
}

function abrirdiv(id){
	if(id.style.display=="none"){
		$(id).show('slow');
	}else{
		$(id).hide('fast');
	}
}

function CajaRaTe(cont){
	if(($("#rbA"+cont).prop("checked"))&&($("#TxtrbA"+cont).val()=="")){
		alert("Debe llenar el campo!");
		$("#TxtrbA"+cont).focus();
	}else{
		return true;
	}
}

function abrirRB(div){
	$(div).find(":input:radio").each(function(){
		var id = $(this).attr("id");
		if($(this).prop("checked")){
			$('#Txt'+id).attr("disabled",false);
			$('#Txt'+id).focus();
		}else{
			$('#Txt'+id).val('');
			$('#Txt'+id).attr("disabled",true);
		}
    });
}

function Nulipara(id){
	if($(id).prop("checked")){
		$("input[name=checkA2]").attr("checked",false);
		$("input[name=checkA3]").attr("checked",false);
		$("input[name=checkA4]").attr("checked",false);
		$("input[name=checkA2]").attr("disabled",true);
		$("input[name=checkA3]").attr("disabled",true);
		$("input[name=checkA4]").attr("disabled",true);
		$(".nulipara").val("");
		$(".nulipara").attr("disabled",true);
	}else{
		$("input[name=checkA2]").removeAttr("disabled");
		$("input[name=checkA3]").removeAttr("disabled");
		$("input[name=checkA4]").removeAttr("disabled");
	}
}

function ColorRB(id){
	$(id).hide();
}

function ColorRB2(id,campo){
	if(campo!=""){
		$(id).hide();
	}else{
		$(id).show('slow');
	}
}

function ValidarRbTx1(){
	var res = null;
	$("#contenido1").find(":input:radio").each(function(){
		var id = $(this).attr("id");
		var nm = $(this).attr("name");
		if($("input[name='"+nm+"']:radio").is(':checked')){
			res = true;
			$('#x'+id).hide();
		}else{
			res = false;
			$('#x'+id).css('color','red');
			$('#x'+id).show('slow');
		}
    });
	return res;
}

function ValidarTxtEvol(){
	var res = null;
	$("#contenido1").find(".evol").each(function(){
		var id = $(this).attr("id");
		if($(this).val()==""){
			$('#S'+id).css('color','red');
			$('#S'+id).show('slow');
			res = false;
		}else{  
			$('#S'+id).hide();
			res = true;
		}
    });
	return res;
}

function ValidarTxtEco(){
	var res = null;
	$("#contenido2").find(".evol2").each(function(){
		var id = $(this).attr("id");
		if($(this).val()==""){
			$('#S'+id).css('color','red');
			$('#S'+id).show('slow');
			res = false;
		}else{  
			$('#S'+id).hide();
			res = true;
		}
    });
	return res;
}

function ValidarRbEvol(){
	var res = null;
	$("#contenido1").find(":input:radio").each(function(){
		var id = $(this).attr("id");
		var nm = $(this).attr("name");
		if($("input[name='"+nm+"']:radio").is(':checked')){
			res = true;
			$('#S'+id).hide();
		}else{
			res = false;
			$('#S'+id).css('color','red');
			$('#S'+id).show('slow');
		}
    });
	return res;
}

function RadExaFisicos(){
	var res = null;
	$("#contenido3").find(":input:radio").each(function(){
		var nm = $(this).attr("name");
		var id = $(this).attr("id");
		if($("input[name='"+nm+"']:radio").is(':checked')){
			$('#S'+id).hide();
			res = true;
		}else{
			$('#S'+id).css('color','red');
			$('#S'+id).show('slow');
			res = false;
		}
    });
	return res;
}

function TxtExaFisicos(){
	var res = null;
	$("#contenido3").find(".ExFis").each(function(){
		var id = $(this).attr("id");
		if($(this).val()==""){
			$('#S'+id).css('color','red');
			$('#S'+id).show('slow');
			res = false;
		}else{  
			$('#S'+id).hide();
			res = true;
		}
    });
	return res;
}

function ValidarRbTx(){
	var res = null;
	$("#contenido1").find(":input:radio").each(function(){
		var nm = $(this).attr("name");
		if($("input[name='"+nm+"']:radio").is(':checked')){
			res = true;
		}else{
			res = false;
		}
    });
	return res;
}

function ValRadD(){
	var res = null;
	$("#contenido4").find(":input:radio").each(function(){
		var id = $(this).attr("id");
		var nm = $(this).attr("name");
		if($("input[name='"+nm+"']:radio").is(':checked')){
			$('#x'+id).hide();
			res = true;
		}else{
			$('#x'+id).css('color','red');
			$('#x'+id).show('slow');
			res = false;
		}
    });
	return res;
}

function abrirCabezeras(){
	if($("#RaExp1").prop("checked")){
		$("#contenido1").slideDown('slow');
		$("#contenido2").hide("slide", { direction: "up" },'fast');
		$("#contenido3").hide("slide", { direction: "up" },'fast');
		$("#contenido4").hide("slide", { direction: "up" },'fast');
	}else if($("#RaExp2").prop("checked")){
		$("#contenido2").slideDown('slow');
		$("#contenido1").hide("slide", { direction: "up" },'fast');
		$("#contenido3").hide("slide", { direction: "up" },'fast');
		$("#contenido4").hide("slide", { direction: "up" },'fast');
	}else if($("#RaExp3").prop("checked")){
		$("#contenido3").slideDown('slow');
		$("#contenido1").hide("slide", { direction: "up" },'fast');
		$("#contenido2").hide("slide", { direction: "up" },'fast');
		$("#contenido4").hide("slide", { direction: "up" },'fast');
	}else if($("#RaExp4").prop("checked")){
		$("#contenido4").slideDown('slow');
		$("#contenido1").hide("slide", { direction: "up" },'fast');
		$("#contenido2").hide("slide", { direction: "up" },'fast');
		$("#contenido3").hide("slide", { direction: "up" },'fast');
	}
}

function abrirCabezeras2(){
	if($("#RaExp1").prop("checked")){
		$("#contenido1").slideDown('2000');
		$("#contenido2").hide("slide", { direction: "up" },'fast');
		$("#contenido3").hide("slide", { direction: "up" },'fast');
	}else if($("#RaExp2").prop("checked")){
		$("#contenido2").slideDown('2000');
		$("#contenido1").hide("slide", { direction: "up" },'fast');
		$("#contenido3").hide("slide", { direction: "up" },'fast');
	}else if($("#RaExp3").prop("checked")){
		$("#contenido3").slideDown('2000');
		$("#contenido1").hide("slide", { direction: "up" },'fast');
		$("#contenido2").hide("slide", { direction: "up" },'fast');
	}
}

//////////Metodo Guardar etapas////////////

function GuardarE1(){
	//GuardarA();
	GuardarB();
	GuardarC();
	GuardarD();
	alert("Encuesta almacenada!");
}

function GuardarE2(){
	Guardar22_1();
	Guardar22_2();
	Guardar22_3();
	alert("Encuesta almacenada!");
}

function GuardarE3(){
	Guardar23_1();
	Guardar22_2();
	Guardar23_3();
	alert("Encuesta almacenada!");
}

function GuardarE4(){
	Guardar24_1();
	Guardar24_2();
	alert("Encuesta almacenada!");
}

//////////Metodos area A///////////////
function GuardarA1(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var etapa = "Etapa 2-1";
	var area = "A";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreRad = $("#La1").val();
	var codResRad = $("input[name=radioA]:checked").attr('class');
	var id = $("input[name=radioA]:checked").attr('id');
	var respRad = $("#T"+id).text();
	var Det = $("#Txt"+id).val();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	//alert(codPac+" "+cedPac+" "+etapa+" "+area+" "+fecha+" "+trim+" "+codPreRad+" "+codResRad+" "+respRad+" "+Det+" "+codUser+" "+codInt);
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=9&codPac="+codPac+"&cedPac="+cedPac+"&etapa="+etapa+
			"&area="+area+"&fecha="+fecha+"&trim="+trim+"&codPreg="+codPreRad+
			"&codResp="+codResRad+"&Resp="+respRad+"&Det="+Det+"&codUser="+codUser+"&codInt="+codInt);
}

function GuardarAN(nm){
	var id = $("input[name="+nm+"]:checked").attr('id');
	Nul = 1;
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var etapa = "Etapa 2-1";
	var area = "A";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPre = $("#La2").val();
	var codRes = $("input[name="+nm+"]").attr('class');
	var respRad = $("#T"+id).text();
	var Det = $("#Txt"+id).val();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	if(Det==""){
		Det=0;
	}
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=9.1&codPac="+codPac+"&cedPac="+cedPac+"&etapa="+etapa+
			"&area="+area+"&fecha="+fecha+"&trim="+trim+"&codPreg="+codPre+
			"&codResp="+codRes+"&Resp="+respRad+"&Det="+Det+"&codUser="+codUser+"&codInt="+codInt);
}

function GuardarA2(nm){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var etapa = "Etapa 2-1";
	var area = "A";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPre = $("#La2").val();
	var codRes = $("input[name="+nm+"]:checked").attr('class');
	var id = $("input[name="+nm+"]:checked").attr('id');
	var respRad = $("#T"+id).text();
	var Det = $("#Txt"+id).val();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	if(Det==""){
		Det=0;
	}
	//alert(codPac+" "+cedPac+" "+etapa+" "+area+" "+fecha+" "+trim+" "+codPre+" "+codRes+" "+respRad+" "+Det+" "+codUser+" "+codInt);
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=9.2&codPac="+codPac+"&cedPac="+cedPac+"&etapa="+etapa+
			"&area="+area+"&fecha="+fecha+"&trim="+trim+"&codPreg="+codPre+
			"&codResp="+codRes+"&Resp="+respRad+"&Det="+Det+"&codUser="+codUser+"&codInt="+codInt);
	
}

function GuardarA3(nm){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var etapa = "Etapa 2-1";
	var area = "A";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPre = $("#La3").val();
	var codRes = $("input[name="+nm+"]:checked").attr('class');
	var id = $("input[name="+nm+"]:checked").attr('id');
	var respRad = $("#T"+id).text();
	var Det = $("#Txt"+id).val();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	if(Det==""){
		Det=0;
	}
	alert(codPac+" "+cedPac+" "+etapa+" "+area+" "+fecha+" "+trim+" "+codPre+" "+codRes+" "+respRad+" "+Det+" "+codUser+" "+codInt);
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=9.2&codPac="+codPac+"&cedPac="+cedPac+"&etapa="+etapa+
			"&area="+area+"&fecha="+fecha+"&trim="+trim+"&codPreg="+codPre+
			"&codResp="+codRes+"&Resp="+respRad+"&Det="+Det+"&codUser="+codUser+"&codInt="+codInt);	
}

function GuardarA(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "A";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg = recorrerCodPreA();
	var codResp = recorrerCodRespRA();
	var codResp1 = recorrerCodResp1A();
	var codResp2 = recorrerCodResp2A();
	var RespR = recorrerRespRadioA();
	var Resp1 = recorrerRespRadio1A();
	var Resp2 = recorrerRespCheck2A();
	var NumDR = recorrerNumDetRA();
	var NumD1 = recorrerNumDet1A();
	var NumD2 = recorrerNumDet2A();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=9&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="
	+fecha+"&trim="+trim+"&codPreg="+codPreg+"&codResp="+codResp+"&RespR="
	+RespR+"&Resp1="+Resp1+"&Resp2="+Resp2+"&NumD1="+NumD1+"&NumD2="
	+NumD2+"&NumDR="+NumDR+"&codUser="+codUser+"&codInt="+codInt+
	"&codResp1="+codResp1+"&codResp2="+codResp2);
}

function recorrerRespRadioA(){
	var arrayID = [];
	$("#SelMul0").find(":input:radio:checked").each(function(){
		var id = $(this).attr('id');
		if($(this).prop('checked')){
			arrayID.push($("#T"+id).text());
		}
    });
	return arrayID;
} 

function recorrerRespRadio1A(){
	var arrayID = [];
	$("#SelMul").find(":input:checkbox:checked").each(function(){
		var id = $(this).attr('id');
		if($(this).prop('checked')){
			arrayID.push($("#T"+id).text());
		}
    });
	return arrayID;
}

function recorrerRespCheck2A(){
	var arrayID = [];
	$("#SelMul2").find(":input:checkbox").each(function(){
		var valor = "";
		if($(this).prop("checked")){
			valor = "Si";
			arrayID.push(valor);
		}else{
			valor = "No";
			arrayID.push(valor);
		}
    });
	return arrayID;
}

function recorrerNumDetRA(){
	var arrayID = [];
	$("#SelMul0").find(":input:radio:checked").each(function(){
		var id = $(this).attr('id');
		if($(this).prop('checked')){
			arrayID.push($("#Txt"+id).val());
		}
    });
	return arrayID;
}

function recorrerNumDet1A(){
	var arrayID = [];
	$("#SelMul").find(":input:checkbox:checked").each(function(){
		var id = $(this).attr('id');
		if($(this).prop('checked')){
			var valor = $("#Txt"+id).val();
			if(valor==""){
				arrayID.push(0);
			}else{
				arrayID.push($("#Txt"+id).val());
			}
		}
    });
	return arrayID;
}

function recorrerNumDet2A(){
	var arrayID = [];
	$("#SelMul2").find(":input:text").each(function(){
		if($(this).val()!=""){
			arrayID.push($(this).val());
		}else{
			arrayID.push(0);
		}
    });
	return arrayID;
}

function recorrerCodPreA(){
	var arrayID = [];
	$("#contenido1").find(".clasA1").each(function(){
		arrayID.push($(this).attr('value'));
    });
	return arrayID;
}

function recorrerCodRespRA(){
	var arrayID = [];
	$("#SelMul0").find(":input:radio").each(function(){
		if($(this).prop("checked")){
			var id = $(this).attr('id');
			var valor = $("#Tx"+id).val();
			arrayID.push(valor);
		}
    });
	return arrayID;
}

function recorrerCodResp1A(){
	var arrayID = [];
	$("#SelMul").find(":input:checkbox:checked").each(function(){
		arrayID.push( $(this).attr('class'));
    });
	return arrayID; 
}

function recorrerCodResp2A(){
	var arrayID = [];
	$("#SelMul2").find(".checkA").each(function(){
		arrayID.push( $(this).attr('name'));
    });
	return arrayID;
}

//////////Metodos area B///////////////
function GuardarB(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "B";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg = recorrerCodPreB();
	var Resp = recorrerRespCheckB();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=13&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="+fecha+
	"&trim="+trim+"&codPreg="+codPreg+"&Resp="+Resp+"&codUser="+codUser+"&codInt="+codInt);
}

function recorrerRespCheckB(){
	var arrayID = [];
	$("#contenido2").find(":input:checkbox").each(function(){
		var valor = "";
		if($(this).prop("checked")){
			valor = "Si";
			arrayID.push(valor);
		}else{
			valor = "No";
			arrayID.push(valor);
		}
    });
	return arrayID;
}

function recorrerCodPreB(){
	var arrayID = [];
	$("#contenido2").find(".clasB").each(function(){
		arrayID.push( $(this).attr('value'));
    });
	return arrayID;
}

//////////Metodos area C///////////////
function GuardarC(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "C";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg = recorrerCodPreC();
	var Resp = recorrerRespCheckC();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=14&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="+fecha+
	"&trim="+trim+"&codPreg="+codPreg+"&Resp="+Resp+"&codUser="+codUser+"&codInt="+codInt);
}

function recorrerRespCheckC(){
	var arrayID = [];
	$("#contenido3").find(":input:checkbox").each(function(){
		var valor = "";
		if($(this).prop("checked")){
			valor = "Si";
			arrayID.push(valor);
		}else{
			valor = "No";
			arrayID.push(valor);
		}
    });
	return arrayID;
}

function recorrerCodPreC(){
	var arrayID = [];
	$("#contenido3").find(".clasC").each(function(){
		arrayID.push($(this).attr('value'));
    });
	return arrayID;
}

///////////Metodos area D//////////

function GuardarD(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "D";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg1 = RecorrerCodPreD1();
	var codPreg2 = RecorrerCodPreD2();
	var codResp1 = RecorrerCodResD1();
	var codResp2 = RecorrerCodResD2();
	var codItem = RecorrerCodItemD();
	var RespR = RecorrerRadioBD();
	var RespS = RecorrerRespD();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=15&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="
	+fecha+"&trim="+trim+"&codPreg1="+codPreg1+"&codPreg2="+codPreg2+"&codResp1="
	+codResp1+"&codResp2="+codResp2+"&RespR="+RespR+"&RespS="+RespS+"&codItem="+codItem+
	"&codUser="+codUser+"&codInt="+codInt);
}

function RecorrerCodPreD1(){
	var arrayID = [];
	$("#contenido4").find(".clasD1").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function RecorrerCodPreD2(){
	var arrayID = [];
	$("#contenido4").find(".clasD3").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function RecorrerCodResD1(){
	var arrayID = [];
	$("#contenido4").find(".clasD2").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function RecorrerCodResD2(){
	var arrayID = [];
	$("#contenido4").find(".clasD4").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function RecorrerRadioBD(){
	var arrayID = [];
	$("#contenido4").find(":input:radio").each(function(){
		if($(this).prop("checked")){
			arrayID.push($(this).attr('value'));
		}
    });
	return arrayID;
}

function RecorrerCodItemD(){
	var arrayID = [];
	$("#contenido4").find(".clasD5").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function RecorrerRespD(){
	var arrayID = [];
	$("#contenido4").find("select option:selected").each(function(){
		arrayID.push($(this).text());
    });
	return arrayID;
}

//////////Metodos area 22_1///////////////
function Guardar22_1(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "22_1";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg = recorrerCodPre22_1();
	var Resp = recorrerRespRad();
	var detResp = recorrerDetRad();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=16&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="+fecha+
	"&trim="+trim+"&codPreg="+codPreg+"&Resp="+Resp+"&detResp="+detResp+"&codUser="+
	codUser+"&codInt="+codInt);
}

function recorrerRespRad(){
	var arrayID = [];
	$("#contenido1").find(":input:radio:checked").each(function(){
		if($(this).prop("checked")){
			arrayID.push($(this).attr('value'));
		}
    });
	arrayID.push("Si");
	return arrayID;
}

function recorrerCodPre22_1(){
	var arrayID = [];
	$("#contenido1").find(".clasA1").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function recorrerDetRad(){
	var arrayID = [];
	$("#contenido1").find(":input:text").each(function(){
		if($(this).val()!=""){
			arrayID.push($(this).val());
		}else if($(this).val()==""){
			arrayID.push("Ninguno");
		}
    });
	return arrayID;
}

//////////Metodos area 22_2///////////////
function Guardar22_2(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "22_2";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg1 = recorrerCodPreRad22_2();
	var codPreg2 = recorrerCodPreSel22_2();
	var codPreg3 = recorrerCodPre22_2();
	var Resp1 = recorrerRespRad22_2();
	var Resp2 = recorrerSel22_2();
	var Resp3 = recorrerRespSN22_2();
	var com = recorrerComen22_2();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=17&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="+fecha+
	"&trim="+trim+"&codPreg="+codPreg1+"&Resp="+Resp1+"&codUser="+codUser+"&codInt="+codInt
	+"&codPreg2="+codPreg2+"&Resp2="+Resp2+"&com="+com+"&codPreg3="+codPreg3+"&Resp3="+Resp3);
}

function recorrerCodPreRad22_2(){
	var arrayID = [];
	$("#contenido2").find(".clasB1").each(function(){
		arrayID.push($(this).attr('id'));
    });
	alert(arrayID);
	return arrayID;
}

function recorrerCodPreSel22_2(){
	var arrayID = [];
	$("#contenido2").find(".clasB5").each(function(){
		arrayID.push($(this).attr('id'));
    });
	alert(arrayID);
	return arrayID;
}

function recorrerCodPre22_2(){
	var arrayID = [];
	$("#contenido2").find(".clasB2").each(function(){
		arrayID.push($(this).attr('id'));
    });
	alert(arrayID);
	return arrayID;
}

function recorrerRespRad22_2(){
	var arrayID = [];
	$("#contenido2").find(".rbTC:checked").each(function(){
		if($(this).prop("checked")){
			arrayID.push($(this).attr('value'));
		}
    });
	alert(arrayID);
	return arrayID;
}

function recorrerRespSN22_2(){
	var arrayID = [];
	$("#contenido2").find(".radSN:checked").each(function(){
		if($(this).prop("checked")){
			arrayID.push($(this).attr('value'));
		}
    });
	alert(arrayID);
	return arrayID;
}

function recorrerSel22_2(){
	var arrayID = [];
	$("#contenido2").find(".clasB4").each(function(){
		var valor = $(this).val();
		arrayID.push(valor);
    });
	alert(arrayID);
	return arrayID;
}

function recorrerComen22_2(){
	var arrayID = [];
	$("#contenido2").find(".clasB3").each(function(){
		var valor = $(this).val();
		if(valor!=""){
			arrayID.push(valor);
		}else{
			arrayID.push("Sin comentarios...");
		}
    });
	alert(arrayID);
	return arrayID;
}

//////////Metodos area 22_3///////////////
function Guardar22_3(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "22_3";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg1 = recorrerCodPre22_3C1();
	var Resp1 = recorrerRes22_3C1();
	var codPreg2 = recorrerCodPre22_3C2();
	var Resp2 = recorrerResRad22_3C2();
	var Det = recorrerDet22_3C();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=18&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="+fecha+
	"&trim="+trim+"&codPreg="+codPreg1+"&Resp="+Resp1+"&codUser="+codUser+"&codInt="+codInt
	+"&codPreg1="+codPreg1+"&codPreg2="+codPreg2+"&Resp2="+Resp2+"&Det="+Det);
}

function recorrerCodPre22_3C1(){
	var arrayID = [];
	$("#contenido3").find(".clasC1").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function recorrerRes22_3C1(){
	var arrayID = [];
	$("#contenido3").find(".ExFis").each(function(){
		var valor = $(this).val();
		if(valor==""){
			arrayID.push(0);
		}else{
			arrayID.push(valor);
		}
    });
	return arrayID;
}

function recorrerCodPre22_3C2(){
	var arrayID = [];
	$("#contenido3").find(".clasC2").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function recorrerResRad22_3C2(){
	var arrayID = [];
	$("#contenido3").find(":input:radio:checked").each(function(){
		if($(this).prop("checked")){
			arrayID.push($(this).val());
		}
    });
	return arrayID;
}

function recorrerDet22_3C(){
	var arrayID = [];
	$("#contenido3").find(".detC").each(function(){
		var valor = $(this).val();
		if(valor==""){
			arrayID.push("Null");
		}else{
			arrayID.push(valor);
		}
    });
	return arrayID;
}

//////////Metodos area 23_1//////////////

function Guardar23_1(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "23_1";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg1 = recorrerCodPre23_1();
	var Resp1 = recorrerRes23_1();
	var codPreg2 = recorrerCodPreRad23_1();
	var Resp2 = recorrerResRad23_1();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=19&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="+fecha+
	"&trim="+trim+"&codPreg1="+codPreg1+"&Resp1="+Resp1+"&codUser="+codUser+"&codInt="+codInt
	+"&codPreg2="+codPreg2+"&Resp2="+Resp2);
}

function recorrerCodPre23_1(){
	var arrayID = [];
	$("#contenido1").find(".clasEV").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function recorrerRes23_1(){
	var arrayID = [];
	$("#contenido1").find(".evol").each(function(){
		arrayID.push($(this).val());
    });
	return arrayID;
}

function recorrerCodPreRad23_1(){
	var arrayID = [];
	$("#contenido1").find(".RadEvo").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function recorrerResRad23_1(){
	var arrayID = [];
	$("#contenido1").find(":input:radio:checked").each(function(){
		if($(this).prop("checked")){
			arrayID.push($(this).val());
		}
    });
	return arrayID;
}

//////////Metodos area 23_2//////////////

function Guardar23_2(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "23-2";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg = recorrerCodPre23_2();
	var Resp = recorrerRes23_2();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=20&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="+fecha+
	"&trim="+trim+"&codPreg="+codPreg+"&Resp="+Resp+"&codUser="+codUser+"&codInt="+codInt);
}

function recorrerCodPre23_2(){
	var arrayID = [];
	$("#contenido2").find("label").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function recorrerRes23_2(){
	var arrayID = [];
	$("#contenido2").find("input:text").each(function(){
		arrayID.push($(this).val());
    });
	return arrayID;
}

//////////Metodos area 23_3//////////////

function Guardar23_3(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "23-3";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg = recorrerCodPre23_3();
	var Resp = recorrerRes23_3();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=21&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="+fecha+
	"&trim="+trim+"&codPreg="+codPreg+"&Resp="+Resp+"&codUser="+codUser+"&codInt="+codInt);
}

function recorrerCodPre23_3(){
	var arrayID = [];
	$("#contenido3").find("label").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function recorrerRes23_3(){
	var arrayID = [];
	$("#contenido3").find(".textf").each(function(){
		var valor = $(this).val();
		if(valor==""){
			arrayID.push("Sin novedades...");
		}else{
			arrayID.push($(this).val());
		}
    });
	return arrayID;
}

////////////////////////////////////////

function ExLab(cont){
	if($("#TxtF"+cont).val()!=""){
		$('#TxtR'+cont).attr("disabled",false);
		$('#TxtR'+cont).focus();
	}else if($("#TxtF"+cont).val("")==""){
		$('#TxtR'+cont).val('');
		$('#TxtR'+cont).attr("disabled",true);
	}
}

function BorExLab(cont){
	if($("#TxtF"+cont).val()!=""){
		$('#TxtF'+cont).val("");
		$('#TxtF'+cont).focus();
		$('#TxtR'+cont).val("");
		$('#TxtR'+cont).attr("disabled",true);
	}
}

//////////Metodos area 24_1//////////////

function Guardar24_1(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "24-1";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var codPreg = recorrerCodPre24_1();
	var fecLab = recorrerResFec24_1();
	var Resp = recorrerRes24_1();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=22&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="+fecha+
	"&trim="+trim+"&codPreg="+codPreg+"&Resp="+Resp+"&codUser="+codUser+"&codInt="+codInt+"&fecLab="+fecLab);
}

function recorrerCodPre24_1(){
	var arrayID = [];
	$("#contenido1").find(".label").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}

function recorrerResFec24_1(){
	var arrayID = [];
	$("#contenido1").find(".clasF").each(function(){
		var valor = $(this).val();
		if(valor==""){
			arrayID.push("0000-00-00");
		}else{
			arrayID.push($(this).val());
		}
    });
	return arrayID;
}

function recorrerRes24_1(){
	var arrayID = [];
	$("#contenido1").find(".clasR").each(function(){
		var valor = $(this).val();
		if(valor==""){
			arrayID.push("Sin resultados");
		}else{
			arrayID.push($(this).val());
		}
    });
	return arrayID;
}

//////////Metodos area 24_2//////////////

function Guardar24_2(){
	var codPac = $("#TxtCodPac").val();
	var cedPac = $("#TxtCedPac").val();
	var area = "24-2";
	var fecha = $("#TxtFecha").val();
	var trim = Trimestre();
	var secciones = recorrerSec24_2();
	var codPreg1 = recorrerCodPre1();
	var codPreg2 = recorrerCodPre2();
	var codPreg3 = recorrerCodPre3();
	var Resp1 = recorrerRes1();
	var Resp2 = recorrerRes2();
	var Resp3 = recorrerRes3();
	var codUser = $("#TxtCod").val();
	var codInt = $("#TxtCodInt").val();
	ajax = getXMLObject();
	ajax.open("POST","ControlConfiguracionPP",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		}
	};
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send("op=23&codPac="+codPac+"&cedPac="+cedPac+"&area="+area+"&fecha="+fecha+
	"&trim="+trim+"&codPreg1="+codPreg1+"&codPreg2="+codPreg2+"&codPreg3="+codPreg3+
	"&Resp1="+Resp1+"&Resp2="+Resp2+"&Resp3="+Resp3+"&codUser="+codUser+
	"&codInt="+codInt+"&secciones="+secciones);
}

function recorrerSec24_2(){
	var arrayID = [];
	$("#contenido2").find(".labSec").each(function(){
		arrayID.push($(this).text());
    });
	return arrayID;
}

function recorrerCodPre1(){
	var arrayID = [];
	$("#d1").find(".labCond").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}
function recorrerCodPre2(){
	var arrayID = [];
	$("#d2").find(".labCond").each(function(){
		arrayID.push($(this).attr('id'));
    });
	return arrayID;
}
function recorrerCodPre3(){
	var arrayID = [];
	$("#d3").find(":input:radio:checked").each(function(){
		if($(this).prop("checked")){
			arrayID.push($(this).attr('id'));
		}
    });
	return arrayID;
}

function recorrerRes1(){
	var arrayID = [];
	arrayID.push($("#TxtFec").val());
	$("#d1").find(":input:checkbox").each(function(){
		var valor = "";
		if($(this).prop("checked")){
			valor = "Si";
			arrayID.push(valor);
		}else{
			valor = "No";
			arrayID.push(valor);
		}
    });
	return arrayID;
}
function recorrerRes2(){
	var arrayID = [];
	$("#d2").find(":input:checkbox").each(function(){
		var valor = "";
		if($(this).prop("checked")){
			valor = "Si";
			arrayID.push(valor);
		}else{
			valor = "No";
			arrayID.push(valor);
		}
    });
	return arrayID;
}
function recorrerRes3(){
	var arrayID = [];
	$("#d3").find(":input:radio:checked").each(function(){
		if($(this).prop("checked")){
			arrayID.push($(this).val());
		}
    });
	return arrayID;
}

/////////////////////////////////////////

function validarChTe(Cont){
	if(($("#RbA"+Cont).prop("checked"))&&($("#TxtRbA"+Cont).val()=="")){
		alert("Debe llenar el campo!");
		$("#TxtRbA"+Cont).focus();
	}else{
		return true;
	}
}

function validarChTe2(Cont){
	if(($("#ChA"+Cont).prop("checked"))&&($("#TxtNumA"+Cont).val()=="")){
		alert("Debe llenar el campo!");
		$("#TxtNumA"+Cont).focus();
	}else{
		return true;
	}
}

function validarCT1(Cont){
	if($("#RbA"+Cont).prop("checked")){
		$("#TxtRbA"+Cont).attr("disabled",false);
		$("#TxtRbA"+Cont).focus();
	}else{
		$("#TxtRbA"+Cont).val("");
		$("#TxtRbA"+Cont).attr("disabled",true);
	}
}

function validarCT2(Cont){
	if($("#ChA"+Cont).prop("checked")){
		$("#TxtChA"+Cont).attr("disabled",false);
		$("#TxtChA"+Cont).focus();
	}else{
		$("#TxtChA"+Cont).val("");
		$("#TxtChA"+Cont).attr("disabled",true);
	}
}

function verifica(){
	var sel = document.getElementById("selectA");
	var Num1 = document.getElementById("TxtNum1");
	Num1.disabled = (sel.selectedIndex==0);
}

function SumarA(){
	var arrayID = [];
	var sum = 0;
	$("#contenido1").find(":input:checked").each(function(){
		arrayID.push($(this).val());
    });
	$.each(arrayID,function(){sum+=parseFloat(this) || 0;});
	$("#TxtSubTotal").val(sum);
}

function SumarPuntajesB(Cont){
	var arrayID = [];
	var sum = 0;
	$("#contenido2").find(":input:checked").each(function(){
		arrayID.push($(this).val());
    });
	$.each(arrayID,function(){sum+=parseFloat(this) || 0;});
	$("#TxtSubTotal2").val(sum);
}

function SumarPuntajesC(Cont){
	var arrayID = [];
	var sum = 0;
	$("#contenido3").find(":input:checked").each(function(){
		arrayID.push($(this).val());
    });
	$.each(arrayID,function(){sum+=parseFloat(this) || 0;});
	$("#TxtSubTotal3").val(sum);
}

function SumarPunRBD(){
	var Cont = $('.ClasInt:checked').length;
	if(Cont>=2){
		$("#TxtSubTotal4").val(1);
	}
	var Cont = $('.ClasAus:checked').length;
	if(Cont>=2){
		$("#TxtSubTotal4").val("");
	}
}

function SumarPuntajesDD(id){
	var resultado = 0;
	$("#contenido4").find('select').each(function(){
		resultado += parseInt($(this).val(),10);
	});
	$('#T6').val(resultado);
	if($('#T6').val()>=10){
		$('#T5').val(1);
	}else{
		$('#T5').val('');
	}
}

function addRows(cam){
	var con = $("#table1 tr").size();
	if(cam.value==""){
		return false;
	}else{
		con++;
		$('#table1 tr:last').after("<tr id='tr"+con+"'><td align='center'>" +
		"<input type='text'id='tab"+con+"'value='Datos del formulario'size='111'onkeypress='Enter3(this)'" +
		"onfocus='camVacio(this)'onblur='camNoVacio(this);'></td></tr>");
	}
}

function camVacio(campo){
	if(campo.value=="Datos del formulario"){
		campo.value="";
	}
}

function camNoVacio(campo){
	if(campo.value==""){
		campo.value="Datos del formulario";
	}
}

function cambiar(celda){
	celda.style.backgroundColor="#CADEE8";
}

function cambiar2(celda){
	celda.style.backgroundColor="#FFFFFF";
}

function GenReportes(){
	var CodPacI = $("#TxtCodInt").val();
	var TipCed = $("#TxtTipoDoc").val();
	var NumCed = $("#TxtCedPac").val();
	if(!$("input[name=rep]:checked").val()){
		alert("Debe escoger una opcion antes!");
	}else if($("input[name=rep]:checked").val()=="infPer"){
		pagina="aiepi_ReporteGestantes.jsp?TipCed="+TipCed+"&NumCed="+NumCed+"";			
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
	}else if($("input[name=rep]:checked").val()=="etapa1"){
		pagina="aiepi_ReporteGestantes1.jsp?CodPacI="+CodPacI+"";			
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
	}
}

function mascara(campo){
	$(document).ready(function(){
		$(campo).mask("99/99/9999");
	});
}