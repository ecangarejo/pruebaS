
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

function cerrar() {
	div = document.getElementById('tipos');
	div.style.display='none';
	}
/////////////////////////////////////////////////////////////////////////////////////////
/*function Radio() {	
	if(xmlhttp) { 
		 var radioButtons = document.getElementsByName("radio");
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;    
		    xmlhttp.open("POST","ControlCrearExamen?valor="+val,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = MostrarRadio;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }	     
	    }
	 }
}
function MostrarRadio() {	
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			tipos.innerHTML = xmlhttp.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,Radio");
	     }
	   }
}*/
////////////////////////////////////////////////////////////////////////////////////////
/*function prueba() {
	if(xmlhttp) { 
		 var check = document.getElementsByName("checkbox");
		
	      for (var x = 0; x < check.length; x ++) {
	        if (check[x].checked) {
		         var val=check[x].id;    
		    xmlhttp.open("POST","ControlCrearExamen?valor="+val,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = MostrarCheck;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }	     
	    }
}
}

function MostrarCheck() {	
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			otros.innerHTML = xmlhttp.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,prueba");
	     }
		
	   }
	   
}*/
//////////////////////////////////////////////////////////////////////////////////////////////////
function RadioTipo1() {	
	getXMLObject();
	if(xmlhttp) { 
		 var radioButtons = document.getElementsByName("radiobutton1");
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;     
		    xmlhttp.open("POST","ControlCrearExamen?valor="+val,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = MostrarTipo1;
		   // xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }	     
	    }
	 }
}

function MostrarTipo1() {	
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			document.getElementById('tipos').innerHTML = xmlhttp.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,RadioTipo1");
	     }
		ajaxArea()
	   }
	
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
function getCheckedRadio() {
	if(xmlhttp) { 
		 var radioButtons = document.getElementsByName("radiobutton");
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;     
		    xmlhttp.open("POST","ControlCrearExamen?valor="+val,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = Mostrar;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }	     
	    }
	 }
}
function Mostrar() {	
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			document.getElementById('valor').innerHTML = xmlhttp.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,getCheckedRadio");
	     }
		ajaxArea()
	   }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////

function Alternar(){ 
	div = document.getElementById('tipos');	
    if (div.style.display=="none"){div.style.display=""}
    else{div.style.display="none"} 
}
function mostrardiv() {	
	div = document.getElementById('tipos');
	div.style.display = 'none';
	}
//////////////////////////////////AJAX GENERO////////////////////////////////////////////
function ajaxGenero(){
	if(xmlhttp){
		xmlhttp.open("POST","ControlCrearExamen?valor=7",true);
		xmlhttp.onreadystatechange = comprobarGenero;
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send("");
		}
}
function comprobarGenero(){
	  if (xmlhttp.readyState == 4) {	   
			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		     	document.getElementById('cmbgenero').length=y;	     
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			    document.getElementById('cmbgenero').options[x+1].text=z[x];
			    }		    		
		     }
		     else {
		        alert("Error during AJAX call. Please try again,ajaxGenero");
		     }
		   }	
}
/////////////////////////////////AJAX UNIDAD////////////////////////////////
function ajaxUnidad() {
	getXMLObject();
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlCrearExamen?valor=9",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarUnidad;
	    //xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	}
function ComprobarUnidad() {
	   if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     		     	
	     	document.getElementById('cmbunidad').length=y;	     
	        var h,ss;	        	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		for(i=0;i<z[x].length;i++){
	     			z[x]=z[x].replace('9','%');
	     		    }
		     document.getElementById('cmbunidad').options[x+1].text=z[x];
		    }  	
	     }
	     else {
	        alert("Error during AJAX call. Please try again,ajaxUnidad");
	     }
		ajaxGenero()		
	   }
	   	   
	}
/////////////////////////////////FIN AJAX UNIDAD////////////////////////////
function ajaxArea() {
	getXMLObject();
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlCrearExamen?valor=3",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarArea;
	   // xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	}
function ComprobarArea() {
	   if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	
	     	document.getElementById('cmbareaA').length=y;	     
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     	document.getElementById('cmbareaA').options[x+1].text=z[x];

		    }		    		
	     }
	     else {
	        alert("Error during AJAX call. Please try again,ajaxArea");
	     }
		 ajaxUnidad()	  
	   }
		 
	}
/////////////////////////////////////////////////////////////////////////////////////////////
function subareas() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('cmbareaA').selectedIndex;
		  x=document.getElementById('cmbareaA').options[x].text;
		xmlhttp.open("POST","ControlCrearExamen?valor=4&area="+x,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarSubareas;
	   //xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	}
function ComprobarSubareas() {
	   if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	
	     	document.getElementById('cmbsubareaS').length=y;	     
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		document.getElementById('cmbsubareaS').options[x+1].text=z[x];
		    }		    		
	     }
	     else {
	        alert("Error during AJAX call. Please try again,subareas");
	     }	
		 codarea();	  
	   }
	   
	}
/////////////////////////////////////CODIGO DEL GENERO/////////////////////////////////////////
function genero() {
	  if(xmlhttp) { 	 
		  var x=document.getElementById('cmbgenero').selectedIndex;
		  x=document.getElementById('cmbgenero').options[x].text;
		xmlhttp.open("POST","ControlCrearExamen?valor=8&nomGen="+x,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCodGenero;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	}

function ComprobarCodGenero() {
	   if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		document.getElementById('txtcodGen').value=z[0];
		    }		    		
	     }
	     else {
	        alert("Error during AJAX call. Please try again,genero");
	     }		
	   }	   
	}


/////////////////////////////////////CODIGO DE LA UNIDAD///////////////////////////////////////
function unidades() {
	  if(xmlhttp) { 	 
		  var x=document.getElementById('cmbunidad').selectedIndex;
		  x=document.getElementById('cmbunidad').options[x].text;
		  for(i=0;i<x.length;i++){
		      x=x.replace('%','9');
		    }
		 // alert(x);
		xmlhttp.open("POST","ControlCrearExamen?valor=10&unidad="+x,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarUnidades;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	}
function ComprobarUnidades() {
	   if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		document.getElementById('txtcoduni').value=z[0];
		    }		    		
	     }
	     else {
	        alert("Error during AJAX call. Please try again,unidades");
	     }		
	   }	   
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
function codarea() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('cmbareaA').selectedIndex;
		  x=document.getElementById('cmbareaA').options[x].text;
		xmlhttp.open("POST","ControlCrearExamen?valor=11&codarea="+x,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCodArea;
	    //xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	}
function ComprobarCodArea() {
	   if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		document.getElementById('txtcodarea').value=z[0];
		    }		    		
	     }
	     else {
	        alert("Error during AJAX call. Please try again,codarea");
	     }		
	   }	   
	}
////////////////////////////////////////////////////////////////////////////////////////////////////
function codsubarea() {
	  if(xmlhttp) { 	 
		  var x=document.getElementById('txtcodarea').value;
		  var subarea=document.getElementById('cmbsubareaS').selectedIndex;
		  subarea=document.getElementById('cmbsubareaS').options[subarea].text;
		xmlhttp.open("POST","ControlCrearExamen?valor=12&codigoarea="+x+"&nomsubarea="+subarea,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCodSubArea;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	}
function ComprobarCodSubArea() {
	
	   if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		document.getElementById('txtcodsubar').value=z[0];
		    }		    		
	     }
	     else {
	        alert("Error during AJAX call. Please try again,codsubarea");
	     }		
	   }	   
	}
/////////////////////////////////////INSERCIONES DE RANGO//////////////////////////////////////////
///////////////////////////////////////INSERTA POR EDAD Y GENENERO///////////////////////////
function InsertarEdadGenero(){
var nomb=document.getElementById('txtnomexam').value;
var codAex=document.getElementById('txtcodarea').value;
var codSex=document.getElementById('txtcodsubar').value;
var valIni=document.getElementById('txtvalini').value;
var valFin=document.getElementById('txtvalfin').value;
var codUni=document.getElementById('txtcoduni').value;
var codGen=document.getElementById('txtcodGen').value;
var edaIni=document.getElementById('txtedadini').value;
var edaFin=document.getElementById('txtedadfin').value;
var CodUsu=document.getElementById("txtCodusuario").value;
if((nomb=="")||(codAex=="")||(valIni=="")||(valFin=="")||(codUni=="")){	
	alert("No Se Ha Llenado los Datos")
	}else{		
	window.location.href="ControlCrearExamen?tipo=1&nomb="+nomb+"&codAex="+codAex+"&codSex="+codSex+"&val=62&valIni="+valIni+"&valFin="+valFin+"&codUni="+codUni+"&codGen="+codGen+"&edaFin="+edaFin+"&edaIni="+edaIni+"&CodUsu="+CodUsu;
	alert("Ingreso Exitoso!!!")
	}
}
///////////////////////////////////////INSERTA RANGO GENERAL///////////////////////////
function InsertarGeneral(){
var nomb=document.getElementById('txtnomexam').value;
var codAex=document.getElementById('txtcodarea').value;
var codSex=document.getElementById('txtcodsubar').value;
var valIni=document.getElementById('txtvalini').value;
var valFin=document.getElementById('txtvalfin').value;
var codUni=document.getElementById('txtcoduni').value;
var CodUsu=document.getElementById("txtCodusuario").value;
if((nomb=="")||(codAex=="")||(valIni=="")||(valFin=="")||(codUni=="")){	
	alert("No Se Ha Llenado los Datos")
	}else{		
	window.location.href="ControlCrearExamen?tipo=1&nomb="+nomb+"&codAex="+codAex+"&codSex="+codSex+"&val=6&valIni="+valIni+"&valFin="+valFin+"&codUni="+codUni+"&codGen=0&edaIni=0&edaFin=0&CodUsu="+CodUsu;
	alert("Ingreso Exitoso!!!")
	}
}
///////////////////////////////////////INSERTA RANGO GENERO///////////////////////////
function InsertarGenero(){
var nomb=document.getElementById('txtnomexam').value;
var codAex=document.getElementById('txtcodarea').value;
var codSex=document.getElementById('txtcodsubar').value;
var valInihombre=document.getElementById('txtvalinihombre').value;
var valFinhombre=document.getElementById('txtvalfinhombre').value;
var valInimujer=document.getElementById('txtvalinimujer').value;
var valFinmujer=document.getElementById('txtvalfinmujer').value;
var codUni=document.getElementById('txtcoduni').value;
var codGen=document.getElementById('txtcodGen').value;
var CodUsu=document.getElementById("txtCodusuario").value;
if((nomb=="")||(codAex=="")||(valInihombre=="")||(valFinhombre=="")||(codUni=="")||(valInimujer=="")||(valFinmujer=="")){	
	alert("No Se Ha Llenado los Datos")
	}else{		
	window.location.href="ControlCrearExamen?tipo=1&nomb="+nomb+"&codAex="+codAex+"&codSex="+codSex+"&val=yosi&valInih="+valInihombre+"&valInim="+valInimujer+"&valFinm="+valFinmujer+"&codUni="+codUni+"&codGen="+codGen+"&valFinh="+valFinhombre+"&edaIni=0&edaFin=0&CodUsu="+CodUsu;
	alert("Ingreso Exitoso!!!")
	}
}
///////////////////////////////////////INSERTA RANGO edad///////////////////////////
function InsertarEdad(){
var nomb=document.getElementById('txtnomexam').value;
var codAex=document.getElementById('txtcodarea').value;
var codSex=document.getElementById('txtcodsubar').value;
var valIni=document.getElementById('txtvalini').value;
var valFin=document.getElementById('txtvalfin').value;
var codUni=document.getElementById('txtcoduni').value;
var edaIni=document.getElementById('txtedadini').value;
var edaFin=document.getElementById('txtedadfin').value;
var CodUsu=document.getElementById("txtCodusuario").value;
if((nomb=="")||(codAex=="")||(valIni=="")||(valFin=="")||(codUni=="")){	
	alert("No Se Ha Llenado los Datos")
	}else{		
	window.location.href="ControlCrearExamen?tipo=1&nomb="+nomb+"&codAex="+codAex+"&codSex="+codSex+"&val=61&valIni="+valIni+"&valFin="+valFin+"&codUni="+codUni+"&codGen=0&edaIni="+edaIni+"&edaFin="+edaFin+"&CodUsu="+CodUsu;
	alert("Ingreso Exitoso!!!")
	}
}
////////////////////////////////////FIN DE INSERCIONES DE TIPO RANGO///////////////////////////////
////////////////////////////////////INSERTA TIPO TEXTO//////////////////////////////////////////////
function verificar1(){	

var nomb=document.getElementById('txtnomexam').value;
var codAex=document.getElementById('txtcodarea').value;
var codSex=document.getElementById('txtcodsubar').value;
var CodUsu=document.getElementById("txtCodusuario").value;
if((nomb=="")||(codAex=="")){
alert("No Se Ha Llenado los Datos")
}else{		
window.location.href="ControlCrearExamen?tipo=0&nomb="+nomb+"&codAex="+codAex+"&codSex="+codSex+"&val=5&CodUsu="+CodUsu;
alert("Ingreso Exitoso!!!")
}
}