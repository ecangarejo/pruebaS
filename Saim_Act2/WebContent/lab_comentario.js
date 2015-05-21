
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
function Radio() {	
	getXMLObject();
	if(xmlhttp) { 
		 var radioButtons = document.getElementsByName("radio");
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;     
		    xmlhttp.open("POST","ControlComentario?valor="+val,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = MostrarTipo;
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }	     
	    }
	 }
}

function MostrarTipo() {	
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			document.getElementById('valor').innerHTML = xmlhttp.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,Radio");
	     }
		ajaxArea()
	   }
	
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
function ajaxArea() {
	getXMLObject();
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlComentario?valor=3",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarArea;
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
		 //ajaxUnidad()	  
	   }
		 
	}
/////////////////////////////////////////////////////////////////////////////////////////////
function codarea() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('cmbareaA').selectedIndex;
		  x=document.getElementById('cmbareaA').options[x].text;
		xmlhttp.open("POST","ControlComentario?valor=4&codarea="+x,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCodArea;
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
		subareas();		
	   }	   
	}
////////////////////////////////////////////////////////////////////////////////////////////////////
function codareaGru() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('cmbareaA').selectedIndex;
		  x=document.getElementById('cmbareaA').options[x].text;
		xmlhttp.open("POST","ControlComentario?valor=4&codarea="+x,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCodAreaGru;
	    xmlhttp.send(""); 
	   }
	}
function ComprobarCodAreaGru() {
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
	     	subareasGru();
	     }
	     else {
	        alert("Error during AJAX call. Please try again,codareaGru");
	     }		
	   }
	    
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
function subareasGru() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('txtcodarea').value;
		xmlhttp.open("POST","ControlComentario?valor=5&area="+x,true); 
	    xmlhttp.onreadystatechange  = ComprobarSubareasGru;
	    xmlhttp.send(""); 
	   }
	}
function ComprobarSubareasGru() {
	   if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	                                                          
	     	document.getElementById('cmbexagruAc').length=y;	     
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		document.getElementById('cmbexagruAc').options[x+1].text=z[x];
		    }	
	     	document.getElementById('cmbexagruAc').selectedIndex=0;
	     }
	     else {
	        alert("Error during AJAX call. Please try again,subareasGru");
	     }		
	   }
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
function subareas() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('txtcodarea').value;
		xmlhttp.open("POST","ControlComentario?valor=6&area="+x,true); 
	    xmlhttp.onreadystatechange  = ComprobarSubareas;
	    xmlhttp.send(""); 
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
	     	document.getElementById('cmbsubareaS').selectedIndex=0;
	     }
	     else {
	        alert("Error during AJAX call. Please try again,subareas");
	     }		
	   }
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
function codsubarea() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('txtcodarea').value;
		  var subarea=document.getElementById('cmbsubareaS').selectedIndex;
		  subarea=document.getElementById('cmbsubareaS').options[subarea].text;
		xmlhttp.open("POST","ControlComentario?valor=7&codarea="+x+"&nomsubarea="+subarea,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCodSubArea;
	    xmlhttp.send(""); 
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
		 mostrarexamen();	 
	   }
	    
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
function mostrarexamen(){
	getXMLObject();
	 if(xmlhttp) {
		 	var x=document.getElementById('txtcodsubar').value; 	
		    xmlhttp.open("POST","ControlComentario?valor=8&codSuba="+x,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarExamen;
		    xmlhttp.send("");
		   }
	
}
function ComprobarExamen(){
	if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
			var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	
	     	document.getElementById('cmbexamenAc').length=y;	     
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		document.getElementById('cmbexamenAc').options[x+1].text=z[x];
		    }	
		    
	     	document.getElementById('cmbexamenAc').selectedIndex=0;	
	     }
	     else {
	        alert("Error during AJAX call. Please try again,mostrarexamen");
	     }		
	   }
}
//////////////////////////////////////////////////////////////////////////////////////////
function codexamen(){
	getXMLObject();
	if(xmlhttp) {
		  var exa=document.getElementById('cmbexamenAc').selectedIndex;
		  exa=document.getElementById('cmbexamenAc').options[exa].text;
	 	var x=document.getElementById('txtcodsubar').value; 	
	    xmlhttp.open("POST","ControlComentario?valor=9&codSuba="+x+"&nomexa="+exa,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = examenes;
	    xmlhttp.send(""); 
	   }
}
function examenes(){
	  if (xmlhttp.readyState == 4) {	   
			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
		     		document.getElementById('txtcodexa').value=z[0];
			    }
		     }
		     else {
		        alert("Error during AJAX call. Please try again,codexamen");
		     }	
			mostrarrango();	
		   }
}
//////////////////////////////////////////////////////////////////////////////////////////////
function codexamengru(){
	getXMLObject();
	if(xmlhttp) {
		  var exa=document.getElementById('cmbexagruAc').selectedIndex;
		  exa=document.getElementById('cmbexagruAc').options[exa].text;
	 	var x=document.getElementById('txtcodarea').value; 	
	    xmlhttp.open("POST","ControlComentario?valor=10&codGru="+x+"&nomExa="+exa,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = examenesgru;
	    xmlhttp.send(""); 
	   }
}
function examenesgru(){
	  if (xmlhttp.readyState == 4) {	   
			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
		     		document.getElementById('txtcodexa').value=z[0];
			    }
		     	 
		     }
		     else {
		        alert("Error during AJAX call. Please try again,codexamengru");
		     }
			mostrarrango(); 		
		   }
}
//////////////////////////////////////////////////////////////////////////////////////////////
function mostrarrango(){
	getXMLObject();
	if(xmlhttp) {			
	    xmlhttp.open("POST","ControlComentario?valor=11",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = rango;
	    xmlhttp.send(""); 
	   }
}
function rango(){
	if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
			document.getElementById('tipos').innerHTML = xmlhttp.responseText;
		}
		 else {
		        alert("Error during AJAX call. Please try again,mostrarrango");
		     }
		llenarCampos();
	}
}
///////////////////////////////////////////////////////////////////////////////////////////////////
function llenarCampos(){
	getXMLObject();
	if(xmlhttp) {
		var x=document.getElementById('txtcodexa').value;
	    xmlhttp.open("POST","ControlComentario?valor=12&codExa="+x,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = verOtros;
	    xmlhttp.send(""); 
	   }
}
function verOtros(){
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{    		
		     		document.getElementById('txtvalini').value=z[0];
		     		document.getElementById('txtvalfin').value=z[1];
		     		
		     		document.getElementById('txtnormalini').value=z[2];
		     		document.getElementById('txtnormalfin').value=z[3];
		     		
		     		document.getElementById('txtbajoini').value=z[4];
		     		document.getElementById('txtbajofin').value=z[5];

		     		document.getElementById('txtaltoini').value=z[6];
		     		document.getElementById('txtaltofin').value=z[7];

		     		document.getElementById('txtbcritini').value=z[8];
		     		document.getElementById('txtbcritfin').value=z[9];

		     		document.getElementById('txtacritini').value=z[10];
		     		document.getElementById('txtacritfin').value=z[11];
			    }
	     }
	     else {
	        alert("Error during AJAX call. Please try again,llenarCampos");
	     }
	   }
	   
}
////////////////////////////////////////////////////////////////////////////////////////////////
function insertarComentario(){
	var normalini=document.getElementById('txtnormalini').value;
	var normalfin=document.getElementById('txtnormalfin').value;
	var bajoini=document.getElementById('txtbajoini').value;
	var bajofin=document.getElementById('txtbajofin').value;
	var altoini=document.getElementById('txtaltoini').value;
	var altofin=document.getElementById('txtaltofin').value;
	var bcritini=document.getElementById('txtbcritini').value;
	var bcritfin=document.getElementById('txtbcritfin').value;
	var acritini=document.getElementById('txtacritini').value;
	var acritfin=document.getElementById('txtacritfin').value;
	var codExafk=document.getElementById('txtcodexa').value;
	if((normalini=="")||(normalfin=="")||(bajoini=="")||(bajofin=="")||(altoini=="")||(altofin=="")||(bcritini=="")||(bcritfin=="")||(acritini=="")||(acritfin=="")||(codExafk=="")){	
		alert("No Se Ha Llenado los Datos")
		}else{		
		window.location.href="ControlComentario?normalini="+normalini+"&normalfin="+normalfin+"&bajoini="+bajoini+"&bajofin="+bajofin+"&altoini="+altoini+"&altofin="+altofin+"&bcritini="+bcritini+"&bcritfin="+bcritfin+"&acritini="+acritini+"&acritfin="+acritfin+"&codExafk="+codExafk;
		alert("Ingreso Exitoso!!!")
		}
}