function objetoAjax(){
	var xmlhttp=false;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
		   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
  		}
	}

	if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp;
}
/////////////////////////////////////////////////////////////////////////////////
function getXMLObject() // XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // For Old Microsoft
		// Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // For Microsoft
			// IE 6.0+
		} catch (e2) {
			xmlHttp = false; // No Browser accepts the XMLHTTP Object then
			// false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}

var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object
/////////////////////////////////////////////////////////////////////////////////

//traslados por usuario 1
function trasUsu() {
	
	//alert("entro a funcion radio :P");
	ajax=getXMLObject();
	var contenido=document.getElementById('examenes');

	
		   
			ajax.open("POST","ControlAuxClinico",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=cargarUsu"); //Posting txtname to Servlet
			     
	
}

function Radio() {
	
	//alert("entro a funcion radio :P");
	ajax=getXMLObject();
	var contenido=document.getElementById('examenes');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlAuxClinico",true); //getname will be the servlet name
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


//traslados por usuario (solo usuario)
function TrasPorUsuario(){
	 var idAux=document.getElementById("cmbAuxiliar").value;
	 if(idAux=="Seleccione"){
		 alert("Seleccione El usuario.");
	 }
	 if(idAux!="Seleccione"){
		 ajax=getXMLObject();
			ajax.open("POST","ControlAuxClinico",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('examenes').innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=cargarTrasUsu"+"&idAux="+idAux); //Posting txtname to Servlet
	 }
}



//traslados por usuario 2 (por usuario y rango de fechas)
function TrasPorUsuario2(){
	 var idAux=document.getElementById("cmbAuxiliar").value;
	 var fi=document.getElementById("FI").value;
		var ff=document.getElementById("FF").value;	
	 if(idAux=="Seleccione"){
		 alert("Seleccione El usuario.");
	 }else{
		 if((fi=="")||(ff=="")){
				alert("Debe seleccionar un rango de busqueda");
	 }
		 else{
		 ajax=getXMLObject();
			ajax.open("POST","ControlAuxClinico",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('examenes').innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=cargarTrasUsu"+"&idAux="+idAux+"&fi="+fi+"&ff="+ff); //Posting txtname to Servlet
	 }
	 }
}

//traslados por usuario 3 (todos los usuarios y rango de fechas)
function TrasPorUsuario3(){
	 var fi=document.getElementById("FI").value;
		var ff=document.getElementById("FF").value;	
	
		 if((fi=="")||(ff=="")){
				alert("Debe seleccionar un rango de busqueda");
	 }
		 else{
		 ajax=getXMLObject();
			ajax.open("POST","ControlAuxClinico",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('examenes').innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=cargarTras"+"&fi="+fi+"&ff="+ff); //Posting txtname to Servlet
	 }
	 
}


//funci de la fecha 
var patron = new Array(2,2,4);
function masca(d,pat,nums,dias,mes,annio){
var sep="/";
if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if((ini>31)||(ini=="00")){
		//alert("Dia No Valido!!!");
		val=d.value="01";
		//d.focus();
	}
	val = val.split(sep);

	val2 = ''
	for(r=0;r<val.length;r++){
		val2 += val[r]
	}
	ini=val2.substring(2,4);
	if((ini=="04")||(ini=="06")||(ini=="09")||(ini=="11")){
		x=val2.substring(0,2);
		if(x=="31"){
			val2="30";
			val2=val2+ini;
		}
	}
	if((ini>12)||(ini=="00")){
		//alert("Mes No Valido!!!");
		mescj=val2.substring(0,2);
		val2=mescj+"01";
		//d.focus();
	}
	
	ano=val2.substring(4,8);
	dia=val2.substring(0,2);
	
	
	
	if(ini=="02"){
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
/*	///////////////////////////////////////////////////
	//Validar fecha mayor que la fecha actual
		
	if(ano.length==4){
		if (ano==annio) {
			if(ini==mes){
				if((dia==dias)||(dia<dias)){
					alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
				val2='';
				}
			}else{
				if(ini<mes){
					alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
					val2='';
				}
			}
		}
		if (ano<annio) {
			alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
			val2='';
		}
	}
	
	///////////////////////////////////////////////////
*/	

	
	if(nums){
		for(z=0;z<val2.length;z++){
			if(isNaN(val2.charAt(z))){
				letra = new RegExp(val2.charAt(z),"g")
				val2 = val2.replace(letra,"")
				
			}
		}
	}
		
	val = ''
	val3 = new Array()
	for(s=0; s<pat.length; s++){
 		val3[s] = val2.substring(0,pat[s])
 		val2 = val2.substring(pat[s])
	}
	
	for(q=0;q<val3.length; q++){
		if(q ==0){
			val = val3[q]         
		}
		else{
			if(val3[q] != ""){
				val += sep + val3[q]
			}
		}
	}
	d.value = val
	d.valant = val
	}
}


function fecha_gru(){
	  var time1 = new Date()
	  var ano = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()

	  var temp1 = "" + ((ano < 10) ? "0" : "") + ano
	  temp1 += ((mes < 10) ? ":0" : ":") + mes
	  temp1 += ((dia < 10) ? ":0" : ":") + dia
	 document.getElementById('txtfecha').value = temp1
	 id = setTimeout("fecha()",1000)
	  
	 
	  }





function hora_gru(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txthora').value = temp;
	  id = setTimeout("hora()",1000)
	  }



function  validar(h){
		if (h.txtnumdoc.value.length==0){
			window.alert('Debe Digitar la Cedula');
			h.nom.focus();
			return false;
		}
	h.submit();
	}


function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

////////////////////////////////////////////////////////////////////////
function Mostrar() {	
	if (ajax.readyState == 4) {		
		if(ajax.status == 200) {		
			document.getElementById('cambio').innerHTML = ajax.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,getCheckedRadio");
	     }
	   }
}
