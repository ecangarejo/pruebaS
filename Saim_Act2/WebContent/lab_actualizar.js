
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
//////////////////////////////////////////////////////////////////////////////////////////////////
function getCheckedRadio() {
	getXMLObject();
	if(xmlhttp) { 
		 var radioButtons = document.form1.radiobutton;
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;     
		    xmlhttp.open("POST","ControlActuExa?valor="+val,true); //getname will be the servlet name
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
	        alert("Error during AJAX call. Please try again, getCheckedRadio");
	     }
		ajaxArea();
	   }
}
///////////////////////////////////////////////////////////////////////////////////////////////////
function mostrarrango(){
	getXMLObject();
	if(xmlhttp) {
			
	    xmlhttp.open("POST","ControlActuExa?valor=16",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = rango;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); 
	   }
}
function rango(){

	if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
			document.getElementById('exame').innerHTML = xmlhttp.responseText;
		}
		 else {
		        alert("Error during AJAX call. Please try again,mostrarrango");
		     }
		codexamengru();
	}
	//
}
///////////////////////////////////////////////////////////////////////////////////////////////////
function codexamengru(){
	getXMLObject();
	if(xmlhttp) {
		  var exa=document.getElementById('cmbexagruAc').selectedIndex;
		  exa=document.getElementById('cmbexagruAc').options[exa].text;
	 	var x=document.getElementById('txtcodarea').value; 	
	    xmlhttp.open("POST","ControlActuExa?valor=15&codarea="+x+"&nomexa="+exa,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = examenesgru;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
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
		     		document.getElementById('txtcodunidadAct').value=z[1];
		     		document.getElementById('txtcodgeneroAct').value=z[2];
		     		document.getElementById('cmbunidadAct').options[0].text=z[3];
		     		document.getElementById('cmbGeneroACt').options[0].text=z[4];
		     		document.getElementById('txtvaliniAct').value=z[5];
		     	    document.getElementById('txtvalfinAct').value=z[6];
		     	    document.getElementById("txtCodigoRango").value=z[7];
			    }
		     	  llenarUnidades();
		     }
		     else {
		        alert("Error during AJAX call. Please try again,codexamengru");
		     }		
		   }
	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
function codexamen(){
	getXMLObject();
	if(xmlhttp) {
		  var exa=document.getElementById('cmbexamenAc').selectedIndex;
		  exa=document.getElementById('cmbexamenAc').options[exa].text;
	 	var x=document.getElementById('txtcodsubar').value; 	
	    xmlhttp.open("POST","ControlActuExa?valor=5&codSuba="+x+"&nomexa="+exa,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = examenes;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
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
		     	 mostrarActu();
		     }
		     else {
		        alert("Error during AJAX call. Please try again,codexamen");
		     }		
		   }
	 
}
//////////////////////////////////////////////////////////////////////////////////////////////
function mostrarActu(){
	getXMLObject();
	if(xmlhttp) {
		var exa=document.getElementById('cmbexamenAc').selectedIndex;
		  exa=document.getElementById('cmbexamenAc').options[exa].text;
		var x=document.getElementById('txtcodsubar').value;
	    xmlhttp.open("POST","ControlActuExa?valor=6&codSuba="+x+"&nomexa="+exa,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ver;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send("");
	   }
}
function ver (){
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {	

			document.getElementById('exame').innerHTML = xmlhttp.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,mostrarActu");
	     }
		//llenarOtros();
		llenarUnidades();
	   }
	   
}
////////////////////////////////////////////////////////////////////////////////////////////////////////
/*function llenarOtros(){
	getXMLObject();
	if(xmlhttp) {
		var exa=document.getElementById('cmbexamenAc').selectedIndex;
		  	exa=document.getElementById('cmbexamenAc').options[exa].text;
		var x=document.getElementById('txtcodsubar').value;
	    xmlhttp.open("POST","ControlActuExa?valor=7&codSuba="+x+"&nomexa="+exa,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = verOtros;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); 
	   }
}*/
function verOtros(){
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{    		
		     		document.getElementById('txtcodunidadAct').value=z[0];
		     		document.getElementById('txtcodgeneroAct').value=z[1];
		     		document.getElementById('cmbunidadAct').options[0].text=z[2];
		     		document.getElementById('cmbGeneroACt').options[0].text=z[3];
			    }
		     	llenarUnidades();
	     }
	     else {
	        alert("Error during AJAX call. Please try again,llenarOtros");
	     }
	   }
	   
}
////////////////////////////////////////////////////////////////////////////////////////////////
function llenarUnidades() {	
	getXMLObject();
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlActuExa?valor=9",true); 
	    xmlhttp.onreadystatechange  = ComprobarUnidad;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); 
	   }
	}
function ComprobarUnidad() {
	   if (xmlhttp.readyState == 4) {	   
		if(xmlhttp.status == 200) {
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	
	     	document.getElementById('cmbunidadAct').length=y;	     
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		/*for(i=0;i<z[x].length;i++){
	     			z[x]=z[x].replace('ñ','%');
	     		    }*/
	     		document.getElementById('cmbunidadAct').options[x+1].text=z[x];
		    }	
	     	llenarGenero();
	     } else {
	        alert("Error during AJAX call. Please try again,llenarUnidades");
	     }
			
	   }
	}

///////////////////////////////////////////////////////////////////////////////////////////////

function llenarGenero(){
	getXMLObject();
	if(xmlhttp){
		xmlhttp.open("POST","ControlActuExa?valor=10",true);
		xmlhttp.onreadystatechange = ComprobarGenero;
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send("");
		}
}
function ComprobarGenero(){
	  if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		     	document.getElementById('cmbGeneroACt').length=y;	     
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
		     		document.getElementById('cmbGeneroACt').options[x+1].text=z[x];	
			    }		    		
		     }
		     else {
		        alert("Error during AJAX call. Please try again,llenarGenero");
		     }
		     
		   }	
}
///////////////////////////////////////////////////////////////////////////////////////////////
function obtenerCodGenero(){
	getXMLObject();
	if(xmlhttp){
		var x=document.getElementById('cmbGeneroACt').selectedIndex;
		  x=document.getElementById('cmbGeneroACt').options[x].text;
		xmlhttp.open("POST","ControlActuExa?valor=13&nomGen="+x,true);
		xmlhttp.onreadystatechange = CodigoGenero;
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send("");
		}	
}
function CodigoGenero(){
	  if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
		     		document.getElementById('txtcodgeneroAct').value=z[x];			     
			    }		    		
		     }
		     else {
		        alert("Error during AJAX call. Please try again,obtenerCodGenero");
		     }		     
		   }	
}
///////////////////////////////////////////////////////////////////////////////////////////////

function obtenerCodUnidad(){
	getXMLObject();
	if(xmlhttp){
		var x=document.getElementById('cmbunidadAct').selectedIndex;
		  x=document.getElementById('cmbunidadAct').options[x].text;
		  /*for(i=0;i<x.length;i++){
		      x=x.replace('%','ñ');
		    }*/
		xmlhttp.open("POST","ControlActuExa?valor=8&nomUni="+x,true);
		xmlhttp.onreadystatechange = CodigoUnidad;
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send("");
		}	
}

function CodigoUnidad(){
	  if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
		     		document.getElementById('txtcodunidadAct').value=z[x];			     
			    }		    		
		     }
		     else {
		        alert("Error during AJAX call. Please try again,obtenerCodUnidad");
		     }		     
		   }	
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////
function mostrarexamen(){
	getXMLObject();
	 if(xmlhttp) {
		 	var x=document.getElementById('txtcodsubar').value; 
		    xmlhttp.open("POST","ControlActuExa?valor=0&codSuba="+x,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarExamen;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
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
///////////////////////////////// AJAX AREA////////////////////////////
function ajaxArea() {
	getXMLObject();
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlActuExa?valor=3",true); 
	    xmlhttp.onreadystatechange  = ComprobarArea;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); 
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
	     	document.getElementById('cmbsubareaS').selectedIndex=0;	    		
	     }
	     else {
	        alert("Error during AJAX call. Please try again,ajaxArea");
	     }		
	   }
}
/////////////////////////////////////////////////////////////////////////////////////////////
function subareas() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('txtcodarea').value;
		xmlhttp.open("POST","ControlActuExa?valor=4&area="+x,true); 
	    xmlhttp.onreadystatechange  = ComprobarSubareas;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
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
function codareaGru() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('cmbareaA').selectedIndex;
		  x=document.getElementById('cmbareaA').options[x].text;
		xmlhttp.open("POST","ControlActuExa?valor=11&codarea="+x,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCodAreaGru;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
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
		xmlhttp.open("POST","ControlActuExa?valor=14&area="+x,true); 
	    xmlhttp.onreadystatechange  = ComprobarSubareasGru;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
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
function codarea() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('cmbareaA').selectedIndex;
		  x=document.getElementById('cmbareaA').options[x].text;
		xmlhttp.open("POST","ControlActuExa?valor=11&codarea="+x,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCodArea;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); 
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
function codsubarea() {
	getXMLObject();
	  if(xmlhttp) { 	 
		  var x=document.getElementById('txtcodarea').value;
		  var subarea=document.getElementById('cmbsubareaS').selectedIndex;
		  subarea=document.getElementById('cmbsubareaS').options[subarea].text;
		xmlhttp.open("POST","ControlActuExa?valor=12&codarea="+x+"&nomsubarea="+subarea,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCodSubArea;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
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
function Actualizar(){
	
	var codUni=document.getElementById('txtcodunidadAct').value;
	var codGen=document.getElementById('txtcodgeneroAct').value;
	//var valIni=document.getElementById('txtvaliniAct').value;
	//var valFin=document.getElementById('txtvalfinAct').value;
	var codExa=document.getElementById('txtcodexa').value;
	var Contador=document.getElementById('txtContador').value;
	//var CodigoRango=document.getElementById('txtCodigoRango').value;
	var oscar=Contador;
	
	if(codUni==""){
		alert("Falta Seleccionar La Unidad.");
	}
	
	if(oscar==1){
	if(codGen==""){
		alert("Falta Seleccionar El Genero");
	}
	}
	/*if(valIni==""){
		alert("Falta Ingresar El Valor Inicial");
	}
	if(valFin==""){
		alert("Falta Ingresar El Valor Final");
	}*/
	if(codExa==""){
		alert("Falta Seleccionar El Examen.");
	}
	if(Contador!=1){
		for(var k=0;k<=Contador-1;k++){
			getXMLObject();
			if(xmlhttp) {
				CodigoRango=document.form1.txtCodigoRango[k].value;
				valfinAct=document.form1.txtvalfinAct[k].value;
				valiniAct=document.form1.txtvaliniAct[k].value;	
				//codUni=document.form1.txtcodunidadAct[k].value;
				//alert(codUni)
				xmlhttp.open("POST","ControlActuExa?valor=17&entra=1&codUni="+codUni+"&valIni="+valiniAct+"&valFin="+valfinAct+"&CodigoRango="+CodigoRango,true); 
				xmlhttp.send("");
			}
			
		}
		window.location.reload();
	}
	
	if(Contador==1){
		
		getXMLObject();
		if(xmlhttp) {
			
			CodigoRango=document.form1.txtCodigoRango.value;
			valfinAct=document.form1.txtvalfinAct.value;
			valiniAct=document.form1.txtvaliniAct.value;		
			xmlhttp.open("POST","ControlActuExa?valor=17&entra=2&codUni="+codUni+"&valIni="+valiniAct+"&valFin="+valfinAct+"&CodigoRango="+CodigoRango+"&codGen="+codGen,true); 
			xmlhttp.send("");
		}
		alert("Actualizacion Exitosa.");
		window.location.reload();
	}
	/*else{		
		window.location.href="ControlActuExa?codUni="+codUni+"&codGen="+codGen+"&valIni="+valIni+"&valFin="+valFin+"&codExa="+codExa;
		alert("Actualizacion Exitosa!!!")
		}*/

	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////
